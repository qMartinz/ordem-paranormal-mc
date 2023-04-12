package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.abilities.ritual.OffensiveRitual;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;

public class Hemofagia extends AbstractRitual implements OffensiveRitual {
    public Hemofagia(ResourceLocation id, ParanormalElement element, int tier, int effortCost, double range, boolean mustHoldIngredient) {
        super(id, element, tier, effortCost, range, mustHoldIngredient);
    }
    @Override
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster, ItemStack ritualItem, InteractionHand hand) {
        LivingEntity target = (LivingEntity) rayTraceResult.getEntity();

        target.hurt(ParanormalDamageSource.ritualAttack(caster, this), 5f);
        caster.heal(5);

        if(world instanceof ServerLevel level) {
            level.sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()),
                    target.getX(), target.getEyeY(), target.getZ(),
                    5, 0, 0, 0, 0d);

            level.sendParticles(ParticleTypes.CRIT,
                    target.getX(), target.getEyeY(), target.getZ(),
                    3, 0, 0, 0, 0d);

            level.sendParticles(
                    new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.NETHERRACK.defaultBlockState()),
                    caster.getX(), caster.getEyeY(), caster.getZ(),
                    5, .2, .2, .2, 1d);
        }
    }
}
