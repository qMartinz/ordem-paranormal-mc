package com.guga.ordemparanormal.common.effects;

import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class DistortedGravityEffect extends OPEffects.ParanormalEffect {
    public DistortedGravityEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity.getDeltaMovement().y != 0){
            double gravity = 0.08d * ((40d / (pAmplifier + 1d)) / 100d);
            double resistance = Mth.clamp(0.90d + (0.02 * pAmplifier), 0.1, 0.98);
            double gravityValue = ((40d / (pAmplifier + 1d)) / 100d);

            pLivingEntity.setDeltaMovement(
                    pLivingEntity.getDeltaMovement().x,
                    (((pLivingEntity.getDeltaMovement().y / 0.98) + 0.08) - gravity) * resistance,
                    pLivingEntity.getDeltaMovement().z);

            pLivingEntity.fallDistance = (float) (pLivingEntity.getDeltaMovement().y * gravityValue);
        }
    }
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
