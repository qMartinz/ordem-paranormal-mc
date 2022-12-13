package com.guga.ordemparanormal.common.effects;

import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;

public class BleedEffect extends OPEffects.ParanormalEffect {
    public BleedEffect(MobEffectCategory effectCategory, int color) {
        super(effectCategory, color);
    }
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        float amount = 2f * (amplifier + 1);
        entity.hurt(ParanormalDamageSource.DANO_SANGUE, amount);

        if (entity.level instanceof ServerLevel level)
            level.sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()),
                    entity.getX(), entity.getEyeY(), entity.getZ(),
                    (int) amount*2, 0, 0, 0, 0);
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
