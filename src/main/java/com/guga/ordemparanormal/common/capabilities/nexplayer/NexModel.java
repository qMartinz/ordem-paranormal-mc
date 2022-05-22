package com.guga.ordemparanormal.common.capabilities.nexplayer;

import com.guga.ordemparanormal.common.network.SyncNex;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nonnull;

public class NexModel implements INBTSerializable<CompoundTag> {
    private final Player player;
    public int nexLevel;
    public double nexXP;
    public final int maxNex = 99;

    public NexModel(@Nonnull Player player) {
        this.player = player;
    }
    public int getNexLevel(){ return this.nexLevel; }
    public void setNexLevel(int level){
        this.nexLevel = level;
        onDataUpdated();
    }
    public boolean isMaxNex(){ return this.nexLevel == this.maxNex; }
    public double getNexXp(){ return this.nexXP; }
    public void giveNexXP(double amount){
        Minecraft minecraft = Minecraft.getInstance();
        this.nexXP += amount;
        int levelsGained = 0;
        while (this.nexXP >= (this.nexLevel + 1) * 10){
            this.nexXP -= (this.nexLevel + 1) * 10;
            levelsGained++;
        }
        this.nexLevel += levelsGained;
        onDataUpdated();
    }
    public static NexModel get(Player player){
        return player.getCapability(NexCapability.INSTANCE).orElseThrow(() ->
                new IllegalArgumentException("Player " + player.getName().getContents() + " does not have a NeX Model.")
        );
    }
    public static NexModel get() {
        return Minecraft.getInstance().player.getCapability(NexCapability.INSTANCE).orElseThrow(() ->
                new IllegalArgumentException("Player does not have a NeX Model!")
        );
    }
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("nexlevel", this.nexLevel);
        nbt.putDouble("nexxp", this.nexXP);

        return nbt;
    }
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.nexLevel = nbt.getInt("nexlevel");
        this.nexXP = nbt.getDouble("nexxp");
    }

    public void onDataUpdated() {
        if (!player.level.isClientSide)
            OrdemParanormal.network.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new SyncNex(this.serializeNBT()));
    }
}