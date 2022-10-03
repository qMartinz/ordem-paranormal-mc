package com.guga.ordemparanormal.api.abilities.ritual;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class RitualCaster implements IRitualCaster {
    private final Map<Integer, AbstractRitual> rituals = new HashMap<>();
    private int slot;
    public ItemStack stack = ItemStack.EMPTY;
    public RitualCaster(ItemStack stack){
        this(stack.getOrCreateTag());
        this.stack = stack;
    }
    @NotNull
    @Override
    public AbstractRitual getRitual() {
        return rituals.get(getCurrentSlot());
    }
    @NotNull
    @Override
    public AbstractRitual getRitual(int slot) {
        return rituals.get(slot);
    }
    @Override
    public int getMaxSlots() {
        return 1;
    }
    @Override
    public int getCurrentSlot() {
        return slot;
    }
    @Override
    public void setCurrentSlot(int slot) {
        this.slot = slot;
        writeItem(stack);
    }
    @Override
    public void setRitual(AbstractRitual ritual, int slot) {
        this.rituals.put(slot, ritual);
        writeItem(stack);
    }
    @Override
    public void setRitual(AbstractRitual ritual) {
        this.rituals.put(getCurrentSlot(), ritual);
        writeItem(stack);
    }
    public CompoundTag writeTag(CompoundTag tag){
        tag.putInt("current_slot", getCurrentSlot());

        for (int i = 0; i < getMaxSlots(); i++){
            tag.putString("ritual_" + i, getRitual(i).getId());
        }
        return tag;
    }
    public RitualCaster(CompoundTag itemTag){
        CompoundTag tag = itemTag.getCompound(getTagID());

        this.slot = tag.contains("current_slot") ? tag.getInt("current_slot") : 1;
        for (int i = 0; i < getMaxSlots(); i++){
            if (tag.contains("ritual_" + i)){
                this.setRitual(OrdemParanormalAPI.getInstance().getRitual(tag.getString("ritual_" + i)), i);
            }
        }
    }
    public void writeItem(ItemStack stack){
        if(stack == null || stack.isEmpty()){
            return;
        }
        CompoundTag tag = stack.getOrCreateTag();
        CompoundTag casterTag = new CompoundTag();
        writeTag(casterTag);
        tag.put(getTagID(), casterTag);
        stack.setTag(tag);
    }
    public void serializeOnTag(CompoundTag tag){
        CompoundTag thisData = writeTag(new CompoundTag());
        tag.put(getTagID(), thisData);
    }
    @Override
    public String getTagID() {
        return "op_ritualCaster";
    }
}
