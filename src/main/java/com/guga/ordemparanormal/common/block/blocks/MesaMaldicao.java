package com.guga.ordemparanormal.common.block.blocks;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.CurseHelper;
import com.guga.ordemparanormal.api.curses.CurseInstance;
import com.guga.ordemparanormal.client.particles.AbilitiesParticleOptions;
import com.guga.ordemparanormal.common.block.entities.MesaMaldicaoBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class MesaMaldicao extends Block implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
    public final ParanormalElement element;
    public MesaMaldicao(ParanormalElement element) {
        super(BlockBehaviour.Properties.copy(Blocks.ENCHANTING_TABLE));
        this.element = element;
    }
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        builder.add(FACING);
    }
    @javax.annotation.Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof MesaMaldicaoBlockEntity) {
                ((MesaMaldicaoBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pHand != InteractionHand.MAIN_HAND)
            return InteractionResult.PASS;
        if (!pLevel.isClientSide && pLevel.getBlockEntity(pPos) instanceof MesaMaldicaoBlockEntity blockEntity) {
            if (blockEntity.getStack() != null && pPlayer.getItemInHand(pHand).isEmpty()) {
                ItemEntity item = new ItemEntity(pLevel, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), blockEntity.getStack());
                pLevel.addFreshEntity(item);
                blockEntity.setStack(ItemStack.EMPTY);
            } else if (!pPlayer.getInventory().getSelected().isEmpty()) {
                if (blockEntity.getStack().isEmpty()){
                    blockEntity.setStack(pPlayer.getInventory().removeItem(pPlayer.getInventory().selected, 1));
                } else {
                    boolean hasCursableItemInSlot = CurseHelper.getCurses(blockEntity.getStack()).stream().filter(inst -> !inst.getCurse().isTemporary()).toList().size() < 4;

                    boolean hasCursedItemInSlot = CurseHelper.getCurses(pPlayer.getInventory().getSelected()).stream().anyMatch(inst ->
                            inst.getCurse().canCurse(blockEntity.getStack()) &&
                                    inst.getCurse().getMaxUses() == 0 &&
                                    CurseHelper.getCurses(blockEntity.getStack()).stream().noneMatch(inst2 -> inst.getCurse() == inst2.getCurse()) &&
                                    inst.getCurse().getElement() == element);

                    boolean accept = !blockEntity.getStack().isEmpty() &&
                            hasCursableItemInSlot && hasCursedItemInSlot && blockEntity.getFuel() >= 4;

                    if (accept) {
                        ItemStack result = blockEntity.getStack().copy();

                        for (CurseInstance curse : CurseHelper.getCurses(pPlayer.getInventory().getSelected())){
                            if (curse.getCurse().getElement() == element &&
                                    CurseHelper.getCurses(blockEntity.getStack()).stream().noneMatch(inst -> inst.getCurse() == curse.getCurse())){

                                CurseHelper.addCurse(result, curse);
                            }
                        }

                        for (CurseInstance curse : CurseHelper.getCurses(pPlayer.getInventory().getSelected())){
                            if (curse.getCurse().getElement() == element &&
                                    CurseHelper.getCurses(blockEntity.getStack()).stream().noneMatch(inst -> inst.getCurse() == curse.getCurse())){

                                CurseHelper.removeCurse(pPlayer.getInventory().getSelected(), curse.getCurse());
                            }
                        }

                        blockEntity.setStack(result);
                        blockEntity.setFuel(blockEntity.getFuel() - 4);

                        for (int i = 0; i < 360; i++){
                            if (i % 20 == 0 && pLevel instanceof ServerLevel level){
                                level.sendParticles(AbilitiesParticleOptions.createData(
                                                element.getParticleColor(),
                                                element != ParanormalElement.MORTE),
                                        pPos.getX() + 0.5d, pPos.getY() + 1.25d, pPos.getZ() + 0.5d,
                                        0, Math.cos(i) / 12d, 0.06d, Math.sin(i) / 12d, 0.5d);
                            }
                        }
                    }
                }
            }
            pLevel.sendBlockUpdated(pPos, pState, pState, 2);
        }

        return InteractionResult.SUCCESS;
    }

    @javax.annotation.Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> pServerType, BlockEntityType<E> pClientType, BlockEntityTicker<? super E> pTicker) {
        return pClientType == pServerType ? (BlockEntityTicker<A>)pTicker : null;
    }
}
