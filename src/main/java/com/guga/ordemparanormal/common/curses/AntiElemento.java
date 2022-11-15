package com.guga.ordemparanormal.common.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseCategory;
import com.guga.ordemparanormal.common.entity.ParanormalCreature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class AntiElemento extends AbstractCurse {
    private final ParanormalElement effectiveElement;
    public AntiElemento(ResourceLocation id, ParanormalElement element, CurseCategory category, ParanormalElement effectiveElement, EquipmentSlot... slots) {
        super(id, element, category, slots);
        this.effectiveElement = effectiveElement;
    }
    @Override
    public float doPostAttack(ItemStack pStack, LivingEntity pAttacker, LivingEntity pTarget, float amount, DamageSource source) {
        if (pTarget instanceof ParanormalCreature creature && creature.getElement() == effectiveElement){
            amount += 8;
        }
        return super.doPostAttack(pStack, pAttacker, pTarget, amount, source);
    }
}
