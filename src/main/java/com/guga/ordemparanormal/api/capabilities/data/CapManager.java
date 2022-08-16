package com.guga.ordemparanormal.api.capabilities.data;

import com.guga.ordemparanormal.api.capabilities.network.SyncEffects;
import com.guga.ordemparanormal.api.capabilities.network.SyncNexToClient;
import com.guga.ordemparanormal.api.capabilities.network.SyncNexToServer;
import com.guga.ordemparanormal.core.network.Messages;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import javax.annotation.Nonnull;

public class CapManager extends SavedData {
    private int counter = 0;
    @Nonnull
    public static CapManager get(Level level) {
        if (level.isClientSide) {
            throw new RuntimeException("Don't access this from the client.");
        }
        DimensionDataStorage storage = ((ServerLevel) level).getDataStorage();
        return storage.computeIfAbsent(CapManager::new, CapManager::new, "nexmanager");
    }
    public void tick(Level level){
        counter--;
        if (counter <= 0){
            counter = 10;
            level.players().forEach(player -> {
                if (player instanceof ServerPlayer serverPlayer){
                    INexCap nex = serverPlayer.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
                    IEffectsCap effects = serverPlayer.getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).orElse(null);
                    if (nex == null || effects == null) return;

                    if (nex.getCurrentEffort() != nex.getMaxEffort() && serverPlayer.getFoodData().getFoodLevel() >= 20){
                        nex.setCurrentEffort(nex.getCurrentEffort() + 0.3D);
                        serverPlayer.getFoodData().addExhaustion(0.5f);
                        Messages.sendToPlayer(new SyncNexToClient(nex.serializeNBT()), serverPlayer);
                    } else {
                        nex.setCurrentEffort(nex.getCurrentEffort() + 0.1D);
                        Messages.sendToPlayer(new SyncNexToClient(nex.serializeNBT()), serverPlayer);
                    }
                    nex.syncAttributeMods(serverPlayer);

                    Messages.sendToPlayer(new SyncEffects(effects.serializeNBT()), serverPlayer);
                } else if (player instanceof  LocalPlayer localPlayer){
                    INexCap nex = localPlayer.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
                    if (nex == null) return;

                    Messages.sendToServer(new SyncNexToServer(nex.serializeNBT()));
                }
            });
        }
    }
    public CapManager() {}
    public CapManager(CompoundTag tag) {}
    @Override
    public CompoundTag save(CompoundTag tag) {
        return tag;
    }
}
