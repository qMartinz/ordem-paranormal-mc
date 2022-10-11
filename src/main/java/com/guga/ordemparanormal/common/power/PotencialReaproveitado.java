package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Random;

public class PotencialReaproveitado extends PlayerPower {
    public PotencialReaproveitado() {
        super("potencial_reaproveitado", false, ParanormalElement.MORTE, 0, 1, new int[]{0, 1, 0});
    }
    public void onHurt(Player player, LivingEntity attacker, float amount) {
        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(cap -> {
            Random rand = new Random();
            if (rand.nextInt(1, 5) == 2){
                cap.setCurrentEffort(cap.getCurrentEffort() + 1);
            }
        });
    }
}
