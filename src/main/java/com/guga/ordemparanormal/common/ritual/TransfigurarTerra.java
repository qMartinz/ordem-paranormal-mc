package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.abilities.ritual.UtilityRitual;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class TransfigurarTerra extends AbstractRitual implements UtilityRitual {
    public TransfigurarTerra(ResourceLocation id, ParanormalElement element, int tier, int effortCost,
                             double range, boolean mustHoldIngredient) {
        super(id, element, tier, effortCost, range, mustHoldIngredient);
    }
    @Override
    public void onUseBlock(BlockHitResult rayTraceResult, Level world, LivingEntity caster,
            @Nullable ItemStack ritualItem, @Nullable InteractionHand hand) {

        BlockPos blockPos = rayTraceResult.getBlockPos();
        // 200
        BlockState lama = Blocks.SOUL_SAND.defaultBlockState();
        BlockState old = world.getBlockState(blockPos);

        //
        // BlockState iblockstate = world.getBlockState(blockPos);
        // Block block = iblockstate.getBlock();
        if (old.getBlock() == Blocks.GRASS_BLOCK || old.getBlock() == Blocks.DIRT || old.getBlock() == Blocks.PODZOL
                || old.getBlock() == Blocks.MYCELIUM || old.getBlock() == Blocks.SAND) {
            world.setBlockAndUpdate(blockPos, lama);
        }
    }
}