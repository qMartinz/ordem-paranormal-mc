package com.guga.ordemparanormal.client.screen.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class OPResultSlot extends SlotItemHandler {
    public OPResultSlot(IItemHandler itemHandler, int index, int x, int y) {
        super(itemHandler, index, x, y);
    }
    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }
}
