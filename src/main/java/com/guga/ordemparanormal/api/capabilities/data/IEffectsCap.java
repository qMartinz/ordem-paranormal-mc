package com.guga.ordemparanormal.api.capabilities.data;

import net.minecraft.nbt.CompoundTag;

public interface IEffectsCap {
    int getDeathHealthPoints();
    int getBloodArmorPoints();
    int getKnowledgeEffortPoints();
    void setDeathHealthPoints(int amount);
    void setBloodArmorPoints(int amount);
    void setKnowledgeEffortPoints(int amount);
    void copyFrom(IEffectsCap source);
    CompoundTag serializeNBT();
    void deserializeNBT(CompoundTag nbt);
}
