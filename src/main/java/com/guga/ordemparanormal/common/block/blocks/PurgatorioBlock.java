package com.guga.ordemparanormal.common.block.blocks;

import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class PurgatorioBlock extends Block {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    public static final IntegerProperty LIFE = IntegerProperty.create("purgatory_life", 0, 2);
    public PurgatorioBlock() {
        super(Properties.of((new Material.Builder(MaterialColor.NETHER)).nonSolid().build())
                .jumpFactor(0.15f).speedFactor(0.25f).strength(600f, 4.0f));
        this.registerDefaultState(this.defaultBlockState().setValue(LIFE, 2));
    }
    public VoxelShape getShape(BlockState p_152917_, BlockGetter p_152918_, BlockPos p_152919_, CollisionContext p_152920_) {
        return SHAPE;
    }
    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return super.getCollisionShape(pState, pLevel, pPos, pContext).move(0, 0.15f, 0);
    }
    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return !pLevel.isEmptyBlock(pPos.below()) && pLevel.getBlockState(pPos.below()).getMaterial().isSolid();
    }
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pLevel.isClientSide()) {
            if (pEntity instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(
                        OPEffects.PURGATORY.get(), 20, 0, false, false));
            }
            if (pLevel instanceof ServerLevel level){
                //level.sendParticles(OPParticles.PURGATORIO_PARTICLE.get(), pEntity.getX(), pEntity.getY() - 1f, pEntity.getZ(),
               //         3, 0.1f, 0.05f, 0.1f, 0d);
            }
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }
    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(LIFE) > 0;
    }
    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        super.randomTick(pState, pLevel, pPos, pRandom);
        int newLife = pState.getValue(LIFE) - 1;
        pLevel.setBlock(pPos, this.defaultBlockState().setValue(LIFE, newLife), 2);
        if (newLife <= 0) {
            pLevel.destroyBlock(pPos, false, null, 512);
            pLevel.getBlockState(pPos).getBlock().destroy(pLevel, pPos, pLevel.getBlockState(pPos));
            List<LivingEntity> entities = pLevel.getEntitiesOfClass(LivingEntity.class, new AABB(pPos));
            entities.forEach(e -> {
                if (e.hasEffect(OPEffects.PURGATORY.get())) e.removeEffect(OPEffects.PURGATORY.get());
            });
        }
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LIFE);
        super.createBlockStateDefinition(pBuilder);
    }
}
