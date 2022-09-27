package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Random;

public class AbsorverAgonia extends PlayerPower {
    public AbsorverAgonia() {
        super("absorver_agonia", false, ParanormalElement.SANGUE, 0, 7, new int[]{0, 4, 0}, OPPowers.FLAGELO);
    }
    @Override
    public void attack(Player player, LivingEntity target) {
        Random rand = new Random();
        if (rand.nextInt(1, 8) == 2){
            player.heal(rand.nextInt(2, 5));
        }
    }
}
