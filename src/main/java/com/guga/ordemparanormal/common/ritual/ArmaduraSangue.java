package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class ArmaduraSangue extends AbstractRitual {
    public ArmaduraSangue() {
        super("armadura_sangue", ParanormalElement.SANGUE, 1, 3, true, 0, true);
    }
    @Override
    public void onUseSelf(Level world, LivingEntity caster) {
        MobEffectInstance resistance = new MobEffectInstance(OPEffects.SKIN_REINFORCED.get(), 1800, 1, false, false);
        caster.addEffect(resistance);

        ServerLevel level = (ServerLevel) world;
        level.sendParticles(
                new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()),
                caster.getX(), caster.getEyeY(), caster.getZ(),
                5, 0, 0.3d, 0, 0d);
    }
}
