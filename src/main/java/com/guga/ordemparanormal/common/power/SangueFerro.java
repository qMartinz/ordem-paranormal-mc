package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class SangueFerro extends PlayerPower {
    private static final AttributeModifier MODIFIER = new AttributeModifier(UUID.randomUUID(), "sangue_ferro_mod", 4, AttributeModifier.Operation.ADDITION);
    public SangueFerro() {
        super("sangue_ferro", false, ParanormalElement.SANGUE, 0, 1, new int[]{0, 1, 0});
    }
    @Override
    public void tick(Player player) {
        if (!player.getAttribute(Attributes.MAX_HEALTH).hasModifier(MODIFIER)){
            player.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(MODIFIER);
        }
    }
}
