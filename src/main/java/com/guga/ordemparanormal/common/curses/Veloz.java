package com.guga.ordemparanormal.common.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.HashMap;
import java.util.Map;

public class Veloz extends AbstractCurse {
    private static final AttributeModifier ATTACK_SPEED_MODIFIER = new AttributeModifier("veloz_modifier", 0.6d, AttributeModifier.Operation.ADDITION);
    public Veloz() {
        super("veloz", ParanormalElement.ENERGIA, CurseCategory.WEAPON, EquipmentSlot.MAINHAND);
    }
    @Override
    public int getMaxUses() {
        return 8;
    }
    @Override
    public Map<AttributeModifier, Attribute> getAttributeModifiers() {
        Map<AttributeModifier, Attribute> modifiers = new HashMap<>();
        modifiers.put(ATTACK_SPEED_MODIFIER, Attributes.ATTACK_SPEED);

        return modifiers;
    }
}
