package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;

public class AprimoramentoFisico extends AbstractRitual {

    public AprimoramentoFisico(ResourceLocation id, ParanormalElement element, int tier, int effortCost, boolean hasEntityTarget, double range, boolean mustHoldIngredient) {
        super(id, element, tier, effortCost, hasEntityTarget, range, mustHoldIngredient);
    }
    @Override
    public void onUseSelf(HitResult rayTraceResult, Level world, LivingEntity caster, ItemStack ritualItem, InteractionHand hand) {
        MobEffectInstance resistance = new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2400, 1, false,
                false);
        MobEffectInstance strength = new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 1, false,
                false);

        caster.addEffect(resistance);
        caster.addEffect(strength);

        ServerLevel level = (ServerLevel) world;
        level.sendParticles(
                new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.NETHERRACK.defaultBlockState()),
                caster.getX(), caster.getEyeY(), caster.getZ(),
                5, 0.2d, 0.2d, 0.2d, 0d);
    }
}
