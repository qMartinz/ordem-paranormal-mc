package com.guga.ordemparanormal.common.effects;

import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.mojang.math.Vector3f;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public class DecayEffect extends OPEffects.ParanormalEffect {
    public DecayEffect(MobEffectCategory effectCategory, int color) {
        super(effectCategory, color);
    }
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        Random random = new Random();
        if (ParanormalDamageSource.isEntityResistant(entity, ParanormalDamageSource.DANO_MORTE)){
            entity.removeEffect(this);
        } else {
            float amount = 2f * amplifier;
            entity.hurt(ParanormalDamageSource.DANO_MORTE, amount);

            if (entity.level instanceof ServerLevel level)
                level.sendParticles(
                        new DustParticleOptions(new Vector3f(0.25f, 0.25f, 0.25f), 0.7f),
                        entity.getX() + random.nextDouble(-0.5d, 0.5d),
                        entity.getEyeY() + random.nextDouble(-0.5d, 0.5d),
                        entity.getZ() + random.nextDouble(-0.5d, 0.5d),
                        (int) amount*2, 0, 0, 0, random.nextDouble(0.5d));
        }
    }
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        int i = 30 >> pAmplifier;
        if (i > 0) {
            return pDuration % i == 0;
        } else {
            return true;
        }
    }
}
