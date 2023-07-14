package com.guga.ordemparanormal.common.block.entities;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.client.particles.AbilitiesParticleOptions;
import com.guga.ordemparanormal.client.particles.SparkleParticleOptions;
import com.guga.ordemparanormal.core.registry.OPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;

public class LuzBlockEntity extends BlockEntity {
    public LuzBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(OPBlockEntities.LUZ_ENTITY.get(), pPos, pBlockState);
    }
    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, LuzBlockEntity pBlockEntity) {
        if (pLevel.isClientSide){
            for (int i = 0; i < 3; i++){
                pLevel.addParticle(AbilitiesParticleOptions.createData(ParanormalElement.ENERGIA.getParticleColor(), true),
                        pPos.getX() + 0.5d + (pLevel.random.nextInt(-15, 15)/100d),
                        pPos.getY() + 0.5d + (pLevel.random.nextInt(-15, 15)/100d),
                        pPos.getZ() + 0.5d + (pLevel.random.nextInt(-15, 15)/100d),
                        0d, 0.02d, 0d);
            }
            for (int i = 0; i < 2; i++){
                pLevel.addParticle(AbilitiesParticleOptions.createData(new Color(102, 48, 255), true),
                        pPos.getX() + 0.5d + (pLevel.random.nextInt(-10, 10)/100d),
                        pPos.getY() + 0.5d + (pLevel.random.nextInt(-10, 10)/100d),
                        pPos.getZ() + 0.5d + (pLevel.random.nextInt(-10, 10)/100d),
                        0d, 0.017d, 0d);
            }
        }
    }
}
