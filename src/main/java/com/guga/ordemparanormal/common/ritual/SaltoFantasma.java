package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class SaltoFantasma extends AbstractRitual {
    public SaltoFantasma() {
        super("salto_fantasma", ParanormalElement.ENERGIA, 3, 6, false, 15D, true);
    }

    @Override
    public void onUseBlock(BlockHitResult rayTraceResult, Level world, LivingEntity caster, ItemStack ritualItem,
            InteractionHand hand) {
        Vec3 blockPos = rayTraceResult.getLocation();
        caster.moveTo(blockPos);

        ServerLevel level = (ServerLevel) world;
        level.sendParticles(ParticleTypes.PORTAL,
                caster.getX(), caster.getY() + caster.getEyeHeight() / 2f, caster.getZ(),
                10,
                0.2d, caster.getEyeHeight() / 2f, 0.2d,
                1);

        caster.moveTo(blockPos);
        level.sendParticles(ParticleTypes.PORTAL,
                caster.getX(), caster.getY() + caster.getEyeHeight() / 2f, caster.getZ(),
                10,
                0.2d, caster.getEyeHeight() / 2f, 0.2d,
                1);
    }
}
