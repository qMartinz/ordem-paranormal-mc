package com.guga.ordemparanormal.common.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseCategory;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;

public class Atroz extends AbstractCurse {
    public Atroz() {
        super("atroz", ParanormalElement.BLOOD, CurseCategory.WEAPON, true, EquipmentSlot.MAINHAND);
    }
    @Override
    public int getMaxTicks() {
        return 200;
    }
    @Override
    public int getDamageBonus() {
        return 2;
    }
    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget) {
        for (int i = 0; i < getDamageBonus(); i++) {
            pTarget.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()),
                    pTarget.getX(), pTarget.getEyeY(), pTarget.getZ(),
                    0D, 0D, 0D);
        }
    }
}
