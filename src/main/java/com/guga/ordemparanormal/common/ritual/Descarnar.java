package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.paranormaldamage.EntityParanormalDamageSource;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;

import static com.guga.ordemparanormal.api.ParanormalElement.SANGUE;

public class Descarnar extends AbstractRitual {
    public Descarnar() {
        super("descarnar", SANGUE, 1, 2, true, 5D, true);
    }
    @Override
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster) {
        LivingEntity target = (LivingEntity) rayTraceResult.getEntity();
        
        target.hurt(ParanormalDamageSource.ritualAttack(caster, this), 4f);

        if(world instanceof ServerLevel level) {
            level.sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()),
                    target.getX(), target.getEyeY(), target.getZ(),
                    5, 0, 0, 0, 0d);

            level.sendParticles(ParticleTypes.CRIT,
                    target.getX(), target.getEyeY(), target.getZ(),
                    3, 0, 0, 0, 0d);
        }
        
        MobEffectInstance effect = new MobEffectInstance(OPEffects.BLEED.get(), 100, 1, false, false);
        target.addEffect(effect, caster);
    }
}
