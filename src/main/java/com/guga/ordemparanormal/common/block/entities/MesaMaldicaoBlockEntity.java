package com.guga.ordemparanormal.common.block.entities;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.client.particles.AbilitiesParticleOptions;
import com.guga.ordemparanormal.core.registry.OPTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MesaMaldicaoBlockEntity extends BlockEntity implements Container {
    private final ItemStackHandler itemHandler = new ItemStackHandler() {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public final ParanormalElement element;
    protected int fuel = 0;
    public MesaMaldicaoBlockEntity(BlockEntityType<?> type, BlockPos pPos, BlockState pBlockState, ParanormalElement element) {
        super(type, pPos, pBlockState);
        this.element = element;
    }
    public MesaMaldicaoBlockEntity(BlockEntityType<?> type, BlockPos pPos, BlockState pBlockState) {
        this(type, pPos, pBlockState, ParanormalElement.NONE);
    }

    @Override
    public int getContainerSize() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return itemHandler.getStackInSlot(0).isEmpty();
    }

    @Override
    public ItemStack getItem(int pSlot) {
        return itemHandler.getStackInSlot(0);
    }

    public boolean updateBlock() {
        if(level != null) {
            BlockState state = level.getBlockState(worldPosition);
            level.sendBlockUpdated(worldPosition, state, state, 2);
            setChanged();
            return true;
        }
        return false;
    }

    @Override
    public ItemStack removeItem(int pSlot, int pAmount) {
        ItemStack copyStack = itemHandler.getStackInSlot(0).copy().split(pAmount);
        itemHandler.getStackInSlot(0).shrink(pAmount);
        updateBlock();
        return copyStack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int pSlot) {
        ItemStack stack = this.itemHandler.getStackInSlot(0).copy();
        this.itemHandler.setStackInSlot(0, ItemStack.EMPTY);
        updateBlock();
        return stack;
    }

    @Override
    public void setItem(int pSlot, ItemStack pStack) {
        this.itemHandler.setStackInSlot(0, pStack);
        updateBlock();
    }

    @Override
    public boolean canPlaceItem(int pIndex, ItemStack pStack) {
        return this.itemHandler.getStackInSlot(0).isEmpty();
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return false;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    public int getMaxFuelSize() {
        return 64;
    }

    @Override
    public void clearContent() {
        this.itemHandler.setStackInSlot(0, ItemStack.EMPTY);
        updateBlock();
    }

    public ItemStack getStack() {
        return itemHandler.getStackInSlot(0);
    }

    public int getFuel() {
        return fuel;
    }

    public void setStack(ItemStack stack) {
        this.itemHandler.setStackInSlot(0, stack);
        updateBlock();
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
        updateBlock();
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, MesaMaldicaoBlockEntity pBlockEntity) {
        if (pLevel.isClientSide() && pBlockEntity.getFuel() >= 4){
            for (int i = 0; i < 3; i++) {
                pLevel.addParticle(AbilitiesParticleOptions.createData(pBlockEntity.element.getParticleColor(),
                                pBlockEntity.element != ParanormalElement.MORTE),
                        pPos.getX() + 0.5d + (pLevel.random.nextInt(-20, 20) / 100d),
                        pPos.getY() + 0.9d,
                        pPos.getZ() + 0.5d + (pLevel.random.nextInt(-20, 20) / 100d),
                        0d, 0.02d, 0d);
            }
        }

        TagKey<Item> acceptedItems = switch(pBlockEntity.element){
            default -> null;
            case SANGUE -> OPTags.BLOOD_FUEL;
            case CONHECIMENTO -> OPTags.KNOWLEDGE_FUEL;
            case MORTE -> OPTags.DEATH_FUEL;
            case ENERGIA -> OPTags.ENERGY_FUEL;
        };

        List<ItemEntity> fuelItems = new ArrayList<>();
        if(acceptedItems != null) fuelItems = pLevel.getEntitiesOfClass(ItemEntity.class, new AABB(pPos.above()).move(0, -0.5, 0),
                e -> e.getItem().is(acceptedItems));
        fuelItems.forEach(i -> {
            if (pBlockEntity.getFuel() < pBlockEntity.getMaxFuelSize()){
                i.getItem().shrink(1);
                pBlockEntity.setFuel(pBlockEntity.getFuel() + 1);
            }
        });
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        itemHandler.deserializeNBT(compound.getCompound("inventory"));
        fuel = compound.getInt("fuel");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("fuel", fuel);
        super.saveAdditional(tag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("fuel", fuel);
        return tag;
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
