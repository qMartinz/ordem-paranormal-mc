package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class Consumir extends PlayerPower {
    public Consumir(ResourceLocation id, ParanormalElement element, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements) {
        super(id, false, element, 0, nexRequired, attributesRequired, powerRequirements);
    }
    @Override
    public ItemStack onFinishUseItem(Player player, ItemStack item, ItemStack result, int duration) {
        INexCap cap = player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
        IAbilitiesCap cap2 = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (cap != null && cap2 != null && item.getFoodProperties(player) != null){
            int ep = item.getFoodProperties(player).getNutrition() / (cap2.hasPower(OPPowers.CONSUMIR_2) ? 2 : 4);
            cap.setCurrentEffort(cap.getCurrentEffort() + ep);
        }

        return super.onFinishUseItem(player, item, result, duration);
    }
}
