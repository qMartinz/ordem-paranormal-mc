package com.guga.ordemparanormal.common.ritual;

import javax.annotation.Nullable;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.paranormaldamage.EntityParanormalDamageSource;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class Inexistir extends AbstractRitual {
    public Inexistir() {
        super("inexistir", ParanormalElement.CONHECIMENTO, 4, 8, true, 3D, true);
    }

    @Override
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster,
            @Nullable ItemStack ritualItem, @Nullable InteractionHand hand) {
        LivingEntity target = (LivingEntity) rayTraceResult.getEntity();

        if (!(target instanceof Player)) {
            target.hurt(
                    EntityParanormalDamageSource.ritualAttack(target, this),
                    60);
        }
    }
}
