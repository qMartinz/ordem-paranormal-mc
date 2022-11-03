package com.guga.ordemparanormal.common.item;

import com.guga.ordemparanormal.core.registry.OPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

//TODO #9
public class Giz extends Item {
    public Giz() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON).tab(null).defaultDurability(18));
    }

    public void onItemUse(BlockHitResult pBlockHitResult, ItemStack pItemStack, Level world) {

        BlockPos pBlockPos = pBlockHitResult.getBlockPos().relative(pBlockHitResult.getDirection());
        BlockState desenho = OPBlocks.LUZ_BLOCK.get().defaultBlockState();
        world.setBlockAndUpdate(pBlockPos, desenho);
    }
}