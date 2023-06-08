package com.guga.ordemparanormal.common.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseCategory;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class Erosiva extends AbstractCurse {
    public Erosiva(ResourceLocation id, ParanormalElement element, CurseCategory category, EquipmentSlot... slots) {
        super(id, element, category, slots);
    }
    @Override
    public int doBlockBreak(Player entity, LevelAccessor level, BlockPos pos, BlockState state, int exp) {
        BlockPos.betweenClosed(
                new BlockPos(pos).offset(1, 1, 1),
                new BlockPos(pos).offset(-1, -1, -1))
                .forEach(otherPos -> {
            if (otherPos != pos && level.getBlockState(otherPos).canHarvestBlock(level, otherPos, entity) && level.getBlockState(otherPos).is(state.getBlock())){
                BlockState otherState = level.getBlockState(otherPos);
                BlockEntity tileEntity = level.getBlockEntity(otherPos);

                int fortuneLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, entity.getMainHandItem());
                int silkTouchLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, entity.getMainHandItem());
                int xp = state.getExpDrop(level, pos, fortuneLevel, silkTouchLevel);

                if (otherState.onDestroyedByPlayer((Level) level, otherPos, entity, true, otherState.getFluidState())){
                    otherState.getBlock().destroy(level, otherPos, state);
                    otherState.getBlock().playerDestroy((Level) level, entity, otherPos, state, tileEntity, entity.getMainHandItem());
                    otherState.getBlock().popExperience((ServerLevel) level, otherPos, xp);
                }
            }
        });
        return super.doBlockBreak(entity, level, pos, state, exp);
    }
}
