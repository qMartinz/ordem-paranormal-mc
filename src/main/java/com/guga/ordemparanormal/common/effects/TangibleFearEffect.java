package com.guga.ordemparanormal.common.effects;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.client.particles.AbilitiesParticleOptions;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

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
                    level.sendParticles(AbilitiesParticleOptions.createData(ParanormalElement.MEDO.getParticleColor()),
                            entity.getX(), entity.getY() + 0.1d, entity.getZ(),
                            0, Math.cos(i), 0.4d, Math.sin(i), 0.03d);
                }
            }

            level.sendParticles(AbilitiesParticleOptions.createData(new Color(255, 180, 180)),
                    entity.getX(), entity.getY() + entity.getEyeHeight()/2d, entity.getZ(),
                    6, 0.3d, 0.3d, 0.3d, 0d);
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
