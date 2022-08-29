package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ElementDamage;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.guga.ordemparanormal.core.registry.OPItems;
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

public class Descarnar extends AbstractRitual {
    public Descarnar() {
        super("descarnar", ParanormalElement.BLOOD, 1, 2, true, 5D, OPItems.ORGAO.get());
    }
    @Override
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster) {
        LivingEntity target = (LivingEntity) rayTraceResult.getEntity();
        
        target.hurt(ElementDamage.ritualDamage(caster, ParanormalElement.BLOOD), 4f);
        
        ServerLevel level = (ServerLevel) world;
        
        level.sendParticles(
                new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()), 
                target.getX(), target.getEyeY(), target.getZ(), 
                5, 0, 0, 0, 0d);
        
        level.sendParticles(ParticleTypes.CRIT, 
                target.getX(), target.getEyeY(), target.getX(), 
                3, 0, 0, 0, 0d);
        
        MobEffectInstance effect = new MobEffectInstance(OPEffects.BLEED.get(), 100, 1, false, false);
        target.addEffect(effect, caster);
    }
}
