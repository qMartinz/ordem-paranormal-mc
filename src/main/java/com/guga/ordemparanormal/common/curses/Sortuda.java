package com.guga.ordemparanormal.common.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseCategory;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.Random;

public class Sortuda extends AbstractCurse {
    public Sortuda(ResourceLocation id, ParanormalElement element, CurseCategory category, EquipmentSlot... slots) {
        super(id, element, category, slots);
    }
    @Override
    public int doBlockBreak(Player player, LevelAccessor level, BlockPos pos, BlockState state, int exp) {
        Random rand = new Random();
        if (rand.nextInt(1, 20) <= 5 &&
                !player.isCreative() &&
                !EnchantmentHelper.getEnchantments(player.getMainHandItem()).containsKey(Enchantments.SILK_TOUCH)){

            state.getDrops(new LootContext.Builder((ServerLevel) player.level)).forEach(item -> {
                player.level.addFreshEntity(new ItemEntity(player.level, pos.getX() - 0.5d, pos.getY(), pos.getZ() - 0.5d, item));
            });
        }

        return super.doBlockBreak(player, level, pos, state, exp);
    }
    @Override
    public boolean canCurse(ItemStack stack) {
        return super.canCurse(stack) && !EnchantmentHelper.getEnchantments(stack).containsKey(Enchantments.SILK_TOUCH);
    }
}
