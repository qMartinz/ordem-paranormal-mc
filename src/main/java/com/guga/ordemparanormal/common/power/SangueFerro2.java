package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class SangueFerro2 extends PlayerPower {
    private static final AttributeModifier MODIFIER = new AttributeModifier(UUID.randomUUID(), "sangue_ferro_2_mod", 6, AttributeModifier.Operation.ADDITION);
    public SangueFerro2() {
        super("sangue_ferro_2", false, ParanormalElement.SANGUE, 0, 10, new int[]{0, 5, 0}, OPPowers.SANGUE_FERRO, OPPowers.AFINIDADE_SANGUE);
    }
    @Override
    public void onTick(Player player) {
        if (!player.getAttribute(Attributes.MAX_HEALTH).hasModifier(MODIFIER)){
            player.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(MODIFIER);
        }
    }
}
