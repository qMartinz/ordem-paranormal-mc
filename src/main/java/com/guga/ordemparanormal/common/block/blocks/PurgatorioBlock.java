package com.guga.ordemparanormal.common.block.blocks;

import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class PurgatorioBlock extends CarpetBlock {
    public PurgatorioBlock() {
        super(Properties.of((new Material.Builder(MaterialColor.COLOR_RED).build())).friction(1.2f).jumpFactor(0f));
    }
    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        super.stepOn(pLevel, pPos, pState, pEntity);
        if (pEntity instanceof LivingEntity livingEntity) livingEntity.addEffect(new MobEffectInstance(
                OPEffects.PURGATORY.get(), 3, 0, false, false));
    }
}
