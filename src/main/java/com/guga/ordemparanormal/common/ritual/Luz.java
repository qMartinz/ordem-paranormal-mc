package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.abilities.ritual.UtilityRitual;
import com.guga.ordemparanormal.client.particles.AbilitiesParticleOptions;
import com.guga.ordemparanormal.core.registry.OPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class Luz extends AbstractRitual implements UtilityRitual {
    public Luz(ResourceLocation id, ParanormalElement element, int tier, int effortCost,
               double range, boolean mustHoldIngredient) {
        super(id, element, tier, effortCost, range, mustHoldIngredient);
    }

    @Override
    public void onUseBlock(BlockHitResult rayTraceResult, Level world, LivingEntity caster,
            @Nullable ItemStack ritualItem, @Nullable InteractionHand hand) {

        BlockPos blockPos = rayTraceResult.getBlockPos().relative(rayTraceResult.getDirection());
        BlockState luz = OPBlocks.LUZ_BLOCK.get().defaultBlockState();

        if (world instanceof ServerLevel serverLevel){
            for (int i = 0; i < 10; i++){
                serverLevel.sendParticles(AbilitiesParticleOptions.createData(getElement().getParticleColor(),
                                getElement() != ParanormalElement.MORTE),
                        blockPos.getX() + 0.5d + (serverLevel.random.nextInt(-20, 20)/100d),
                        blockPos.getY() + 0.5d + (serverLevel.random.nextInt(-20, 20)/100d),
                        blockPos.getZ() + 0.5d + (serverLevel.random.nextInt(-20, 20)/100d),
                        0, 0d, 0.04d, 0d, 1d);
            }
        }

        if (world.isEmptyBlock(blockPos)) world.setBlockAndUpdate(blockPos, luz);
    }
}