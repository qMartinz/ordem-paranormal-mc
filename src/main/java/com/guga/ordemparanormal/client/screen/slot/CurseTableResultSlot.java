package com.guga.ordemparanormal.client.screen.slot;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.CurseHelper;
import com.guga.ordemparanormal.api.curses.CurseInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class CurseTableResultSlot extends SlotItemHandler {
    private final ParanormalElement element;
    public CurseTableResultSlot(IItemHandler itemHandler, int index, int x, int y, ParanormalElement element) {
        super(itemHandler, index, x, y);
        this.element = element;
    }
    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }
    @Override
    public void onTake(Player pPlayer, ItemStack pStack) {
        super.onTake(pPlayer, pStack);

        ItemStack cursedItem = getItemHandler().getStackInSlot(2).copy();

        for (CurseInstance curse : CurseHelper.getCurses(getItemHandler().getStackInSlot(2))){
            if (curse.getCurse().getElement() == element &&
                    CurseHelper.getCurses(getItemHandler().getStackInSlot(1)).stream().noneMatch(inst -> inst.getCurse() == curse.getCurse())){

                CurseHelper.removeCurse(cursedItem, curse.getCurse());
            }
        }

        getItemHandler().extractItem(0, 1, false);
        getItemHandler().extractItem(1, 1, false);
        getItemHandler().extractItem(2, getItemHandler().getStackInSlot(2).getCount(), false);
        getItemHandler().insertItem(2, cursedItem, false);
    }
}
