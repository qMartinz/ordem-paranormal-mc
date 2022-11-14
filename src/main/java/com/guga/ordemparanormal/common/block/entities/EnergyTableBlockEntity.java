package com.guga.ordemparanormal.common.block.entities;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.CurseHelper;
import com.guga.ordemparanormal.api.curses.CurseInstance;
import com.guga.ordemparanormal.client.screen.EnergyTableMenu;
import com.guga.ordemparanormal.core.registry.OPBlockEntities;
import com.guga.ordemparanormal.core.registry.OPTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class EnergyTableBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    public boolean isOpen = false;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public EnergyTableBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(OPBlockEntities.ENERGY_TABLE_ENTITY.get(), pWorldPosition, pBlockState);
    }
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new EnergyTableMenu(pContainerId, pInventory, this);
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
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
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }
    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(tag);
    }
    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(level, this.worldPosition, inventory);
    }
    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, EnergyTableBlockEntity pBlockEntity) {
        if(hasRecipe(pBlockEntity)) {
            craftItem(pBlockEntity);
        } else {
            pBlockEntity.itemHandler.setStackInSlot(3, ItemStack.EMPTY);
        }
    }
    private static void craftItem(EnergyTableBlockEntity entity) {
        ItemStack result = entity.itemHandler.getStackInSlot(1).copy();

        for (CurseInstance curse : CurseHelper.getCurses(entity.itemHandler.getStackInSlot(2))){
            if (curse.getCurse().getElement() == ParanormalElement.ENERGIA &&
                    CurseHelper.getCurses(entity.itemHandler.getStackInSlot(1)).stream().noneMatch(inst -> inst.getCurse() == curse.getCurse())){

                CurseHelper.addCurse(result, curse);
            }
        }

        entity.itemHandler.setStackInSlot(3, result);
    }
    public static boolean hasRecipe(EnergyTableBlockEntity entity) {
        boolean hasItemInFirstSlot = entity.itemHandler.getStackInSlot(0).is(OPTags.ENERGY_FUEL);
        boolean hasCursableItemInSlot = !entity.itemHandler.getStackInSlot(1).isEmpty();

        boolean hasCursedItemInSlot = CurseHelper.getCurses(entity.itemHandler.getStackInSlot(2)).stream().anyMatch(inst ->
                inst.getCurse().getCategory().canCurse(entity.itemHandler.getStackInSlot(1).getItem()) &&
                inst.getCurse().getMaxUses() == 0 &&
                CurseHelper.getCurses(entity.itemHandler.getStackInSlot(1)).stream().noneMatch(inst2 -> inst.getCurse() == inst2.getCurse()) &&
                CurseHelper.getCurses(entity.itemHandler.getStackInSlot(1)).stream().allMatch(inst2 -> inst.getCurse().isCompatibleWith(inst2.getCurse())) &&
                inst.getCurse().getElement() == ParanormalElement.ENERGIA);

        return hasItemInFirstSlot && hasCursableItemInSlot && hasCursedItemInSlot;
    }
    public boolean isOpen() {
        return isOpen;
    }
    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("ordemparanormal.block.block_entity.curse_table");
    }
}
