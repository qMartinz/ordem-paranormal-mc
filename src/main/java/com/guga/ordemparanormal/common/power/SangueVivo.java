package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class SangueVivo extends PlayerPower {
    public SangueVivo() {
        super("sangue_vivo", false, ParanormalElement.SANGUE, 0, 5, new int[]{0, 2, 0}, OPPowers.SANGUE_FERRO, OPPowers.ADRENALINA);
    }
    @Override
    public float onHurt(Player player, float amount, @Nullable Entity attacker, DamageSource source) {
        if (player.getHealth() - amount < player.getMaxHealth()/3f){
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 500, 0, false, false));
        }
        return amount;
    }
}
