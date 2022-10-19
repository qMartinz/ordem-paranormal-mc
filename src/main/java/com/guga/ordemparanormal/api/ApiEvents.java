package com.guga.ordemparanormal.api;

import com.guga.ordemparanormal.api.capabilities.data.*;
import com.guga.ordemparanormal.api.capabilities.network.Packets;
import com.guga.ordemparanormal.api.curses.CurseHelper;
import com.guga.ordemparanormal.api.curses.CurseInstance;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.network.Messages;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class ApiEvents {
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
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event){
        if (event.getEntityLiving() instanceof Player player){
            player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities ->
                    playerAbilities.getPowers().forEach(power -> power.onTick(player)));
        }

        CurseHelper.doTickEffects(event.getEntityLiving());
    }
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onEntityHurt(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof LivingEntity living) {
            event.setAmount(CurseHelper.doPostAttackEffects(living, event.getEntityLiving(), event.getAmount(), event.getSource()));
        }

        event.setAmount(CurseHelper.doPostHurtEffects(event.getEntityLiving(), event.getSource().getEntity(), event.getAmount(), event.getSource()));

        if (event.getSource().getEntity() instanceof Player player)
            player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities ->
                    playerAbilities.getPowers().forEach(power -> event.setAmount(power.onAttack(player, event.getAmount(), event.getEntityLiving(), event.getSource()))));

        if (event.getEntity() instanceof Player player)
            player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities ->
                    playerAbilities.getPowers().forEach(power -> event.setAmount(power.onHurt(player, event.getAmount(), event.getSource().getEntity(), event.getSource()))));
    }
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onParanormalEffectsApply(LivingHurtEvent event){
        float amount = event.getAmount();

        // pega a capability com efeitos paranormais
        IEffectsCap targetEffects = event.getEntityLiving().getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).orElse(null);
        if (targetEffects == null) return;

        // altera o dano com base nos efeitos paranormais
        amount = !targetEffects.unappliableBloodArmorDamageSources().contains(event.getSource()) ||
                (event.getSource() instanceof ParanormalDamageSource s && s.element == ParanormalElement.MORTE) ?
                Math.max(amount - targetEffects.getBloodArmorPoints()/2f, 1) : amount;

        float deathHealthApplied = Math.max(amount - targetEffects.getDeathHealthPoints(), 0);
        targetEffects.setDeathHealthPoints(targetEffects.getDeathHealthPoints() - (int) Math.min(targetEffects.getDeathHealthPoints(), amount), event.getEntityLiving().getMaxHealth());
        amount = deathHealthApplied;

        event.setAmount(amount);
    }
    @SubscribeEvent
    public static void onLivingUseItem(LivingEntityUseItemEvent event){
        if (event.getEntity() instanceof Player player)
            player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities ->
                    playerAbilities.getPowers().forEach(power -> {
                        if (event instanceof LivingEntityUseItemEvent.Start) event.setDuration(power.onStartUseItem(player, event.getItem(), event.getDuration()));
                        if (event instanceof LivingEntityUseItemEvent.Tick) event.setDuration(power.onTickUseItem(player, event.getItem(), event.getDuration()));
                        if (event instanceof LivingEntityUseItemEvent.Finish e) e.setResultStack(power.onFinishUseItem(player, event.getItem(), e.getResultStack(), event.getDuration()));
                    }));
    }
    @SubscribeEvent
    public static void onRenderItemTooltips(ItemTooltipEvent event){
        if (!CurseHelper.getCurses(event.getItemStack()).isEmpty() && CurseHelper.getCurses(event.getItemStack()).stream().noneMatch(Objects::isNull)){

            List<Component> curseComponents = new ArrayList<>();
            for (CurseInstance curse : CurseHelper.getCurses(event.getItemStack())) {
                curseComponents.add(curse.getCurse().getDisplayName());
            }
            curseComponents.sort(Comparator.comparing(Component::getString));

            event.getToolTip().addAll(1, curseComponents);
        }
    }
    @SubscribeEvent
    public static void onCalculateItemAttributes(ItemAttributeModifierEvent event){
        if (!CurseHelper.getCurses(event.getItemStack()).isEmpty() && CurseHelper.getCurses(event.getItemStack()).stream().noneMatch(Objects::isNull)){
            for (CurseInstance curse : CurseHelper.getCurses(event.getItemStack())){
                curse.getCurse().getAttributeModifiers().forEach((modifier, attribute) -> {
                    if (Arrays.stream(curse.getCurse().getSlots()).anyMatch(slot -> slot == event.getSlotType())) event.addModifier(attribute, modifier);
                });
            }
        }
    }
}
