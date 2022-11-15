package com.guga.ordemparanormal.common.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseCategory;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class Lancinante extends AbstractCurse {
    public Lancinante(ResourceLocation id, ParanormalElement element, CurseCategory category, EquipmentSlot... slots) {
        super(id, element, category, slots);
    }
    @Override
    public float doPostAttack(ItemStack pStack, LivingEntity pAttacker, LivingEntity pTarget, float amount, DamageSource source) {
        if (pTarget.getHealth() > pTarget.getMaxHealth() / 1.2){
            amount += 5 * (ParanormalDamageSource.isEntityWeakTo(pTarget, this.getElement().getDamage()) ? 2f : 1f)
                    / (ParanormalDamageSource.isEntityResistant(pTarget, this.getElement().getDamage()) ? 2f : 1f);
        }
        return super.doPostAttack(pStack, pAttacker, pTarget, amount, source);
    }
}
