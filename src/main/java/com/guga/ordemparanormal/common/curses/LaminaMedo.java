package com.guga.ordemparanormal.common.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;

public class LaminaMedo extends AbstractCurse {
    public LaminaMedo(ResourceLocation id, ParanormalElement element, CurseCategory category, EquipmentSlot... slots) {
        super(id, element, category, slots);
    }
    @Override
    public int getMaxUses() {
        return 1;
    }
    @Override
    public int getDamageBonus() {
        return 60;
    }
}
