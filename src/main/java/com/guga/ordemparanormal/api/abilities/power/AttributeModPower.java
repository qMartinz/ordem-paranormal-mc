package com.guga.ordemparanormal.api.abilities.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

public class AttributeModPower extends PlayerPower{
    private final Attribute attribute;
    private final AttributeModifier modifier;
    public AttributeModPower(String id, Attribute attribute, AttributeModifier modifier, ParanormalElement element, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements) {
        super(id, false, element, 0, nexRequired, attributesRequired, powerRequirements);
        this.attribute = attribute;
        this.modifier = modifier;
    }
    @Override
    public void onTick(Player player) {
        if (!player.getAttribute(attribute).hasModifier(modifier)){
            player.getAttribute(attribute).addTransientModifier(modifier);
        }
    }
}
