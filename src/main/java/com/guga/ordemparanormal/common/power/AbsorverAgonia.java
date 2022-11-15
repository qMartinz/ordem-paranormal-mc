package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Random;

public class AbsorverAgonia extends PlayerPower {
    public AbsorverAgonia(ResourceLocation id, ParanormalElement element, int nex, int[] attributes, PlayerPower... powers) {
        super(id, false, element, 0, nex, attributes, powers);
    }
    @Override
    public float onAttack(Player player, float amount, LivingEntity target, DamageSource source) {
        Random rand = new Random();
        if (rand.nextInt(1, 20) <= 6) {
            player.heal(rand.nextInt(2, 5));
        }
        return amount;
    }
}
