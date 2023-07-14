package com.guga.ordemparanormal.common.block.entities;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.core.registry.OPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class MesaSangueBlockEntity extends MesaMaldicaoBlockEntity {
    public MesaSangueBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(OPBlockEntities.BLOOD_CURSE_TABLE_BLOCK_ENTITY.get(), pPos, pBlockState, ParanormalElement.SANGUE);
    }
}
