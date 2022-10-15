package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.common.entity.ParanormalCreature;
import com.mojang.math.Vector3f;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class Cicatrizacao extends AbstractRitual {
    public Cicatrizacao() {
        super("cicatrizacao", ParanormalElement.MORTE, 1, 2, true, 3.5D, true);
    }

    @Override
    public void onUseSelf(HitResult rayTraceResult, Level world, LivingEntity caster, ItemStack ritualItem, InteractionHand hand) {
        caster.heal(6);

        ServerLevel level = (ServerLevel) world;
        level.sendParticles(
                new DustParticleOptions(new Vector3f(0.25f, 0.25f, 0.25f), 0.7f),
                caster.getX(), caster.getEyeY(), caster.getZ(),
                6, 0.4d, 0.4d, 0.4d, 0d);
    }

    @Override
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster, ItemStack ritualItem, InteractionHand hand) {
        LivingEntity target = (LivingEntity) rayTraceResult.getEntity();
        if (target instanceof ParanormalCreature paranormalCreature) {
            if (paranormalCreature.getMainElement() != ParanormalElement.SANGUE) {
                paranormalCreature.heal(6);
            }
        } else {
            target.heal(6);
        }

        ServerLevel level = (ServerLevel) world;
        level.sendParticles(
                ParticleTypes.ASH,
                target.getX(), target.getEyeY(), target.getZ(),
                6, 0, 0, 0, 0d);
    }
}
