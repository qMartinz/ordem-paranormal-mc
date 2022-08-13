package com.guga.ordemparanormal.common.effects;

import com.guga.ordemparanormal.api.capabilities.data.ParanormalEffectsProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class LifeAbsorptionEffect extends MobEffect {
    public LifeAbsorptionEffect(MobEffectCategory p_19414_, int p_19415_) {
        super(p_19414_, p_19415_);
    }

    @Override
    public void addAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        pLivingEntity.getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).ifPresent(effects -> effects.setDeathHealthPoints((int) (effects.getDeathHealthPoints() + Math.min(4 * (pAmplifier + 1), pLivingEntity.getMaxHealth()))));
        super.addAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        pLivingEntity.getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).ifPresent(effects -> effects.setDeathHealthPoints((int) (effects.getDeathHealthPoints() - Math.min(4 * (pAmplifier + 1), pLivingEntity.getMaxHealth()))));
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }
}
