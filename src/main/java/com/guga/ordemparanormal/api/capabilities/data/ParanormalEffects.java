package com.guga.ordemparanormal.api.capabilities.data;

import com.guga.ordemparanormal.api.ElementDamage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;

import java.util.ArrayList;
import java.util.Collection;

public class ParanormalEffects implements IEffectsCap{
    public int deathHealthPoints;
    public int bloodArmorPoints;
    public int knowledgeEffortPoints;
    @Override
    public int getDeathHealthPoints() {
        return deathHealthPoints;
    }
    @Override
    public int getBloodArmorPoints() {
        return bloodArmorPoints;
    }
    public Collection<DamageSource> unappliableBloodArmorDamageSources(){
        ArrayList<DamageSource> unappliableDamageSources = new ArrayList<>();

        unappliableDamageSources.add(DamageSource.DROWN);
        unappliableDamageSources.add(DamageSource.WITHER);
        unappliableDamageSources.add(DamageSource.STARVE);
        unappliableDamageSources.add(DamageSource.OUT_OF_WORLD);
        unappliableDamageSources.add(ElementDamage.DANO_MORTE);
        unappliableDamageSources.add(ElementDamage.DANO_MEDO);

        return unappliableDamageSources;
    }
    @Override
    public int getKnowledgeEffortPoints() {
        return knowledgeEffortPoints;
    }
    @Override
    public void setDeathHealthPoints(int amount) {
        deathHealthPoints = Math.max(0, amount);
    }

    @Override
    public void setBloodArmorPoints(int amount) {
        bloodArmorPoints = Math.max(0, amount);
    }

    @Override
    public void setKnowledgeEffortPoints(int amount) {
        knowledgeEffortPoints = Math.max(0, amount);
    }
    @Override
    public void copyFrom(IEffectsCap source) {
        deathHealthPoints = source.getDeathHealthPoints();
        bloodArmorPoints = source.getBloodArmorPoints();
        knowledgeEffortPoints = source.getKnowledgeEffortPoints();
    }
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();

        nbt.putInt("deathHealthPoints", deathHealthPoints);
        nbt.putInt("bloodArmorPoints", bloodArmorPoints);
        nbt.putInt("knowledgeEffortPoints", knowledgeEffortPoints);

        return nbt;
    }
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        deathHealthPoints = nbt.getInt("deathHealthPoints");
        bloodArmorPoints = nbt.getInt("bloodArmorPoints");
        knowledgeEffortPoints = nbt.getInt("knowledgeEffortPoints");
    }
}
