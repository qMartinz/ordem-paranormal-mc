package com.guga.ordemparanormal.api.attributes;
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
}
