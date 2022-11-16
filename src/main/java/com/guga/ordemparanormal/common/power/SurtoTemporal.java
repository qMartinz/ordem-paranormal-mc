package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class SurtoTemporal extends PlayerPower {
    public SurtoTemporal(ResourceLocation id, boolean isActivePower, ParanormalElement element, int effortCost, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements) {
        super(id, isActivePower, element, effortCost, nexRequired, attributesRequired, powerRequirements);
    }
    @Override
    public void onUse(Player player) {
        MobEffectInstance effect = new MobEffectInstance(OPEffects.TEMPORAL_SURGE.get(), 600, 0);
        player.addEffect(effect);
    }
}
