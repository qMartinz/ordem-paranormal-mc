package com.guga.ordemparanormal.api.curses;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.INBTSerializable;

public final class CurseInstance implements INBTSerializable<CompoundTag> {
    private AbstractCurse curse;
    private int uses;

    public CurseInstance(AbstractCurse curse) {
        this.curse = curse;
        this.uses = 0;
    }
    public CurseInstance(AbstractCurse curse, int uses){
        this.curse = curse;
        this.uses = uses;
    }
    public AbstractCurse getCurse() {
        return curse;
    }
    public void setCurse(AbstractCurse curse) {
        this.curse = curse;
    }
    public int getUses() {
        return uses;
    }
    public void setUses(int uses) {
        this.uses = uses;
    }
    public void useOrRemove(ItemStack pStack){
        if (curse.isTemporary()) {
            int newUses = uses + 1;
            if (newUses == curse.getMaxUses()) {
                CurseHelper.removeCurse(pStack, this.curse);
            } else {
                this.uses = newUses;
                CurseHelper.addCurse(pStack, this);
            }
        }
    }
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putString("id", curse.id.toString());
        nbt.putInt("uses", uses);

        return nbt;
    }
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        curse = OrdemParanormalAPI.getInstance().getCurse(ResourceLocation.tryParse(nbt.getString("id")));
        uses = nbt.getInt("uses");
    }
}
