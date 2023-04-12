package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.abilities.ritual.DefensiveRitual;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;

public class ArmaduraSangue extends AbstractRitual implements DefensiveRitual {

    public ArmaduraSangue(ResourceLocation id, ParanormalElement element, int tier, int effortCost, double range, boolean mustHoldIngredient) {
        super(id, element, tier, effortCost, range, mustHoldIngredient);
    }
    @Override
    public void onUseSelf(HitResult rayTraceResult, Level world, LivingEntity caster, ItemStack ritualItem, InteractionHand hand) {
        MobEffectInstance resistance = new MobEffectInstance(OPEffects.SKIN_REINFORCED.get(), 1200, 1, false, false);
        caster.addEffect(resistance);

        ServerLevel level = (ServerLevel) world;
        level.sendParticles(
                new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()),
                caster.getX(), caster.getEyeY(), caster.getZ(),
                5, 0, 0.3d, 0, 0d);
    }
}
