package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import net.minecraft.world.entity.player.Player;

public class Flagelo extends PlayerPower {
    public Flagelo() {
        super("flagelo", true, ParanormalElement.SANGUE, 0, 5, new int[]{0, 3, 0});
    }
    @Override
    public void use(Player player) {
        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(cap -> {
            if (player.getHealth() < 8 && !canUse(player)) return;
            player.setHealth(player.getHealth() - 8);
            cap.setCurrentEffort(cap.getCurrentEffort() + 2);
        });
    }
}
