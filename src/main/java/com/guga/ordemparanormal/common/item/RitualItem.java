package com.guga.ordemparanormal.common.item;

import com.guga.ordemparanormal.api.capabilities.data.IPlayerCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNex;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.ritual.IRitualCaster;
import com.guga.ordemparanormal.api.ritual.RitualCaster;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RitualItem extends Item {
    public final AbstractRitual ritual;
    public RitualItem(Properties properties, AbstractRitual ritual) {
        super(properties);
        this.ritual = ritual;
    }
    @NotNull
    public AbstractRitual getRitual() {
        return ritual;
    }
    @NotNull
    public IRitualCaster getRitualCaster(ItemStack stack) {
        return new ItemCaster(stack);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        IPlayerCap abilities = pPlayer.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (abilities == null) return InteractionResultHolder.pass(stack);

        if (abilities.knowsRitual(this.getRitual())){
            IRitualCaster caster = getRitualCaster(stack);
            return caster.castRitual(pLevel, pPlayer, pUsedHand, ritual);
        } else {
            return InteractionResultHolder.pass(stack);
        }
    }
    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }
    public static class ItemCaster extends RitualCaster {
        public ItemCaster(ItemStack stack) {
            super(stack);
        }
        public ItemCaster(CompoundTag tag) {
            super(tag);
        }
    }
}
