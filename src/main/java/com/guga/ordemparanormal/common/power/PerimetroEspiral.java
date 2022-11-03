package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.common.entity.ParanormalCreature;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class PerimetroEspiral extends PlayerPower {
    public PerimetroEspiral(String id, ParanormalElement element, int cost, int nex, int[] attributes, PlayerPower... powers) {
        super(id, true, element, cost, nex, attributes, powers);
    }
    @Override
    public void onUse(Player player) {
        List<LivingEntity> targets = player.level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(4d, 4d, 4d));
        if (targets.size() > 5) targets = targets.subList(0, 4);

        targets.removeIf(entity -> entity == player || (entity instanceof ParanormalCreature creature && creature.getMainElement() == ParanormalElement.ENERGIA));

        targets.forEach(target -> {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600));
        });

        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600));
    }
}
