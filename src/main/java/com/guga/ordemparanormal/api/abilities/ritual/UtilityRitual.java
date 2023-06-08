package com.guga.ordemparanormal.api.abilities.ritual;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public interface UtilityRitual {
    /**
     * Chamado quando o ritual é utilizado em um bloco como alvo
     *
     * @param rayTraceResult o bloco que foi atingido
     * @param world o level em que o ritual foi utilizado
     * @param caster a entidade que utilizou o ritual
     */
    void onUseBlock(BlockHitResult rayTraceResult, Level world, LivingEntity caster, @Nullable ItemStack ritualItem, @Nullable InteractionHand hand);
}
