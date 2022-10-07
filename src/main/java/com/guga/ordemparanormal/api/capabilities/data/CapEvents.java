package com.guga.ordemparanormal.api.capabilities.data;

import com.guga.ordemparanormal.api.ElementDamage;
import com.guga.ordemparanormal.api.capabilities.network.Packets;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.network.Messages;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class CapEvents {
    @SubscribeEvent
    public static void onEntityAttachCapabilities(AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof Player player){
            if (!player.getCapability(PlayerNexProvider.PLAYER_NEX).isPresent()){
                event.addCapability(PlayerNexProvider.IDENTIFIER, new PlayerNexProvider());
            }
            if (!player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).isPresent()){
                event.addCapability(PlayerAbilitiesProvider.IDENTIFIER, new PlayerAbilitiesProvider());
            }
        }
        if (!event.getObject().getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).isPresent()){
            event.addCapability(ParanormalEffectsProvider.IDENTIFIER, new ParanormalEffectsProvider());
        }
    }
    @SubscribeEvent
    public static void onRegisterCapabilites(RegisterCapabilitiesEvent event){
        event.register(PlayerNex.class);
        event.register(PlayerAbilities.class);
        event.register(ParanormalEffects.class);
    }
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event){
        Player oldPlayer = event.getOriginal();
        oldPlayer.revive();

        oldPlayer.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(oldNex -> event.getPlayer().getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(newNex -> newNex.copyFrom(oldNex)));

        if (!event.isWasDeath()){
            oldPlayer.getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).ifPresent(oldEffects ->
                    event.getPlayer().getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).ifPresent(newEffects -> newEffects.copyFrom(oldEffects)));
        }

        oldPlayer.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(oldPowers ->{
            IAbilitiesCap newAbilities = event.getPlayer().getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(new PlayerAbilities());
            CompoundTag tag = oldPowers.serializeNBT();
            newAbilities.deserializeNBT(tag);
            if (event.getPlayer() instanceof ServerPlayer){
                syncPlayerPowers(event.getPlayer());
            }
        });

        event.getOriginal().invalidateCaps();
    }
    @SubscribeEvent
    public static void onPlayerLoginEvent(PlayerEvent.PlayerLoggedInEvent event) {
        syncPlayerPowers(event.getPlayer());
    }
    @SubscribeEvent
    public static void respawnEvent(PlayerEvent.PlayerRespawnEvent event) {
        syncPlayerPowers(event.getPlayer());
        if (!event.isEndConquered()){
            INexCap nex = event.getPlayer().getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
            if (nex == null) return;
            nex.setCurrentEffort(nex.getMaxEffort());
        }
    }
    @SubscribeEvent
    public static void onPlayerStartTrackingEvent(PlayerEvent.StartTracking event) {
        syncPlayerPowers(event.getPlayer());
    }
    @SubscribeEvent
    public static void onPlayerDimChangedEvent(PlayerEvent.PlayerChangedDimensionEvent event) {
        syncPlayerPowers(event.getPlayer());
    }
    public static void syncPlayerPowers(Player player){
        if (player instanceof ServerPlayer) {
            player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(cap -> {
                CompoundTag tag = cap.serializeNBT();
                Messages.sendToPlayer(new Packets.SyncAbilities(tag), player);
            });
        }
    }
    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event){
        if (event.world.isClientSide){
            return;
        }
        if (event.phase == TickEvent.Phase.START){
            return;
        }
        CapManager manager = CapManager.get(event.world);
        manager.tick(event.world);
    }
    @SubscribeEvent
    public static void onEntityHurt(LivingHurtEvent event){
        IEffectsCap effects = event.getEntity().getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).orElse(null);
        if (effects == null) return;

        List<DamageSource> unAppliableBloodArmor = new ArrayList<>();
        unAppliableBloodArmor.add(DamageSource.DROWN);
        unAppliableBloodArmor.add(DamageSource.WITHER);
        unAppliableBloodArmor.add(DamageSource.STARVE);
        unAppliableBloodArmor.add(DamageSource.OUT_OF_WORLD);
        unAppliableBloodArmor.add(ElementDamage.DANO_MORTE);
        unAppliableBloodArmor.add(ElementDamage.DANO_MEDO);

        List<DamageSource> unAppliableMedoTangivel = new ArrayList<>();
        unAppliableMedoTangivel.add(DamageSource.OUT_OF_WORLD);
        unAppliableMedoTangivel.add(ElementDamage.DANO_MORTE);
        unAppliableMedoTangivel.add(ElementDamage.DANO_SANGUE);
        unAppliableMedoTangivel.add(ElementDamage.DANO_CONHECIMENTO);
        unAppliableMedoTangivel.add(ElementDamage.DANO_ENERGIA);
        unAppliableMedoTangivel.add(ElementDamage.DANO_MEDO);

        float bloodArmorApplied = !unAppliableBloodArmor.contains(event.getSource()) ? Math.max(event.getAmount() - effects.getBloodArmorPoints()/2f, 1) : event.getAmount();
        float deathHealthApplied = Math.max(bloodArmorApplied - effects.getDeathHealthPoints(), 0);
        effects.setDeathHealthPoints(effects.getDeathHealthPoints() - (int) Math.min(effects.getDeathHealthPoints(), bloodArmorApplied));
        if (event.getEntity() instanceof LivingEntity entity && entity.hasEffect(OPEffects.TANGIBLE_FEAR.get()) &&
        !unAppliableMedoTangivel.contains(event.getSource())) deathHealthApplied = 0;

        event.setAmount(deathHealthApplied);
    }
}
