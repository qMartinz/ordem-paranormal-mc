package com.guga.ordemparanormal.common.effects;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class TangibleFearEffect extends MobEffect {
    public TangibleFearEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level instanceof ServerLevel level) {
            for (int i = 0; i < 360; i++) {
                if (i % 40 == 0) {
                    level.sendParticles(ParticleTypes.INSTANT_EFFECT,
                            entity.getX(), entity.getY() + 0.1d, entity.getZ(),
                            0, Math.cos(i) + 0.2d, 0.1d, Math.sin(i) + 0.2d, 1d);
                }
            }

            level.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                    entity.getX(), entity.getY() + entity.getEyeHeight()/2d, entity.getZ(),
                    4, 0.2d, 0.2d, 0.2d, 1d);
        }
    }
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        int i = 45 >> pAmplifier;
        if (i > 0) {
            return pDuration % i == 0;
        } else {
            return true;
        }
    }
}
