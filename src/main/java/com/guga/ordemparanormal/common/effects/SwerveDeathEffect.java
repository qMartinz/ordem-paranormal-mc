package com.guga.ordemparanormal.common.effects;

import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public class SwerveDeathEffect extends OPEffects.ParanormalEffect{
    public SwerveDeathEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public float onHurt(LivingEntity livingEntity, @Nullable Entity attacker, float amount, DamageSource source) {
        if (amount > livingEntity.getHealth() && livingEntity.getHealth() > 1f){
            livingEntity.removeEffect(OPEffects.SWERVE_DEATH.get());
            return livingEntity.getHealth() - 1f;
        }
        return amount;
    }
}
