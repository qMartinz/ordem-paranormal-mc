package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class LembrarDaMorte extends PlayerPower {
    public LembrarDaMorte(String id, ParanormalElement element, int nex, int[] attributes, PlayerPower... powers) {
        super(id, false, element, 0, nex, attributes, powers);
    }
    @Override
    public void onTick(Player player, int tickCount) {
        List<LivingEntity> targets = player.level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(5d, 5d, 5d));

        if (targets.stream().anyMatch(e -> e.getMobType() == MobType.UNDEAD) && !player.hasEffect(MobEffects.MOVEMENT_SPEED)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60));
        }
    }
}
