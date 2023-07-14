package com.guga.ordemparanormal.common.block.blocks;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.common.block.entities.MesaConhecimentoBlockEntity;
import com.guga.ordemparanormal.common.block.entities.MesaMorteBlockEntity;
import com.guga.ordemparanormal.core.registry.OPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MesaMorte extends MesaMaldicao{
    public MesaMorte() {
        super(ParanormalElement.MORTE);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new MesaMorteBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, OPBlockEntities.DEATH_CURSE_TABLE_BLOCK_ENTITY.get(),
                MesaMorteBlockEntity::tick);
    }
}
