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

public class DecayEffect extends MobEffect {
    public DecayEffect(MobEffectCategory effectCategory, int color) {
        super(effectCategory, color);
    }
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (ElementDamage.isEntityResistant(entity, ElementDamage.DEATH_DAMAGE)){
            entity.removeEffect(this);
        } else {
            float amount = ElementDamage.isEntityWeakTo(entity, ElementDamage.DEATH_DAMAGE) ? 2f : 1f;
            entity.hurt(ElementDamage.DEATH_DAMAGE, amount);
            entity.level.playSound(null, entity.blockPosition(), SoundEvents.WITHER_SKELETON_AMBIENT, SoundSource.AMBIENT, 1f, 1f);
            ServerLevel level = (ServerLevel) entity.level;
            int i = 5 * amplifier;
            level.sendParticles(ParticleTypes.ASH, entity.getRandomX(0.5D), entity.getRandomY(), entity.getRandomZ(0.5D), i, 0, 0, 0, 1d);
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
