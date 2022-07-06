package com.guga.ordemparanormal.api.capabilities.data;

import com.guga.ordemparanormal.api.capabilities.network.SyncAbilities;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.network.Messages;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
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

            if (!player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).isPresent()){
                event.addCapability(PlayerAbilitiesProvider.IDENTIFIER, new PlayerAbilitiesProvider());
            }
        }
    }
    @SubscribeEvent
    public static void onRegisterCapabilites(RegisterCapabilitiesEvent event){
        event.register(PlayerNex.class);
        event.register(PlayerAbilities.class);
    }
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event){
        Player oldPlayer = event.getOriginal();
        oldPlayer.revive();
        oldPlayer.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(oldNex -> event.getPlayer().getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(newNex -> {
            newNex.copyFrom(oldNex);
        }));

        oldPlayer.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(oldAbilities ->{
            if (event.getPlayer() instanceof ServerPlayer player){
                IPlayerCap newAbilities = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(new PlayerAbilities());
                CompoundTag tag = oldAbilities.serializeNBT();
                newAbilities.deserializeNBT(tag);
                syncPlayerAbilities(player);
            }
        });
        event.getOriginal().invalidateCaps();
    }
    @SubscribeEvent
    public static void onPlayerLoginEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if(event.getPlayer() instanceof ServerPlayer player){
            syncPlayerAbilities(player);
        }
    }
    @SubscribeEvent
    public static void respawnEvent(PlayerEvent.PlayerRespawnEvent event) {
        if(event.getPlayer() instanceof ServerPlayer player) {
            syncPlayerAbilities(player);
        }
        if (event.isEndConquered()){
            PlayerNex nex = event.getPlayer().getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
            if (nex == null) return;
            nex.setCurrentEffort(nex.getMaxEffort());
        }
    }
    @SubscribeEvent
    public static void onPlayerStartTrackingEvent(PlayerEvent.StartTracking event) {
        if (event.getTarget() instanceof Player && event.getPlayer() instanceof ServerPlayer player) {
            syncPlayerAbilities(player);
        }
    }
    @SubscribeEvent
    public static void onPlayerDimChangedEvent(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getPlayer() instanceof ServerPlayer player) {
            syncPlayerAbilities(player);
        }
    }
    public static void syncPlayerAbilities(ServerPlayer player){
        IPlayerCap cap = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(new PlayerAbilities());
        CompoundTag tag = cap.serializeNBT();
        Messages.sendToPlayer(new SyncAbilities(tag), player);
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
}
