package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class AprimoramentoFisico extends AbstractRitual {
    public AprimoramentoFisico() {
        super("aprimoramento_fisico", ParanormalElement.SANGUE, 2, 3, true, 0D, true);
    }

    @Override
    public void onUseSelf(Level world, LivingEntity caster) {
        // checa se está agachado
        boolean crouch = caster.isCrouching();

        if (crouch == true) {
            MobEffectInstance velocity = new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1800, 0, false, false);
            caster.addEffect(velocity);
        } else {

            MobEffectInstance resistance = new MobEffectInstance(OPEffects.ENHANCED_PHYSIQUE.get(), 1800, 2, false,
                    false);
            caster.addEffect(resistance);
        }

        ServerLevel level = (ServerLevel) world;
        level.sendParticles(
                new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.NETHERRACK.defaultBlockState()),
                caster.getX(), caster.getEyeY(), caster.getZ(),
                5, 0.2d, 0.2d, 0.2d, 0d);
    }
}
