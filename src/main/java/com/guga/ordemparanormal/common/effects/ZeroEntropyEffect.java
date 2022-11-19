package com.guga.ordemparanormal.common.effects;

import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ZeroEntropyEffect extends OPEffects.ParanormalEffect {
    public ZeroEntropyEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity.getDeltaMovement().y > 0){
            pLivingEntity.setDeltaMovement(
                    pLivingEntity.getDeltaMovement().x,
                    -1,
                    pLivingEntity.getDeltaMovement().z);
        }
    }
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
