package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class SangueVisceral extends PlayerPower {
    public SangueVisceral() {
        super("sangue_visceral", false, ParanormalElement.SANGUE, 0, 5, new int[]{2, 1, 0}, OPPowers.PUNHO_ENRAIVECIDO, OPPowers.ADRENALINA);
    }
    @Override
    public void hurt(Player player, LivingEntity attacker, float amount) {
        if (player.getHealth() - amount < player.getMaxHealth()/3f){
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 500, 0, false, false));
        }
    }
}
