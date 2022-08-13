package com.guga.ordemparanormal.api.capabilities.data;

import com.guga.ordemparanormal.api.capabilities.network.SyncPowers;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.network.Messages;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class CapEvents {
    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof Player player){
            if (!player.getCapability(PlayerNexProvider.PLAYER_NEX).isPresent()){
                event.addCapability(PlayerNexProvider.IDENTIFIER, new PlayerNexProvider());
            }
            if (!player.getCapability(PlayerPowersProvider.PLAYER_POWERS).isPresent()){
                event.addCapability(PlayerPowersProvider.IDENTIFIER, new PlayerPowersProvider());
            }
        }
        if (!event.getObject().getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).isPresent()){
            event.addCapability(ParanormalEffectsProvider.IDENTIFIER, new ParanormalEffectsProvider());
        }
    }
    @SubscribeEvent
    public static void onRegisterCapabilites(RegisterCapabilitiesEvent event){
        event.register(PlayerNex.class);
        event.register(PlayerPowers.class);
        event.register(ParanormalEffects.class);
    }
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event){
        Player oldPlayer = event.getOriginal();
        oldPlayer.revive();

        oldPlayer.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(oldNex -> event.getPlayer().getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(newNex -> {
            newNex.copyFrom(oldNex);
        }));

        if (!event.isWasDeath()){
            oldPlayer.getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).ifPresent(oldEffects ->
                    event.getPlayer().getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).ifPresent(newEffects -> {
                newEffects.copyFrom(oldEffects);
            }));
        }

        oldPlayer.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(oldPowers ->{
            IPowerCap newPowers = event.getPlayer().getCapability(PlayerPowersProvider.PLAYER_POWERS).orElse(new PlayerPowers());
            CompoundTag tag = oldPowers.serializeNBT();
            newPowers.deserializeNBT(tag);
            if (event.getPlayer() instanceof ServerPlayer){
                syncPlayerPowers(event.getPlayer());
            }
        });

        event.getOriginal().invalidateCaps();
    }
    @SubscribeEvent
    public static void onPlayerLoginEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getPlayer() instanceof ServerPlayer){
            syncPlayerPowers(event.getPlayer());
        }
    }
    @SubscribeEvent
    public static void respawnEvent(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getPlayer() instanceof ServerPlayer){
            syncPlayerPowers(event.getPlayer());
        }
        if (!event.isEndConquered()){
            INexCap nex = event.getPlayer().getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
            if (nex == null) return;
            nex.setCurrentEffort(nex.getMaxEffort());
        }
    }
    @SubscribeEvent
    public static void onPlayerStartTrackingEvent(PlayerEvent.StartTracking event) {
        if (event.getTarget() instanceof Player && event.getPlayer() instanceof ServerPlayer player) {
            syncPlayerPowers(player);
        }
    }
    @SubscribeEvent
    public static void onPlayerDimChangedEvent(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getPlayer() instanceof ServerPlayer) {
            syncPlayerPowers(event.getPlayer());
        }
    }
    public static void syncPlayerPowers(Player player){
        IPowerCap cap = player.getCapability(PlayerPowersProvider.PLAYER_POWERS).orElse(new PlayerPowers());
        CompoundTag tag = cap.serializeNBT();
        Messages.sendToPlayer(new SyncPowers(tag), player);
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

        float newAmount = Math.max(event.getAmount() - effects.getDeathHealthPoints(), 0);
        effects.setDeathHealthPoints(effects.getDeathHealthPoints() - (int) Math.min(effects.getDeathHealthPoints(), event.getAmount()));

        event.setAmount(newAmount);
    }
}
