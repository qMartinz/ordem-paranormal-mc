package com.guga.ordemparanormal.common.effects;

import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public class TangibleFearEffect extends OPEffects.ParanormalEffect {
    public TangibleFearEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }
    @Override
    public float onHurt(LivingEntity livingEntity, @Nullable Entity attacker, float amount, DamageSource source) {
        if ((!(source instanceof ParanormalDamageSource) || source == DamageSource.OUT_OF_WORLD)){
            return 0;
        }
        return amount;
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
