package com.guga.ordemparanormal.common.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseCategory;
import com.guga.ordemparanormal.core.registry.OPParticles;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

public class Amaldicoada extends AbstractCurse {
    public Amaldicoada(ResourceLocation id, ParanormalElement element, CurseCategory category, EquipmentSlot... slots) {
        super(id, element, category, slots);
    }
    public int getMaxUses() {
        return 8;
    }
    @Override
    public int getDamageBonus() {
        return 2;
    }
    @Override
    public float doPostAttack(ItemStack pStack, LivingEntity pAttacker, LivingEntity pTarget, float amount, DamageSource source) {
        Random random = new Random();
        for (int i = 0; i < getDamageBonus(); i++) {
            pTarget.level.addParticle(OPParticles.SIGILOS_PARTICLE.get(),
                    pTarget.getX() + random.nextDouble(-0.5d, 0.5d),
                    pTarget.getEyeY() + random.nextDouble(-0.5d, 0.5d),
                    pTarget.getZ() + random.nextDouble(-0.5d, 0.5d),
                    0D, 0D, 0D);
        }

        return super.doPostAttack(pStack, pAttacker, pTarget, amount, source);
    }
}
