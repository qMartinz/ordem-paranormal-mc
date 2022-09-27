package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ElementDamage;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;

public class Hemofagia extends AbstractRitual {
    public Hemofagia() {
        super("hemofagia", ParanormalElement.SANGUE, 2, 4, true, 5D, true);
    }
    @Override
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster) {
        LivingEntity target = (LivingEntity) rayTraceResult.getEntity();

        target.hurt(ElementDamage.ritualDamage(caster, ParanormalElement.SANGUE),
                ElementDamage.isEntityResistant(target, ElementDamage.DANO_SANGUE) ? 3f :
                        ElementDamage.isEntityWeakTo(target, ElementDamage.DANO_SANGUE) ? 12f : 6f);
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
