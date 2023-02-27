package com.guga.ordemparanormal.common.entity;

import net.minecraft.world.entity.LivingEntity;

public interface RitualCasterMob {

    /**
     * Attack the specified entity using a ritual.
     */
    void performRitualCast(LivingEntity pTarget, float pDistanceFactor);
}
