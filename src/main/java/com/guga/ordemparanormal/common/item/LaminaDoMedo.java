package com.guga.ordemparanormal.common.item;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.paranormaldamage.EntityParanormalDamageSource;
import com.guga.ordemparanormal.core.registry.OPRituals;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class LaminaDoMedo extends RitualItem {
    public LaminaDoMedo() {
        super(OPRituals.LAMINA_MEDO);
    }
    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pStack.is(this) && pStack.getOrCreateTag().getBoolean("active")){
            pTarget.hurt((new EntityParanormalDamageSource("ritualLaminaMedo", pAttacker)).setElement(ParanormalElement.MEDO),
                    60);

            pStack.getOrCreateTag().putBoolean("active", false);
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
    @Override
    public boolean canStoreInBook() {
        return false;
    }
}
