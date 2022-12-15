package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class Carismatico extends PlayerPower {
    public Carismatico(ResourceLocation id, ParanormalElement element, int effortCost, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements) {
        super(id, true, element, effortCost, nexRequired, attributesRequired, powerRequirements);
    }
    @Override
    public void onUse(Player player) {
        player.addEffect(new MobEffectInstance(OPEffects.CHARISMATIC.get(), 200, 0, false, false));
    }
}
