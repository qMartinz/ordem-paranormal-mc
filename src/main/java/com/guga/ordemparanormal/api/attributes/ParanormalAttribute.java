package com.guga.ordemparanormal.api.attributes;

import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public enum ParanormalAttribute {
    STRENGTH(0, "ordemparanormal.op_attribute.strength"),
    VIGOR(1, "ordemparanormal.op_attribute.vigor"),
    PRESENCE(2, "ordemparanormal.op_attribute.will");

    public final int index;
    public final String name;
    ParanormalAttribute(int index, String name) {
        this.index = index;
        this.name = name;
    }
    public Component getDisplayName(){
        return Component.translatable(name);
    }
    public List<Component> getDescription() {
        List<Component> lines = new ArrayList<>();
        for (int i = 1; i < 4; i++){
            lines.add(Component.translatable(name + ".description.line_" + i));
            if (i == 1) lines.add(CommonComponents.EMPTY);
        }

        return lines;
    }
}
