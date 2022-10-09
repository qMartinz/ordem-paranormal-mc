package com.guga.ordemparanormal.api.capabilities.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;

import java.util.Collection;

public interface IEffectsCap {
    int getDeathHealthPoints();
    int getBloodArmorPoints();
    Collection<DamageSource> unappliableBloodArmorDamageSources();
    int getKnowledgeEffortPoints();
    void setDeathHealthPoints(int amount);
    void setBloodArmorPoints(int amount);
    void setKnowledgeEffortPoints(int amount);
    void copyFrom(IEffectsCap source);
    CompoundTag serializeNBT();
    void deserializeNBT(CompoundTag nbt);
}
