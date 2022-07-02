package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.ritual.ElementDamage;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;

public class SkinningRitual extends AbstractRitual {
    public SkinningRitual() {
        super("skinning", 5, 2);
    }
    @Override
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, @Nullable LivingEntity caster) {
        LivingEntity target = (LivingEntity) rayTraceResult.getEntity();
        float amount = ElementDamage.isEntityWeakTo(target, ElementDamage.BLOOD_DAMAGE) ? 10f : ElementDamage.isEntityResistant(target, ElementDamage.BLOOD_DAMAGE) ? 2.5f : 5f;
        target.hurt(ElementDamage.BLOOD_DAMAGE, amount);
        target.level.playSound(null, target.blockPosition(), SoundEvents.DOLPHIN_EAT, SoundSource.PLAYERS, 2f, 1f);
        ServerLevel level = (ServerLevel) world;
        level.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()), target.getX(), target.getEyeY(), target.getZ(), 10, 0, 0, 0, 0d);
        level.sendParticles(ParticleTypes.CRIT, target.getX(), target.getEyeY(), target.getX(), 4, 0, 0, 0, 0d);
        MobEffectInstance effect = new MobEffectInstance(OPEffects.BLEED.get(), 100, 1, false, false);
        target.addEffect(effect);
    }
}
