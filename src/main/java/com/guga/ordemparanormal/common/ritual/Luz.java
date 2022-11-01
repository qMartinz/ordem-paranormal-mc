package com.guga.ordemparanormal.common.ritual;

import javax.annotation.Nullable;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.registry.OPBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class Luz extends AbstractRitual {
    public Luz() {
        super("luz", ParanormalElement.ENERGIA, 1, 1, false, 5D, true);
    }

    @Override
    public void onUseBlock(BlockHitResult rayTraceResult, Level world, LivingEntity caster,
            @Nullable ItemStack ritualItem, @Nullable InteractionHand hand) {

        BlockPos blockPos = rayTraceResult.getBlockPos().relative(rayTraceResult.getDirection());
        // 200
        BlockState luz = OPBlocks.LUZ_BLOCK.get().defaultBlockState();

        //
        // BlockState iblockstate = world.getBlockState(blockPos);
        // Block block = iblockstate.getBlock();

        world.setBlockAndUpdate(blockPos, luz);
    }
}