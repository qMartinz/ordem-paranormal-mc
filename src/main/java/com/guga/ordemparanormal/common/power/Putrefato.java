package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TieredItem;

import java.util.Random;

public class Putrefato extends PlayerPower {
    public Putrefato() {
        super("putrefato", false, ParanormalElement.MORTE, 0, 1, new int[]{1, 0, 0});
    }
    @Override
    public float onAttack(Player player, float amount, LivingEntity target, DamageSource source) {
        Random rand = new Random();

        player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(cap -> {
            if (rand.nextInt(1, 20) <= (cap.hasPower(OPPowers.PUTREFATO_2) ? 12 : 5) &&
                    !(player.getMainHandItem().getItem() instanceof TieredItem)) {

                target.addEffect(new MobEffectInstance(OPEffects.DECAY.get(), 100));
            }
        });
        return amount;
    }
}
