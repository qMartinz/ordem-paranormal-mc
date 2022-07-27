package com.guga.ordemparanormal.common.item;

import com.guga.ordemparanormal.api.capabilities.data.IPowerCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerPowersProvider;
import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.powers.ritual.IRitualCaster;
import com.guga.ordemparanormal.api.powers.ritual.RitualCaster;
import com.guga.ordemparanormal.api.util.PowerUtils;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RitualItem extends Item {
    public final AbstractRitual ritual;
    public RitualItem(AbstractRitual ritual) {
        super(new Item.Properties().stacksTo(1).rarity(PowerUtils.getRarity(ritual.getTier())).tab(OrdemParanormal.RITUALS_TAB));
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
    public void releaseUsing(ItemStack stack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {
            int i = this.getUseDuration(stack) - pTimeLeft;
            if (i < 0) return;

            IPowerCap abilities = player.getCapability(PlayerPowersProvider.PLAYER_POWERS).orElse(null);
            if (abilities == null) return;

            if (abilities.knowsRitual(this.getRitual()) && getPowerForTime(i) == 1f){
                IRitualCaster caster = getRitualCaster(stack);
                caster.castRitual(pLevel, player, stack, ritual);
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
        float f = (float)pCharge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (pStack.getOrCreateTag().getBoolean("ritualLearned")){
            pTooltipComponents.add(new TranslatableComponent("ordemparanormal.ritual_item.ritual_learned"));
        } else {
            pTooltipComponents.add(new TranslatableComponent("ordemparanormal.ritual_item.ritual_unknown"));
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
