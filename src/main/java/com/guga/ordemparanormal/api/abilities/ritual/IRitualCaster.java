package com.guga.ordemparanormal.api.abilities.ritual;

import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.util.PowerUtils;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
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
    int getMaxSlots();
    int getCurrentSlot();
    void setCurrentSlot(int slot);
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
    default InteractionResultHolder<ItemStack> castRitual(Level world, LivingEntity entity, ItemStack stack, @Nonnull AbstractRitual ritual){
        IAbilitiesCap abilities = entity.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (abilities == null) return InteractionResultHolder.pass(stack);

        double length = ritual.getRange();
        if (abilities.hasPower(OPPowers.AMPLIAR_RITUAL) && length > 0d) length += 3.5d;
        HitResult result = PowerUtils.rayTrace(entity, length, 0, false);
        if (!world.isClientSide) {
            ritual.onUse(result, world, entity);
            return InteractionResultHolder.consume(stack);
        } else {
            return InteractionResultHolder.pass(stack);
        }
    }
    String getTagID();
}
