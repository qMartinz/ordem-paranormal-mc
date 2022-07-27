package com.guga.ordemparanormal.api.powers.ritual;

import com.guga.ordemparanormal.api.util.PowerUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nonnull;

public interface IRitualCaster {
    @Nonnull
    AbstractRitual getRitual();
    @Nonnull
    AbstractRitual getRitual(int slot);
    @Nonnull int getMaxSlots();
    @Nonnull int getCurrentSlot();
    @Nonnull void setCurrentSlot(int slot);
    default void setNextSlot(){
        int slot = getCurrentSlot() + 1;
        if(slot < 1) slot = getMaxSlots();
        setCurrentSlot(slot);
    }
    default void setPreviousSlot(){
        int slot = getCurrentSlot() + 1;
        if(slot < 1) slot = getMaxSlots();
        setCurrentSlot(slot);
    }
    void setRitual(AbstractRitual ritual, int slot);
    void setRitual(AbstractRitual ritual);
    @Nonnull
    default AbstractRitual getRitual(Level world, Player playerEntity, InteractionHand hand, IRitualCaster caster){
        return caster.getRitual();
    }
    default InteractionResultHolder<ItemStack> castRitual(Level world, Player player, ItemStack stack, @Nonnull AbstractRitual ritual){
        HitResult result = PowerUtils.rayTrace(player, 5, 0, false);
        if (!world.isClientSide) {
            ritual.onUse(result, world, player);
            return InteractionResultHolder.consume(stack);
        } else {
            return InteractionResultHolder.pass(stack);
        }
    }
    String getTagID();
}
