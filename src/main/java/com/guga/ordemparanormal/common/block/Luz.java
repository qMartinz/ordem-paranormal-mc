package com.guga.ordemparanormal.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
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

public class Luz extends Block {
    protected static final VoxelShape SHAPE = Block.box(6D, 6D, 6D, 10D, 10D, 10D);
    public Luz() {
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
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, Random pRandom) {
        super.animateTick(pState, pLevel, pPos, pRandom);

        for (int i = 0; i < 3; i++){
            pLevel.addParticle(ParticleTypes.ELECTRIC_SPARK,
                    pPos.getX() + 0.5d + pRandom.nextDouble(-0.15d, 0.15d),
                    pPos.getY() + 0.5d + pRandom.nextDouble(-0.15d, 0.15d),
                    pPos.getZ() + 0.5d + pRandom.nextDouble(-0.15d, 0.15d),
                    0d, 0.3d, 0d);
        }
    }
}
