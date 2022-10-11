package com.guga.ordemparanormal.api.abilities.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class MobEffectPower extends PlayerPower{
    private final MobEffectInstance effect;
    public MobEffectPower(String id, MobEffectInstance effect, ParanormalElement element, int effortCost, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements) {
        super(id, true, element, effortCost, nexRequired, attributesRequired, powerRequirements);
        this.effect = effect;
    }
    @Override
    public void onUse(Player player) {
        player.addEffect(effect);
    }
}
