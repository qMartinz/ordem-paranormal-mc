package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class SangueVisceral extends PlayerPower {
    public SangueVisceral(ResourceLocation id, ParanormalElement element, int nex, int[] attributes, PlayerPower... powers) {
        super(id, false, element, 0, nex, attributes, powers);
    }
    @Override
    public float onHurt(Player player, float amount, @Nullable Entity attacker, DamageSource source) {
        if (player.getHealth() - amount < player.getMaxHealth()/3f){
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 500, 0, false, false));
        }
        return amount;
    }
}
