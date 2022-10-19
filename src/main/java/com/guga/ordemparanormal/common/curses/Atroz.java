package com.guga.ordemparanormal.common.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseCategory;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class Atroz extends AbstractCurse {
    public Atroz() {
        super("atroz", ParanormalElement.SANGUE, CurseCategory.WEAPON, EquipmentSlot.MAINHAND);
    }
    @Override
    public int getMaxUses() {
        return 8;
    }
    @Override
    public int getDamageBonus() {
        return 2;
    }
    @Override
    public float doPostAttack(ItemStack pStack, LivingEntity pAttacker, LivingEntity pTarget, float amount, DamageSource source) {
        for (int i = 0; i < getDamageBonus(); i++) {
            pTarget.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()),
                    pTarget.getX(), pTarget.getEyeY(), pTarget.getZ(),
                    0D, 0D, 0D);
        }
        return super.doPostAttack(pStack, pAttacker, pTarget, amount, source);
    }
}
