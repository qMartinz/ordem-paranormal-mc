package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.registry.OPItems;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;

public class LaminaMedo extends AbstractRitual {
    public LaminaMedo() {
        super("lamina_medo", ParanormalElement.MEDO, 4, 12, true, 0D, false);
    }

    @Override
    public void onUseSelf(HitResult rayTraceResult, Level world, LivingEntity caster, ItemStack ritualItem, InteractionHand hand) {
        ritualItem.getOrCreateTag().putBoolean("active", true);

        // Particula
        ServerLevel level = (ServerLevel) world;
        level.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.GLASS.defaultBlockState()),
                caster.getX(), caster.getY(), caster.getZ(), 5, 0, 0.3, 0, 0D);
    }
}
