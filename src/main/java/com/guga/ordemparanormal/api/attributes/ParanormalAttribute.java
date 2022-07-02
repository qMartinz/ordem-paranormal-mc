package com.guga.ordemparanormal.api.attributes;
public enum ParanormalAttribute {
    STRENGTH(0, "op_attribute.strength"),
    VIGOR(1, "op_attribute.vigor"),
    WILL(2, "op_attribute.will");

    public final int index;
    public final String name;

    ParanormalAttribute(int index, String name) {
        this.index = index;
        this.name = name;
    }
}
