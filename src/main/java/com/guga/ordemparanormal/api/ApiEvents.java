package com.guga.ordemparanormal.api;

import com.guga.ordemparanormal.api.capabilities.data.*;
import com.guga.ordemparanormal.api.capabilities.network.Packets;
import com.guga.ordemparanormal.api.curses.CurseHelper;
import com.guga.ordemparanormal.api.curses.CurseInstance;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.network.Messages;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    @SubscribeEvent
    public static void onEntityHurt(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player && event.getSource().getEntity() instanceof LivingEntity living)
            player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities ->
                    playerAbilities.getPowers().forEach(power -> power.onHurt(player, living, event.getAmount())));

        if (event.getSource().getEntity() instanceof Player player && event.getEntity() instanceof LivingEntity living)
            player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities ->
                    playerAbilities.getPowers().forEach(power -> power.onAttack(player, living)));
    }
    @SubscribeEvent
    public static void onEntityAttack(LivingAttackEvent event){
        CurseHelper.doPostAttackEffects((LivingEntity) event.getSource().getEntity(), event.getEntity());
        CurseHelper.doPostHurtEffects(event.getEntityLiving(), event.getSource().getEntity(), event.getAmount(), event.getSource());
    }
    @SubscribeEvent
    public static void onRenderItemTooltips(ItemTooltipEvent event){
        if (!CurseHelper.getCurses(event.getItemStack()).isEmpty() && CurseHelper.getCurses(event.getItemStack()).stream().noneMatch(Objects::isNull)){
            List<Component> components = new ArrayList<>();

            CurseHelper.getCurses(event.getItemStack()).forEach(curse -> {
                components.add(curse.getCurse().getDisplayName());

                for (EquipmentSlot slot : curse.getCurse().getSlots()){
                    if (slot == EquipmentSlot.MAINHAND && curse.getCurse().getDamageBonus() > 0) {
                        event.getToolTip().add(curse.getCurse().getElement().getDamage().elementDmgTypeName().plainCopy()
                                .append(": ").append(Integer.toString(curse.getCurse().getDamageBonus())).withStyle(ChatFormatting.BLUE));
                    }
                }
            });
            event.getToolTip().addAll(1, components);
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
