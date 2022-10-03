package com.guga.ordemparanormal.api;

import com.guga.ordemparanormal.core.registry.OPSounds;
import com.mojang.math.Vector3f;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.damagesource.DamageSource;

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
    public DamageSource getDamage() {
        return switch (this){
            case MEDO -> ElementDamage.DANO_MEDO;
            case SANGUE -> ElementDamage.DANO_SANGUE;
            case CONHECIMENTO -> ElementDamage.DANO_CONHECIMENTO;
            case MORTE -> ElementDamage.DANO_MORTE;
            case ENERGIA -> ElementDamage.DANO_ENERGIA;
            case NONE -> DamageSource.GENERIC;
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
