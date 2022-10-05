package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class VelocidadeMortal extends AbstractRitual {
    public VelocidadeMortal() {
        super("velocidade_mortal", ParanormalElement.MORTE, 2, 3, true, 0D, true);
    }

    @Override
    public void onUseSelf(Level world, LivingEntity caster) {
        MobEffectInstance velocity = new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2400, 1, false, false);
        caster.addEffect(velocity);

        MobEffectInstance haste = new MobEffectInstance(MobEffects.DIG_SPEED, 2400, 1, false, false);
        caster.addEffect(haste);

        ServerLevel level = (ServerLevel) world;
        level.sendParticles(
                new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.GOLD_BLOCK.defaultBlockState()),
                caster.getX(), caster.getEyeY(), caster.getZ(),
                5, 0.2d, 0.2d, 0.2d, 0d);

    }

}
