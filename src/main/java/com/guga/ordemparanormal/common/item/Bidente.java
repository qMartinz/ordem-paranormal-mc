package com.guga.ordemparanormal.common.item;

import com.guga.ordemparanormal.common.entity.ThrownBidente;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.guga.ordemparanormal.core.registry.OPSounds;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class Bidente extends TridentItem {
    public Bidente(Properties p_43381_) {
        super(p_43381_);
    }
    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {
            int i = this.getUseDuration(pStack) - pTimeLeft;
            if (i >= 10) {
                int j = EnchantmentHelper.getRiptide(pStack);
                if (j <= 0 || player.getHealth() <= player.getMaxHealth()/2.5f) {
                    if (!pLevel.isClientSide) {
                        pStack.hurtAndBreak(1, player, (p_43388_) -> {
                            p_43388_.broadcastBreakEvent(pEntityLiving.getUsedItemHand());
                        });
                        if (j == 0) {
                            ThrownBidente thrownbident = new ThrownBidente(pLevel, player, pStack);
                            thrownbident.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F + (float)j * 0.5F, 1.0F);
                            if (player.getAbilities().instabuild) {
                                thrownbident.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                            }

                            pLevel.addFreshEntity(thrownbident);
                            pLevel.playSound(null, thrownbident, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                            if (!player.getAbilities().instabuild) {
                                player.getInventory().removeItem(pStack);
                            }
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                    if (j > 0){
                        player.awardStat(Stats.ITEM_USED.get(this));

                        float f7 = player.getYRot();
                        float f = player.getXRot();
                        float f1 = -Mth.sin(f7 * ((float)Math.PI / 180F)) * Mth.cos(f * ((float)Math.PI / 180F));
                        float f2 = -Mth.sin(f * ((float)Math.PI / 180F));
                        float f3 = Mth.cos(f7 * ((float)Math.PI / 180F)) * Mth.cos(f * ((float)Math.PI / 180F));
                        float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
                        float f5 = 3.0F * ((1.0F + (float)j) / 4.0F);
                        f1 *= f5 / f4;
                        f2 *= f5 / f4;
                        f3 *= f5 / f4;
                        player.push(f1, f2, f3);
                        player.startAutoSpinAttack(20);
                        if (player.isOnGround()) {
                            float f6 = 1.1999999F;
                            player.move(MoverType.SELF, new Vec3(0.0D, f6, 0.0D));
                        }

                        SoundEvent soundevent;
                        if (j >= 3) {
                            soundevent = OPSounds.BIDENTE_RIPTIDE_3.get();
                        } else if (j == 2) {
                            soundevent = OPSounds.BIDENTE_RIPTIDE_2.get();
                        } else {
                            soundevent = OPSounds.BIDENTE_RIPTIDE_1.get();
                        }

                        pLevel.playSound(null, player, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
                    }
                }
            }
        }
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
            return InteractionResultHolder.fail(itemstack);
        } else if (EnchantmentHelper.getRiptide(itemstack) > 0 && pPlayer.getHealth() > pPlayer.getMaxHealth()/2.5f) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addEffect(new MobEffectInstance(OPEffects.BLEED.get(), 140, 0, false, false));
        if (pTarget.level instanceof ServerLevel level)
            level.sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()),
                    pTarget.getX(), pTarget.getEyeY(), pTarget.getZ(),
                    6, 0, 0, 0, 0);

        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
