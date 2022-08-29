package com.guga.ordemparanormal.common.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseCategory;
import com.guga.ordemparanormal.api.curses.CurseHelper;
import com.guga.ordemparanormal.core.registry.OPParticles;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public class Amaldicoada extends AbstractCurse {
    public Amaldicoada() {
        super("amaldicoada", ParanormalElement.KNOWLEDGE, CurseCategory.WEAPON, true, EquipmentSlot.MAINHAND);
    }
    public int getMaxTicks() {
        return 200;
    }
    @Override
    public int getDamageBonus() {
        return 2;
    }
    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget) {
        Random random = new Random();
        for (int i = 0; i < getDamageBonus(); i++) {
            pTarget.level.addParticle(OPParticles.SIGILOS_PARTICLE.get(),
                    pTarget.getX() + random.nextDouble(-0.5d, 0.5d),
                    pTarget.getEyeY() + random.nextDouble(-0.5d, 0.5d),
                    pTarget.getZ() + random.nextDouble(-0.5d, 0.5d),
                    0D, 0D, 0D);
        }
    }
}
