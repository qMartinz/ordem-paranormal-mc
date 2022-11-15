package com.guga.ordemparanormal.common.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.HashMap;
import java.util.Map;

public class Lepida extends AbstractCurse {
    private static final AttributeModifier MOVEMENT_SPEED_MODIFIER = new AttributeModifier("lepida_modifier", 0.25d, AttributeModifier.Operation.ADDITION);
    public Lepida(ResourceLocation id, ParanormalElement element, CurseCategory category, EquipmentSlot... slots) {
        super(id, element, category, slots);
    }
    @Override
    public Map<AttributeModifier, Attribute> getAttributeModifiers() {
        Map<AttributeModifier, Attribute> modifiers = new HashMap<>();
        modifiers.put(MOVEMENT_SPEED_MODIFIER, Attributes.MOVEMENT_SPEED);
        return modifiers;
    }
}
