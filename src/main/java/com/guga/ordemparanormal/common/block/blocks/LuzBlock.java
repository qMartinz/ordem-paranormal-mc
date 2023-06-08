package com.guga.ordemparanormal.common.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class LuzBlock extends Block {
    protected static final VoxelShape SHAPE = Block.box(6D, 6D, 6D, 10D, 10D, 10D);
    public LuzBlock() {
        super(Properties.of((new Material.Builder(MaterialColor.COLOR_PURPLE).build())).lightLevel((emission) -> 12).instabreak());
    }
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.empty();
    }
    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        super.animateTick(pState, pLevel, pPos, pRandom);

        for (int i = 0; i < 3; i++){
            pLevel.addParticle(ParticleTypes.ELECTRIC_SPARK,
                    pPos.getX() + 0.5d + (pRandom.nextInt(-15, 15)/100d),
                    pPos.getY() + 0.5d + (pRandom.nextInt(-15, 15)/100d),
                    pPos.getZ() + 0.5d + (pRandom.nextInt(-15, 15)/100d),
                    0d, 0.3d, 0d);
        }
    }
}
