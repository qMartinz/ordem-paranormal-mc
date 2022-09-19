package com.guga.ordemparanormal.common.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseCategory;
import com.guga.ordemparanormal.api.curses.CurseHelper;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class Decadente extends AbstractCurse {
    public Decadente() {
        super("decadente", ParanormalElement.MORTE, CurseCategory.WEAPON, true, EquipmentSlot.MAINHAND);
    }
    @Override
    public int getMaxTicks() {
        return 100;
    }
    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget) {
        MobEffectInstance effect = new MobEffectInstance(OPEffects.DECAY.get(), 200, 1, false, false);
        if (pTarget instanceof LivingEntity target) target.addEffect(effect);

        for (EquipmentSlot slot : getSlots()){
            if (slot == EquipmentSlot.MAINHAND){
                CurseHelper.removeCurse(pAttacker.getItemBySlot(slot), this);
            }
        }
    }
}
