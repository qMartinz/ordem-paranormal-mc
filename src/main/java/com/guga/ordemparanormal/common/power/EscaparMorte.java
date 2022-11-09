package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class EscaparMorte extends PlayerPower {
    public EscaparMorte(String id, boolean isActivePower, ParanormalElement element, int effortCost, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements) {
        super(id, isActivePower, element, effortCost, nexRequired, attributesRequired, powerRequirements);
    }
    @Override
    public void onUse(Player player) {
        MobEffectInstance effect = new MobEffectInstance(OPEffects.SWERVE_DEATH.get(), 800, 0);
        player.addEffect(effect);
    }
}
