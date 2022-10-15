package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.mojang.math.Vector3f;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class ConsumirManancial extends AbstractRitual {
    public ConsumirManancial() {
        super("consumir_manancial", ParanormalElement.MORTE, 1, 3, true, 0D, true);
    }
    @Override
    public void onUseSelf(HitResult rayTraceResult, Level world, LivingEntity caster) {
        MobEffectInstance effect = new MobEffectInstance(OPEffects.LIFE_ABSORBED.get(), 1800, 1, false, false);
        caster.addEffect(effect);

        ServerLevel level = (ServerLevel) world;
        level.sendParticles(
                new DustParticleOptions(new Vector3f(0.25f, 0.25f, 0.25f), 0.7f),
                caster.getX(), caster.getEyeY(), caster.getZ(),
                10, 0.4d, 0.4d, 0.4d, 0d);
    }
}
