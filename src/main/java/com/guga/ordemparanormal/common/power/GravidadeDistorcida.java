package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class GravidadeDistorcida extends PlayerPower {
    public GravidadeDistorcida(ResourceLocation id, boolean isActivePower, ParanormalElement element, int effortCost, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements) {
        super(id, isActivePower, element, effortCost, nexRequired, attributesRequired, powerRequirements);
    }
    @Override
    public boolean canEquip(Player player) {
        IAbilitiesCap cap = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (cap == null) return false;

        return !cap.hasPower(OPPowers.GRAVIDADE_DISTORCIDA_2);
    }
    @Override
    public boolean canUse(Player player) {
        IAbilitiesCap cap = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (cap == null) return false;

        return super.canUse(player) && !cap.hasPower(OPPowers.GRAVIDADE_DISTORCIDA_2);
    }
    @Override
    public void onUse(Player player) {
        MobEffectInstance effect = new MobEffectInstance(OPEffects.DISTORTED_GRAVITY.get(), 600, 0);
        player.addEffect(effect);
    }
}
