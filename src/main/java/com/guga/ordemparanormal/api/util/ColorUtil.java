package com.guga.ordemparanormal.api.util;

import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class ColorUtil {
    public static Color fromString(String s){
        if (s == null || s.isEmpty())
            return new Color(0, 0, 0);
        String[] arr = s.split(",");
        return new Color(Integer.parseInt(arr[0].trim()), Integer.parseInt(arr[1].trim()), Integer.parseInt(arr[2].trim()));
    }
    public static Color fromNBT(@Nullable CompoundTag tag){
        if(tag == null){
            return new Color(0,0,0);
        }
        return new Color(tag.getInt("r"), tag.getInt("g"), tag.getInt("b"));
    }
}
