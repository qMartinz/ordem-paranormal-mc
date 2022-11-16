package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

import javax.annotation.Nullable;

public class Perturbacao extends AbstractRitual {
    public Perturbacao(ResourceLocation id, ParanormalElement element, int tier, int effortCost,
            boolean hasEntityTarget, double range, boolean mustHoldIngredient) {
        super(id, element, tier, effortCost, hasEntityTarget, range, mustHoldIngredient);
    }

    @Override
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster,
            @Nullable ItemStack ritualItem, @Nullable InteractionHand hand) {
        LivingEntity target = (LivingEntity) rayTraceResult.getEntity();
        if (target instanceof Player) {
            MobEffectInstance stop = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 100, false, false, false);
            target.addEffect(stop);
            MobEffectInstance jump = new MobEffectInstance(MobEffects.JUMP, 100, -6, false, false, false);
            target.addEffect(jump);
        } else {
            MobEffectInstance stop = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 100, false, false, false);
            target.addEffect(stop);
            MobEffectInstance jump = new MobEffectInstance(MobEffects.JUMP, 200, -6, false, false, false);
            target.addEffect(jump);
        }
    }
}