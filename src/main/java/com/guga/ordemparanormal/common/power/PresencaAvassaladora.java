package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.util.PowerUtils;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class PresencaAvassaladora extends PlayerPower {
    public PresencaAvassaladora(ResourceLocation id, ParanormalElement element, int effortCost, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements) {
        super(id, true, element, effortCost, nexRequired, attributesRequired, powerRequirements);
    }
    @Override
    public void onUse(Player player) {
        HitResult result = PowerUtils.rayTrace(player, 5d, 0, false);
        if (result instanceof EntityHitResult entityHitResult){
            if (entityHitResult.getEntity() instanceof LivingEntity living) living.addEffect(new MobEffectInstance(
                    OPEffects.WEAKENED_MIND.get(), 240, 0
            ));
        }
    }
}
