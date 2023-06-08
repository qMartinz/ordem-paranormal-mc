package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.abilities.ritual.UtilityRitual;
import com.guga.ordemparanormal.core.registry.OPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class Purgatorio extends AbstractRitual implements UtilityRitual {
    public Purgatorio(ResourceLocation id, ParanormalElement element, int tier, int effortCost, double range, boolean mustHoldIngredient) {
        super(id, element, tier, effortCost, range, mustHoldIngredient);
    }
    @Override
    public void onUseBlock(BlockHitResult rayTraceResult, Level world, LivingEntity caster, @Nullable ItemStack ritualItem, @Nullable InteractionHand hand) {
        BlockPos blockPos = rayTraceResult.getBlockPos().relative(rayTraceResult.getDirection());
        BlockState purgatorio = OPBlocks.PURGATORIO_BLOCK.get().defaultBlockState();

        AABB area = new AABB(blockPos).inflate(2);
        // TODO not working
        for (BlockPos pos : BlockPos.betweenClosedStream(area).toList()){
            if (purgatorio.canSurvive(world, pos)) {
                world.setBlockAndUpdate(pos, purgatorio);
            }
        }
    }
}
