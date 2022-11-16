package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class Adrenalina extends PlayerPower {
    public Adrenalina(ResourceLocation id, ParanormalElement element, int nex, int[] attributes, PlayerPower... powers) {
        super(id, false, element, 0, nex, attributes, powers);
    }
    @Override
    public float onHurt(Player player, float amount, @Nullable Entity attacker, DamageSource source) {
        player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(cap -> {
            if (player.getHealth() - amount < player.getMaxHealth()/3f && !cap.hasPower(OPPowers.ADRENALINA_2)){
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 500, 0, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 500, 0, false, false));
            } else if (player.getHealth() - amount < player.getMaxHealth()/3f && cap.hasPower(OPPowers.ADRENALINA_2)){
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 700, 1, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 700, 1, false, false));
            }
        });
        return amount;
    }
}
