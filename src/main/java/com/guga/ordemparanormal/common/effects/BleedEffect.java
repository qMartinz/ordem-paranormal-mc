package com.guga.ordemparanormal.common.effects;

import com.guga.ordemparanormal.api.powers.ElementDamage;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;

public class BleedEffect extends MobEffect {
    public BleedEffect(MobEffectCategory effectCategory, int color) {
        super(effectCategory, color);
    }
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (ElementDamage.isEntityResistant(entity, ElementDamage.BLOOD_DAMAGE)){
            entity.removeEffect(this);
        } else {
            float amount = ElementDamage.isEntityWeakTo(entity, ElementDamage.BLOOD_DAMAGE) ? 2f : 1f;
            entity.hurt(ElementDamage.BLOOD_DAMAGE, amount);
            entity.level.playSound(null, entity.blockPosition(), SoundEvents.BEEHIVE_DRIP, SoundSource.AMBIENT, 1f, 1f);

            int i = 5 + amplifier * 2;
            if (entity.level instanceof ServerLevel level)
                level.sendParticles(
                        new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()),
                        entity.getX(), entity.getEyeY(), entity.getZ(),
                        i, 0, 0.3d, 0, 0d);
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
