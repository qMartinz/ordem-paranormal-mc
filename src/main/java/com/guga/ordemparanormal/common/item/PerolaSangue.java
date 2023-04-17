package com.guga.ordemparanormal.common.item;

import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class PerolaSangue extends Item {
    public PerolaSangue(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void releaseUsing(ItemStack stack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        int i = this.getUseDuration(stack) - pTimeLeft;
        if (i < 0) return;

        if (getPowerForTime(i) == 1f){
            MobEffectInstance effect = new MobEffectInstance(OPEffects.ADRENALINE.get(), 1200, 0, false, false);
            pEntityLiving.addEffect(effect);

            stack.shrink(1);
        }
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.consume(stack);
    }
    public static float getPowerForTime(int pCharge) {
        float f = (float)pCharge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }
    public int getUseDuration(ItemStack stack) {
        return 36000;
    }
    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }
}
