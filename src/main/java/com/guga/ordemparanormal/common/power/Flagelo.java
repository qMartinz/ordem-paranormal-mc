package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;

public class Flagelo extends PlayerPower {
    public Flagelo(ResourceLocation id, ParanormalElement element, int nex, int[] attributes, PlayerPower... powers) {
        super(id, true, element, 0, nex, attributes, powers);
    }
    @Override
    public void onUse(Player player) {
        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(cap -> {
            player.hurt(new DamageSource("powerFlagelo"), 8);
            cap.setCurrentEffort(cap.getCurrentEffort() + 2);
        });
    }
}
