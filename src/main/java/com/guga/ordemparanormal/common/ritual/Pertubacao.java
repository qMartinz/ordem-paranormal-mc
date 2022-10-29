package com.guga.ordemparanormal.common.ritual;

import javax.annotation.Nullable;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class Pertubacao extends AbstractRitual {
    public Pertubacao() {
        super("pertubacao", ParanormalElement.CONHECIMENTO, 1, 1, true, 3D, true);
    }

    @Override
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster,
            @Nullable ItemStack ritualItem, @Nullable InteractionHand hand) {
        LivingEntity target = (LivingEntity) rayTraceResult.getEntity();

        MobEffectInstance stop = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 100, false, false);
        target.addEffect(stop);
        MobEffectInstance jump = new MobEffectInstance(MobEffects.JUMP, 200, -6, false, false);
        target.addEffect(jump);
    }
}