package com.guga.ordemparanormal.common.effects;

import com.guga.ordemparanormal.api.capabilities.data.ParanormalEffectsProvider;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.level.block.Blocks;

public class SkinReinforcedEffect extends RitualEffect {
    public SkinReinforcedEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }
    @Override
    public void addAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        pLivingEntity.getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).ifPresent(effects -> effects.setBloodArmorPoints(effects.getBloodArmorPoints() + (pAmplifier + 1)));
        super.addAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }
    @Override
    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        pLivingEntity.getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).ifPresent(effects -> effects.setBloodArmorPoints(effects.getBloodArmorPoints() - (pAmplifier + 1)));
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        int i = 5 + amplifier * 2;
        if (entity.level instanceof ServerLevel level)
            level.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()),
                    entity.getX(), entity.getY(), entity.getZ(),
                    i, 0, 0, 0, 1d);
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
