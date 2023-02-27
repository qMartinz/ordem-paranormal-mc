package com.guga.ordemparanormal.api.abilities.ritual;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

import javax.annotation.Nullable;

public interface OffensiveRitual {
    /**
     * Chamado quando o ritual é utilizado em uma entidade como alvo
     *
     * @param rayTraceResult a entidade que foi atingido
     * @param world o level em que o ritual foi utilizado
     * @param caster a entidade que utilizou o ritual
     */
    void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster, @Nullable ItemStack ritualItem, @Nullable InteractionHand hand);
}
