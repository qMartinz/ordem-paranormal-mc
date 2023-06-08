package com.guga.ordemparanormal.api;

import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.mojang.math.Vector3f;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;

import javax.annotation.Nonnull;

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
    public Vector3f getParticleVec3fColor(){
        return switch (this){
            case MEDO, NONE -> new Vector3f(1f, 1f, 1f);
            case SANGUE -> new Vector3f(0.9f, 0f, 0f);
            case CONHECIMENTO -> new Vector3f(1f, 0.9f, 0f);
            case MORTE -> new Vector3f(0f, 0f, 0f);
            case ENERGIA -> new Vector3f(0.7f, 0.1f, 1f);
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
