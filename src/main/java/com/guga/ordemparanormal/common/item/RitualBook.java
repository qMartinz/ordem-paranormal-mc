package com.guga.ordemparanormal.common.item;

import com.guga.ordemparanormal.api.abilities.ritual.IRitualCaster;
import com.guga.ordemparanormal.api.abilities.ritual.RitualCaster;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPCreativeTabs;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RitualBook extends Item {
    public RitualBook() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).tab(OPCreativeTabs.OP_TAB));
    }
    @Override
    public void releaseUsing(ItemStack stack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {
            int i = this.getUseDuration(stack) - pTimeLeft;
            if (i < 0) return;

            IAbilitiesCap abilities = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
            if (abilities == null) return;
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
    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }
    @NotNull
    public IRitualCaster getRitualCaster(ItemStack stack){
        return new BookCaster(stack);
    }
    public static class BookCaster extends RitualCaster{
        public BookCaster(ItemStack stack) {
            super(stack);
        }
        public BookCaster(CompoundTag itemTag) {
            super(itemTag);
        }
        @Override
        public int getMaxSlots() {
            return 4;
        }
    }
}
