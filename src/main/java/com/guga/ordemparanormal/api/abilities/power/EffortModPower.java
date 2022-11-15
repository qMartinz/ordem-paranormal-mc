package com.guga.ordemparanormal.api.abilities.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

public class EffortModPower extends PlayerPower{
    private final AttributeModifier modifier;
    public EffortModPower(ResourceLocation id, AttributeModifier modifier, ParanormalElement element, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements) {
        super(id, false, element, 0, nexRequired, attributesRequired, powerRequirements);
        this.modifier = modifier;
    }
    @Override
    public void onTick(Player player, int tickCount) {
        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(cap -> {
            if (!cap.hasEffortModifier(modifier)){
                cap.addEffortModifier(modifier);
            }
        });
    }
}
