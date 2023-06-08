package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class PresencaAvassaladora2 extends PlayerPower {
    public PresencaAvassaladora2(ResourceLocation id, ParanormalElement element, int effortCost, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements) {
        super(id, true, element, effortCost, nexRequired, attributesRequired, powerRequirements);
    }
    @Override
    public void onUse(Player player) {
        player.level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(6d)).forEach(living -> {
            if (living != player) living.addEffect(new MobEffectInstance(
                    OPEffects.WEAKENED_MIND.get(), 240, 0
            ));
        });
    }
}
