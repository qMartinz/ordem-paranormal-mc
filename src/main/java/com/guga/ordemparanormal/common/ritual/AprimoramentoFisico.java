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
import net.minecraft.world.phys.HitResult;

public class AprimoramentoFisico extends AbstractRitual {
    public AprimoramentoFisico() {
        super("aprimoramento_fisico", ParanormalElement.SANGUE, 2, 4, true, 0D, true);
    }

    @Override
    public void onUseSelf(HitResult rayTraceResult, Level world, LivingEntity caster) {
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
