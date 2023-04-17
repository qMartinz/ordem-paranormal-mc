package com.guga.ordemparanormal.common.effects;

import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.level.block.Blocks;

public class AdrenalineEffect extends OPEffects.ParanormalEffect {
    public AdrenalineEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        if (pLivingEntity.level instanceof ServerLevel level)
            level.sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()),
                    pLivingEntity.getX(), pLivingEntity.getEyeY(), pLivingEntity.getZ(),
                    5, 0, 0, 0, 0);
    }
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        int i = 15 >> pAmplifier;
        if (i > 0) {
            return pDuration % i == 0;
        } else {
            return true;
        }
    }
    @Override
    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 800));
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 800, 1));
    }
}
