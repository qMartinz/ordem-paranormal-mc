package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class SaltoFantasma extends AbstractRitual {
    public SaltoFantasma() {
        super("salto_fantasma", ParanormalElement.ENERGIA, 3, 6, false, 10D, true);
    }

    @Override
    public void onUseBlock(BlockHitResult rayTraceResult, Level world, LivingEntity caster) {
        if (world instanceof ServerLevel level) {
            level.sendParticles(
                    new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.PURPLE_WOOL.defaultBlockState()),
                    caster.getX(), caster.getEyeY(), caster.getZ(),
                    10, 0, 0, 0, 0d);
            level.sendParticles(
                    new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.PURPLE_WOOL.defaultBlockState()),
                    caster.getX(), caster.getEyeY(), caster.getZ(),
                    10, 0, -1, 0, 0d);
        }
        // caster.getOnPos()
        // Vec3 blockPos = rayTraceResult.getLocation();
        caster.moveTo(rayTraceResult.getLocation());

        if (world instanceof ServerLevel level) {
            level.sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK, Blocks.PURPLE_WOOL.defaultBlockState()),
                    caster.getX(), caster.getEyeY(), caster.getZ(),
                    10, 0, 0, 0, 0d);

            level.sendParticles(ParticleTypes.ENCHANTED_HIT,
                    caster.getX(), caster.getEyeY(), caster.getZ(),
                    3, 0, 0, 0, 0d);
        }
    }
}
