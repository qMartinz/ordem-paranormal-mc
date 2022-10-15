package com.guga.ordemparanormal.common.item;

import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.core.OrdemParanormal;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class PergaminhoAntigo extends Item {
    public PergaminhoAntigo() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).tab(OrdemParanormal.OP_TAB));
    }

    @Override
    public void releaseUsing(ItemStack stack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {
            int i = this.getUseDuration(stack) - pTimeLeft;
            if (i < 0)
                return;

            /*
             * IAbilitiesCap abilities =
             * player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
             * if (abilities == null)
             * return;
             * 
             * int tier = 2;
             * int peCount;
             * 
             * if (tier == 2){
             * 
             * }
             * 
             * 
             * switch (tier) {
             * default:
             * peCount = 2;
             * case 1:
             * peCount = 2;
             * case 2:
             * peCount = 4;
             * case 3:
             * peCount = 6;
             * case 4:
             * peCount = 8;
             * }
             */
            int peCount = 4;

            INexCap nex = player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
            if (!player.isCreative()) {
                if (getPowerForTime(i) == 1F) {
                    nex.setCurrentEffort(nex.getCurrentEffort() + peCount);
                    stack.shrink(1);
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.consume(stack);
    }

    public static float getPowerForTime(int pCharge) {
        float f = (float) pCharge / 20.0f;
        f = (f * f + f * 2.0f) / 3.0f;
        if (f > 1.0f) {
            f = 1.0f;
        }

        return f;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 36000;
    }

}
