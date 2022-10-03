package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class Adrenalina extends PlayerPower {
    public Adrenalina() {
        super("adrenalina", false, ParanormalElement.SANGUE, 0, 3, new int[]{1, 1, 0});
    }
    @Override
    public void onHurt(Player player, LivingEntity attacker, float amount) {
        player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(cap -> {
            if (player.getHealth() - amount < player.getMaxHealth()/3f && !cap.hasPower(OPPowers.ADRENALINA_2)){
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 500, 0, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 500, 0, false, false));
            }
        });
    }
}
