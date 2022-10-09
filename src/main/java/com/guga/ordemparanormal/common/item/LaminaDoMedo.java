package com.guga.ordemparanormal.common.item;

import com.guga.ordemparanormal.api.ElementDamage;
import com.guga.ordemparanormal.core.registry.OPRituals;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class LaminaDoMedo extends RitualItem {
    public LaminaDoMedo() {
        super(OPRituals.LAMINA_MEDO);
    }
    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pStack.is(this) && pStack.getOrCreateTag().getBoolean("active")){
            pTarget.hurt(new EntityDamageSource("laminaMedo", pAttacker),
                    60 / (ElementDamage.isEntityResistant(pTarget, ElementDamage.DANO_MEDO) ? 2f : 1f));

            pStack.getOrCreateTag().putBoolean("active", false);
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
    @Override
    public boolean canStoreInBook() {
        return false;
    }
}
