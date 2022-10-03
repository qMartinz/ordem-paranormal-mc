package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class Adrenalina2 extends PlayerPower {
    public Adrenalina2() {
        super("adrenalina_2", false, ParanormalElement.SANGUE, 0, 10, new int[]{2, 2, 0}, OPPowers.ADRENALINA, OPPowers.AFINIDADE_SANGUE);
    }
    @Override
    public void onHurt(Player player, LivingEntity attacker, float amount) {
        if (player.getHealth() - amount < player.getMaxHealth()/3f){
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 700, 1, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 700, 1, false, false));
        }
    }
}
