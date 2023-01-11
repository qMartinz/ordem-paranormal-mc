package com.guga.ordemparanormal.common.block.blocks;

import com.guga.ordemparanormal.api.ApiEvents;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.common.item.RitualItem;
import com.guga.ordemparanormal.core.registry.OPSounds;
import com.guga.ordemparanormal.core.registry.OPTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AltarTranscender extends HorizontalBlock {
    public static final VoxelShape NORTH = Shapes.or(
            Block.box(0, 0, 0, 16, 3, 16),
            Block.box(4, 3, 4, 12, 17, 12),
            Block.box(0.5, 11, 0, 15.5, 16, 4),
            Block.box(0.5, 13.5, 4, 15.5, 18.5, 11),
            Block.box(0.5, 16, 11, 15.5, 21, 16));
    public static final VoxelShape EAST = Shapes.or(
            Block.box(0, 0, 0, 16, 3, 16),
            Block.box(4, 3, 4, 12, 17, 12),
            Block.box(12, 11, 0.5, 16, 16, 15.5),
            Block.box(5, 13.5, 0.5, 12, 18.5, 15.5),
            Block.box(0, 16, 0.5, 5, 21, 15.5));
    public static final VoxelShape SOUTH = Shapes.or(
            Block.box(0, 0, 0, 16, 3, 16),
            Block.box(4, 3, 4, 12, 17, 12),
            Block.box(0.5, 11, 12, 15.5, 16, 16),
            Block.box(0.5, 13.5, 5, 15.5, 18.5, 12),
            Block.box(0.5, 16, 0, 15.5, 21, 5));
    public static final VoxelShape WEST = Shapes.or(
            Block.box(0, 0, 0, 16, 3, 16),
            Block.box(4, 3, 4, 12, 17, 12),
            Block.box(0, 11, 0.5, 4, 16, 15.5),
            Block.box(4, 13.5, 0.5, 11, 18.5, 15.5),
            Block.box(11, 16, 0.5, 16, 21, 15.5));
    public AltarTranscender() {
        super(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE));
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

        IAbilitiesCap abilities = pPlayer.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        INexCap nex = pPlayer.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);

        if (nex != null && abilities != null){
            if (stack.getItem() instanceof RitualItem item){
                AbstractRitual ritual = item.getRitual();
                if (!abilities.knowsRitual(ritual)){
                    if (nex.getAttribute(ParanormalAttribute.PRESENCE) >= item.getRitual().getPresenceRequired() &&
                            abilities.getKnownRituals().size() < nex.getRitualSlots() &&
                            !stack.getOrCreateTag().getBoolean("ritualLearned")) {
                        CompoundTag tag = new CompoundTag();
                        tag.putBoolean("ritualLearned", true);
                        stack.setTag(tag);
                        abilities.learnRitual(ritual);
                        ApiEvents.syncPlayerPowers(pPlayer);
                        pLevel.playSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), OPSounds.RITUAL_LEARNED.get(), SoundSource.BLOCKS, 0.5f, 1f);
                        pLevel.playSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), SoundEvents.BOOK_PAGE_TURN, SoundSource.BLOCKS, 1f, 1f);

                        if (pPlayer instanceof ServerPlayer serverPlayer) {
                            OPTriggers.LEARN_RITUAL.trigger(serverPlayer);
                        };

                        return InteractionResult.CONSUME;
                    }
                } else {
                    if (stack.getOrCreateTag().getBoolean("ritualLearned")){
                        CompoundTag tag = new CompoundTag();
                        tag.putBoolean("ritualLearned", false);
                        stack.setTag(tag);
                    }
                    abilities.forgetRitual(ritual);
                    ApiEvents.syncPlayerPowers(pPlayer);
                    pLevel.playSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), OPSounds.RITUAL_FORGOTTEN.get(), SoundSource.BLOCKS, 0.5f, 1f);
                    pLevel.playSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), SoundEvents.BOOK_PAGE_TURN, SoundSource.BLOCKS, 1f, 1f);
                    return InteractionResult.CONSUME;
                }
            }
        }
        return InteractionResult.PASS;
    }
}