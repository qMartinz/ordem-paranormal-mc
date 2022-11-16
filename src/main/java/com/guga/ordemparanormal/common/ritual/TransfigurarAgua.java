package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.util.PowerUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;

public class TransfigurarAgua extends AbstractRitual {
    public TransfigurarAgua(ResourceLocation id, ParanormalElement element, int tier, int effortCost, boolean hasEntityTarget,
                            double range, boolean mustHoldIngredient) {
        super(id, element, tier, effortCost, hasEntityTarget, range, mustHoldIngredient);
    }

    public void onUseSelf(HitResult rayTraceResult, Level world, LivingEntity caster, @Nullable ItemStack ritualItem,
            @Nullable InteractionHand hand) {
        rayTraceResult = PowerUtils.rayTrace(caster, this.getRange(), 0, true);

        BlockPos pPos = new BlockPos(rayTraceResult.getLocation());

        BlockState gelo = Blocks.ICE.defaultBlockState();
        // LiquidBlock liquid LiquidBlock
        BlockState liquid = world.getBlockState(pPos);// Blocks.WATER.defaultBlockState();
        // HitResult.Type result = rayTraceResult.getType();

        // if (liquid instanceof BlockLiquid) {
        world.setBlockAndUpdate(pPos.offset(-1, 0, 1), gelo);
        world.setBlockAndUpdate(pPos.offset(0, 0, 1), gelo);
        world.setBlockAndUpdate(pPos.offset(1, 0, 1), gelo);
        world.setBlockAndUpdate(pPos.offset(-1, 0, 0), gelo);
        world.setBlockAndUpdate(pPos.offset(0, 0, 0), gelo);
        world.setBlockAndUpdate(pPos.offset(1, 0, 0), gelo);
        world.setBlockAndUpdate(pPos.offset(-1, 0, -1), gelo);
        world.setBlockAndUpdate(pPos.offset(-0, 0, -1), gelo);
        world.setBlockAndUpdate(pPos.offset(1, 0, -1), gelo);
        // }
    }
}
