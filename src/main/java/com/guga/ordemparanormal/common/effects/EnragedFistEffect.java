package com.guga.ordemparanormal.common.effects;

import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.TieredItem;

public class EnragedFistEffect extends OPEffects.ParanormalEffect {
    public EnragedFistEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public float onAttack(LivingEntity livingEntity, LivingEntity target, float amount, DamageSource source) {
        if (livingEntity.getMainHandItem().getItem() instanceof TieredItem) return amount;
        return amount + 3
                * (ParanormalDamageSource.isEntityWeakTo(target, OPPowers.PUNHO_ENRAIVECIDO.getElement().getDamage()) ? 2f : 1f)
                / (ParanormalDamageSource.isEntityResistant(target, OPPowers.PUNHO_ENRAIVECIDO.getElement().getDamage()) ? 2f : 1f);
    }
}
