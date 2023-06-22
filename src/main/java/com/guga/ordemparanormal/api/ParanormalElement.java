package com.guga.ordemparanormal.api;

import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;

import javax.annotation.Nonnull;
import java.awt.*;

public enum ParanormalElement implements StringRepresentable {
    MEDO("medo", 0),
    SANGUE("sangue", 1),
    CONHECIMENTO("conhecimento", 2),
    MORTE("morte", 3),
    ENERGIA("energia", 4),
    NONE("vazio", 5);
    public final String name;
    public final int index;
    ParanormalElement(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public ParanormalDamageSource getDamage() {
        return switch (this){
            case MEDO -> ParanormalDamageSource.DANO_MEDO;
            case SANGUE -> ParanormalDamageSource.DANO_SANGUE;
            case CONHECIMENTO -> ParanormalDamageSource.DANO_CONHECIMENTO;
            case MORTE -> ParanormalDamageSource.DANO_MORTE;
            case ENERGIA -> ParanormalDamageSource.DANO_ENERGIA;
            case NONE -> null;
        };
    }
    public ParanormalElement getOpressingElement() {
        return switch (this){
            case MEDO, NONE -> null;
            case SANGUE -> MORTE;
            case CONHECIMENTO -> SANGUE;
            case MORTE -> ENERGIA;
            case ENERGIA -> CONHECIMENTO;
        };
    }

    public Color getParticleColor(){
        return switch (this){
            case MEDO, NONE -> new Color(255, 255, 255);
            case SANGUE -> new Color(255, 0, 27);
            case CONHECIMENTO -> new Color(255, 166, 0);
            case MORTE -> new Color(0, 0, 0);
            case ENERGIA -> new Color(64, 0, 255);
        };
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
        return Component.translatable(getTranslationKey());
    }
    public static ParanormalElement byName(String name) {
        for (ParanormalElement paranormalElement : values()) {
            if (paranormalElement.name.equals(name)) {
                return paranormalElement;
            }
        }
        return NONE;
    }
    public static ParanormalElement byIndex(int index) {
        for (ParanormalElement paranormalElement : values()) {
            if (paranormalElement.index == index) {
                return paranormalElement;
            }
        }
        return NONE;
    }
    public boolean isCompatible(ParanormalElement other){
        boolean result = true;

        if (!other.equals(this)) {
            switch (other) {
                case SANGUE -> {
                    if (this.equals(MORTE) || this.equals(CONHECIMENTO)) result = false;
                }
                case MORTE -> {
                    if (this.equals(ENERGIA) || this.equals(SANGUE)) result = false;
                }
                case CONHECIMENTO -> {
                    if (this.equals(SANGUE) || this.equals(ENERGIA)) result = false;
                }
                case ENERGIA -> {
                    if (this.equals(CONHECIMENTO) || this.equals(MORTE)) result = false;
                }
                default -> result = true;
            }
        }

        return result;
    }
}
