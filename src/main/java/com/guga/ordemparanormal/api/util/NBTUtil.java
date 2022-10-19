package com.guga.ordemparanormal.api.util;

import com.guga.ordemparanormal.api.curses.CurseInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NBTUtil {
    public static CompoundTag storeBlockPos(CompoundTag tag, String prefix, BlockPos pos){
        if(pos == null)
            return tag;
        tag.putDouble(prefix + "_x", pos.getX());
        tag.putDouble(prefix + "_y", pos.getY());
        tag.putDouble(prefix + "_z", pos.getZ());
        return tag;
    }
    public static CompoundTag removeBlockPos(CompoundTag tag, String prefix){
        tag.remove(prefix + "_x");
        tag.remove(prefix + "_y");
        tag.remove(prefix + "_z");
        return tag;
    }
    public static BlockPos getBlockPos(CompoundTag tag, String prefix){
        return new BlockPos(tag.getDouble(prefix + "_x"), tag.getDouble(prefix + "_y"),tag.getDouble(prefix + "_z"));
    }
    public static boolean hasBlockPos(CompoundTag tag, String prefix){
        return tag.contains(prefix + "_x");
    }
    public static List<String> readStrings(CompoundTag tag, String prefix){
        List<String> strings = new ArrayList<>();
        if(tag == null)
            return strings;

        for(String s : tag.getAllKeys()){
            if(s.contains(prefix)){
                strings.add(tag.getString(s));
            }
        }
        return strings;
    }
    public static List<CompoundTag> readCurses(CompoundTag tag){
        List<CompoundTag> curses = new ArrayList<>();
        if(tag == null)
            return curses;

        for(String s : tag.getAllKeys()){
            if(s.contains("curse_")){
                curses.add(tag.getCompound(s));
            }
        }
        return curses;
    }
    public static void writeStrings(CompoundTag tag, String prefix, Collection<String> strings){
        int i = 0;
        for(String s : strings){
            tag.putString(prefix + "_" + i, s);
            i++;
        }
    }
    public static void writeCurses(CompoundTag tag, Collection<CurseInstance> instances){
        int i = 0;
        for(CurseInstance c : instances){
            CompoundTag curse = new CompoundTag();
            curse.putString("id", c.getCurse().getId());
            curse.putInt("uses", c.getUses());

            tag.put("curse_" + i, curse);
            i++;
        }
    }
}
