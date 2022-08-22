package com.guga.ordemparanormal.common.block;

import com.guga.ordemparanormal.api.capabilities.data.*;
import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import com.guga.ordemparanormal.common.item.RitualItem;
import com.guga.ordemparanormal.core.registry.OPSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class AltarTranscender extends HorizontalDirectionalBlock {
    public static final VoxelShape NORTH = Shapes.or(
            Block.box(2, 0, 2.0100752375298914, 14, 2, 14.010075237529891),
            Block.box(3, 2, 3.0100752375298914, 13, 3, 13.010075237529891),
            Block.box(4, 3, 4.010075237529891, 12, 12, 12.010075237529891),
            Block.box(2.5, 9.876189780110156, 2.4896610595177275, 13.5, 13.876189780110156, 13.489661059517728));
    public static final VoxelShape EAST = Shapes.or(
            Block.box(1.9899247624701086, 0, 2, 13.989924762470109, 2, 14),
            Block.box(2.9899247624701086, 2, 3, 12.989924762470109, 3, 13),
            Block.box(3.9899247624701086, 3, 4, 11.989924762470109, 12, 12),
            Block.box(2.5103389404822725, 9.876189780110156, 2.5, 13.510338940482272, 13.876189780110156, 13.5));
    public static final VoxelShape SOUTH = Shapes.or(
            Block.box(2, 0, 1.9899247624701086, 14, 2, 13.989924762470109),
            Block.box(3, 2, 2.9899247624701086, 13, 3, 12.989924762470109),
            Block.box(4, 3, 3.9899247624701086, 12, 12, 11.989924762470109),
            Block.box(2.5, 9.876189780110156, 2.5103389404822725, 13.5, 13.876189780110156, 13.510338940482272));
    public static final VoxelShape WEST = Shapes.or(
            Block.box(2.0100752375298914, 0, 2, 14.010075237529891, 2, 14),
            Block.box(3.0100752375298914, 2, 3, 13.010075237529891, 3, 13),
            Block.box(4.010075237529891, 3, 4, 12.010075237529891, 12, 12),
            Block.box(2.4896610595177275, 9.876189780110156, 2.5, 13.489661059517728, 13.876189780110156, 13.5));
    public AltarTranscender() {
        super(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        builder.add(FACING);
    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            default -> NORTH;
        };
    }
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        ItemStack stack = pPlayer.getItemInHand(pHand);

        IPowerCap abilities = pPlayer.getCapability(PlayerPowersProvider.PLAYER_POWERS).orElse(null);
        INexCap nex = pPlayer.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);

        if (nex != null && abilities != null){
            if (stack.getItem() instanceof RitualItem item){
                AbstractRitual ritual = item.getRitual();
                if (!abilities.knowsRitual(ritual)){
                    if (nex.getNex() >= item.getRitual().getNexRequired() && abilities.getKnownRituals().size() < nex.getRitualSlots() /*&& !stack.getOrCreateTag().getBoolean("ritualLearned")*/) {
                        CompoundTag tag = new CompoundTag();
                        tag.putBoolean("ritualLearned", true);
                        stack.setTag(tag);
                        abilities.learnRitual(ritual);
                        CapEvents.syncPlayerPowers(pPlayer);
                        pLevel.playSound(null, pPos, OPSounds.RITUAL_LEARNED.get(), SoundSource.BLOCKS, 0.5f, 1f);
                        pLevel.playSound(null, pPos, SoundEvents.BOOK_PAGE_TURN, SoundSource.BLOCKS, 1f, 1f);
                        return InteractionResult.CONSUME;
                    }
                } else {
                    if (stack.getOrCreateTag().getBoolean("ritualLearned")){
                        CompoundTag tag = new CompoundTag();
                        tag.putBoolean("ritualLearned", false);
                        stack.setTag(tag);
                    }
                    abilities.forgetRitual(ritual);
                    CapEvents.syncPlayerPowers(pPlayer);
                    pLevel.playSound(null, pPos, OPSounds.RITUAL_FORGOTTEN.get(), SoundSource.BLOCKS, 0.5f, 1f);
                    pLevel.playSound(null, pPos, SoundEvents.BOOK_PAGE_TURN, SoundSource.BLOCKS, 1f, 1f);
                    return InteractionResult.CONSUME;
                }
            }
        }
        return InteractionResult.PASS;
    }
}