package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.guga.ordemparanormal.core.registry.OPItems;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class AprimorarFisico extends AbstractRitual {
    public AprimorarFisico() {
        super("aprimorar_fisico", ParanormalElement.SANGUE, 2, 4, true, 0D, true);
    }
    @Override
    public void onUseSelf(Level world, LivingEntity caster) {
        MobEffectInstance resistance = new MobEffectInstance(OPEffects.ENHANCED_PHYSIQUE.get(), 1800, 2, false, false);
        caster.addEffect(resistance);

        ServerLevel level = (ServerLevel) world;
        level.sendParticles(
                new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.NETHERRACK.defaultBlockState()),
                caster.getX(), caster.getEyeY(), caster.getZ(),
                5, 0.2d, 0.2d, 0.2d, 0d);
    }
}
