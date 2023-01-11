package com.guga.ordemparanormal.api.capabilities.data;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.CurseHelper;
import com.guga.ordemparanormal.api.curses.CurseInstance;
import com.guga.ordemparanormal.core.network.Messages;
import com.guga.ordemparanormal.core.network.Packets;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import javax.annotation.Nonnull;
import java.util.UUID;

public class CapManager extends SavedData {
    private int counter = 0;
    private final UUID uuid = UUID.randomUUID();
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
                    IAbilitiesCap abilities = serverPlayer.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
                    IEffectsCap effects = serverPlayer.getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).orElse(null);
                    if (nex == null || effects == null || abilities == null) return;

                    float knowledgeCursePenalty = 1f;
                    for (ItemStack item : player.getInventory().items) {
                        knowledgeCursePenalty += 0.4f * CurseHelper.getCurses(item).stream().filter(curse ->
                                        curse.getCurse() != null && !curse.getCurse().isTemporary() &&
                                                curse.getCurse().getElement() == ParanormalElement.CONHECIMENTO)
                                .toList().size();
                    }
                    for (ItemStack item : player.getAllSlots()) {
                        knowledgeCursePenalty += 0.4f * CurseHelper.getCurses(item).stream().filter(curse ->
                                        curse.getCurse() != null && !curse.getCurse().isTemporary() &&
                                                curse.getCurse().getElement() == ParanormalElement.CONHECIMENTO)
                                .toList().size();
                    }

                    if (nex.getCurrentEffort() != nex.getMaxEffort() && serverPlayer.getFoodData().getFoodLevel() >= 20){
                        nex.setCurrentEffort(nex.getCurrentEffort() + (0.15D / knowledgeCursePenalty));
                        serverPlayer.getFoodData().addExhaustion(0.3f);
                    } else {
                        nex.setCurrentEffort(nex.getCurrentEffort() + (0.05D / knowledgeCursePenalty));
                    }
                    if (nex.getPowerCooldown() > 0) nex.setPowerCooldown(nex.getPowerCooldown() - 1);

                    nex.syncEffortMods();
                    Messages.sendToPlayer(new Packets.SyncNexToClient(nex.serializeNBT()), serverPlayer);
                    Messages.sendToPlayer(new Packets.SyncEffects(effects.serializeNBT()), serverPlayer);
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
