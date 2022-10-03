package com.guga.ordemparanormal.common.item;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.abilities.ritual.IRitualCaster;
import com.guga.ordemparanormal.api.abilities.ritual.RitualCaster;
import com.guga.ordemparanormal.api.util.PowerUtils;
import com.guga.ordemparanormal.common.CommonComponents;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.ChatFormatting;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
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

            IAbilitiesCap abilities = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
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
        IAbilitiesCap abilities = pPlayer.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (abilities == null) return InteractionResultHolder.pass(stack);

        if (abilities.knowsRitual(ritual)) {
            pPlayer.startUsingItem(pUsedHand);
            return InteractionResultHolder.consume(stack);
        }
        return InteractionResultHolder.pass(stack);
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
            pTooltipComponents.add(CommonComponents.RITUAL_LEARNED);

            ChatFormatting formatting = ChatFormatting.WHITE;
            switch (ritual.getElement()){
                case SANGUE -> formatting = ChatFormatting.DARK_RED;
                case CONHECIMENTO -> formatting = ChatFormatting.GOLD;
                case ENERGIA -> formatting = ChatFormatting.DARK_PURPLE;
                case MORTE -> formatting = ChatFormatting.DARK_GRAY;
                case MEDO -> formatting = ChatFormatting.WHITE;
                case NONE -> formatting = ChatFormatting.GRAY;
            }

            Component ritual = this.getRitual().getDisplayName().plainCopy().withStyle(formatting);
            pTooltipComponents.add(CommonComponents.CURSED_WITH.plainCopy().append(ritual));
        } else {
            pTooltipComponents.add(CommonComponents.RITUAL_UNKNOWN);
        }
    }
    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        if (player.level instanceof ServerLevel level){
            for (int i = 0; i < 360; i++){
                if (i % 20 == 0 && count % 20 == 0){
                    level.sendParticles(ParticleTypes.INSTANT_EFFECT,
                            player.getX(), player.getY() + 0.1d, player.getZ(),
                            0, Math.cos(i) + 0.25d, 0d, Math.sin(i) + 0.25d, 1d);
                }
            }
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
