package com.guga.ordemparanormal.api.abilities.ritual;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;

public interface DefensiveRitual {
    /**
     * Chamado quando o ritual não possui alvo específico (não é uma entidade ou um bloco)
     *
     * @param world o level em que o ritual foi utilizado
     * @param caster a entidade que utilizou o ritual
     */
    void onUseSelf(HitResult rayTraceResult, Level world, LivingEntity caster, @Nullable ItemStack ritualItem, @Nullable InteractionHand hand);
}
