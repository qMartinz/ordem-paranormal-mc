package com.guga.ordemparanormal.api.abilities.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.HashMap;
import java.util.Map;

public class AttributeModPower extends PlayerPower{
    private final Map<Attribute, AttributeModifier> attributeMods = new HashMap<>();
    public AttributeModPower(String id, ParanormalElement element, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements) {
        super(id, false, element, 0, nexRequired, attributesRequired, powerRequirements);
    }
    public Map<Attribute, AttributeModifier> getAttributeModifiers() {
        return attributeMods;
    }
    public AttributeModPower modifier(Attribute attribute, AttributeModifier modifier){
        this.attributeMods.put(attribute, modifier);
        return this;
    }
}
