package com.guga.ordemparanormal.api.ritual;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.StringRepresentable;

import javax.annotation.Nonnull;

public enum ParanormalElement implements StringRepresentable, IParanormalElementProvider {
    BLOOD("blood"),
    KNOWLEDGE("knowledge"),
    ENERGY("energy"),
    DEATH("death"),
    FEAR("fear"),
    NONE("none");
    private final String name;
    ParanormalElement(String name) {
        this.name = name;
    }
    @Override
    public ParanormalElement getParanormalElement() {
        return this;
    }
    @Nonnull
    @Override
    public String getSerializedName() {
        return this.name;
    }
    public String getTranslationKey(){
        return "ordemparanormal.element." + getSerializedName();
    }
    public Component getDisplayName() {
        return new TranslatableComponent(getTranslationKey());
    }
    public static ParanormalElement byName(String name) {
        for (ParanormalElement paranormalElement : values()) {
            if (paranormalElement.name.equals(name)) {
                return paranormalElement;
            }
        }
        return NONE;
    }
}
