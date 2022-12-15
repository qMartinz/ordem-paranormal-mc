package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class Sabedoria extends PlayerPower {
    public Sabedoria(ResourceLocation id, ParanormalElement element, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements) {
        super(id, false, element, 0, nexRequired, attributesRequired, powerRequirements);
    }
    @Override
    public int onXPChange(Player player, int exp) {
        IAbilitiesCap cap = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (exp > 0) exp += exp * (cap.hasPower(OPPowers.SABEDORIA_2) ? 0.5f : 0.25f);
        return super.onXPChange(player, exp);
    }
    @Override
    public int onXPLevelChange(Player player, int levels) {
        IAbilitiesCap cap = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);

        if (levels < 0) levels -= levels * (cap.hasPower(OPPowers.SABEDORIA_2) ? 0.5f : 0.25f);
        return super.onXPLevelChange(player, levels);
    }
}
