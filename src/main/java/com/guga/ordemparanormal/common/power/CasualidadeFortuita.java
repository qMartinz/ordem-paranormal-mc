package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.Random;

public class CasualidadeFortuita extends PlayerPower {
    public CasualidadeFortuita(ResourceLocation id, ParanormalElement element, int nex, int[] attributes, PlayerPower... powers) {
        super(id, false, element, 0, nex, attributes, powers);
    }
    @Override
    public int onBlockBreak(Player player, LevelAccessor level, BlockPos pos, BlockState state, int exp) {
        Random rand = new Random();
        if (rand.nextInt(1, 20) <= 1 &&
                state.is(Tags.Blocks.STONE) &&
                !player.isCreative() &&
                !EnchantmentHelper.getEnchantments(player.getMainHandItem()).containsKey(Enchantments.SILK_TOUCH)){

            List<ItemStack> items = player.getServer().getLootTables().get(new ResourceLocation(OrdemParanormal.MOD_ID, "powers/casualidade_fortuita"))
                    .getRandomItems(new LootContext.Builder((ServerLevel) player.level).create(LootContextParamSet.builder().build()));

            player.level.addFreshEntity(new ItemEntity(player.level, pos.getX() - 0.5d, pos.getY(), pos.getZ() - 0.5d, items.get(rand.nextInt(0, items.size()))));
        }

        return super.onBlockBreak(player, level, pos, state, exp);
    }
}
