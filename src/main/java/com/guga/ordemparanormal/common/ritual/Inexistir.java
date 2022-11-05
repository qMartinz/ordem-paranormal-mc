package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.paranormaldamage.EntityParanormalDamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

import javax.annotation.Nullable;

public class Inexistir extends AbstractRitual {
    public Inexistir(String id, ParanormalElement element, int tier, int effortCost, boolean hasEntityTarget, double range, boolean mustHoldIngredient) {
        super(id, element, tier, effortCost, hasEntityTarget, range, mustHoldIngredient);
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
