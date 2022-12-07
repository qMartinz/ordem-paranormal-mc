package com.guga.ordemparanormal.common.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseCategory;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class Voltaica extends AbstractCurse {
    public Voltaica(ResourceLocation id, ParanormalElement element, CurseCategory category, EquipmentSlot... slots) {
        super(id, element, category, slots);
    }
    @Override
    public float doPostHurt(ItemStack pStack, LivingEntity pTarget, @Nullable Entity pAttacker, float amount, DamageSource source) {
        if (source instanceof ParanormalDamageSource paranormaldamage &&
                paranormaldamage.element == ParanormalElement.ENERGIA) amount -= Math.min(13, amount);
        return super.doPostHurt(pStack, pTarget, pAttacker, amount, source);
    }
}
