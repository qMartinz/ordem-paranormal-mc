package com.guga.ordemparanormal.common.block.blocks;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.common.block.entities.KnowledgeTableBlockEntity;
import com.guga.ordemparanormal.core.registry.OPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class MesaConhecimento extends MesaMaldicao{
    public MesaConhecimento() {
        super(ParanormalElement.MORTE);
    }
    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof KnowledgeTableBlockEntity) {
                ((KnowledgeTableBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof KnowledgeTableBlockEntity e && !e.isOpen()) {
                NetworkHooks.openScreen(((ServerPlayer)pPlayer), (KnowledgeTableBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Container provider is missing.");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new KnowledgeTableBlockEntity(pPos, pState);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, OPBlockEntities.KNOWLEDGE_TABLE_ENTITY.get(),
                KnowledgeTableBlockEntity::tick);
    }
}
