package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class PotencialAprimorado extends PlayerPower {
    private static final AttributeModifier MODIFIER = new AttributeModifier(UUID.randomUUID(), "potencial_aprimorado_mod", 2, AttributeModifier.Operation.ADDITION);
    public PotencialAprimorado() {
        super("potencial_aprimorado", false, ParanormalElement.MORTE, 0, 1, new int[]{0, 0, 1});
    }
    @Override
    public void onTick(Player player) {
        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(cap -> {
            if (!cap.hasEffortModifier(MODIFIER)){
                cap.addEffortModifier(MODIFIER);
            }
        });
    }
}
