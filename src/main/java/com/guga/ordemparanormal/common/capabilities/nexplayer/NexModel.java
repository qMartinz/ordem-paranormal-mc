package com.guga.ordemparanormal.common.capabilities.nexplayer;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;

public class NexModel implements INBTSerializable<CompoundTag> {
    public int nexLevel;
    public double nexXP;
    public final int maxNex = 99;

    public int getNexLevel(){ return nexLevel; }
    public void setNexLevel(int level){ nexLevel = level; }
    public void lvlNexUp(){
        if (nexXP > (nexLevel + 1) * 10){
            nexXP -= (nexLevel + 1) * 10;
            nexLevel++;
        } else if (nexXP == (nexLevel + 1) * 10) {
            nexXP = 0;
            nexLevel++;
        }
    }
    public void lvlNexDown(){
        if (nexXP < 0 && nexLevel > 0) {
            nexXP += (nexLevel - 1) * 10;
            nexLevel--;
        }
    }
    public boolean isMaxNex(){ return nexLevel == maxNex; }

    public double getXP(){ return nexXP; }
    public void increaseXP(double amount){
        nexXP += amount;
        lvlNexUp();
    }
    public void decreaseXP(double amount){
        nexXP -= amount;
        lvlNexDown();
    }


    public static NexModel get(Player player){
        return player.getCapability(NexCapability.INSTANCE).orElseThrow(() ->
                new IllegalArgumentException("Player " + player.getName().getContents() + " does not have a NeX Model.")
        );
    }

    public static NexModel get()
    {
        return Minecraft.getInstance().player.getCapability(NexCapability.INSTANCE).orElseThrow(() ->
                new IllegalArgumentException("Player does not have a NeX Model!")
        );
    }
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("nexlevel", nexLevel);
        nbt.putDouble("nexxp", nexXP);

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        nexLevel = nbt.getInt("nexlevel");
        nexXP = nbt.getDouble("nexxp");
    }
}
