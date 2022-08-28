package com.guga.ordemparanormal.common.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseCategory;
import com.guga.ordemparanormal.api.curses.CurseHelper;
import com.mojang.math.Vector3f;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public class Decadente extends AbstractCurse {
    public Decadente() {
        super("decadente", ParanormalElement.DEATH, CurseCategory.WEAPON, true, EquipmentSlot.MAINHAND);
    }
    @Override
    public int getMaxTicks() {
        return 100;
    }
    public int getDamageBonus() {
        return 10;
    }
    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget) {
        Random random = new Random();
        for (int i = 0; i < getDamageBonus(); i++) {
            pTarget.level.addParticle(new DustParticleOptions(new Vector3f(0.25f, 0.25f, 0.25f), 0.7f),
                    pTarget.getX() + random.nextDouble(-0.5d, 0.5d),
                    pTarget.getEyeY() + random.nextDouble(-0.5d, 0.5d),
                    pTarget.getZ() + random.nextDouble(-0.5d, 0.5d),
                    random.nextDouble(0.5d), random.nextDouble(0.5d), random.nextDouble(0.5d));
        }
        for (EquipmentSlot slot : getSlots()){
            if (slot == EquipmentSlot.MAINHAND){
                CurseHelper.removeCurse(pAttacker.getItemBySlot(slot), this);
            }
        }
    }
}
