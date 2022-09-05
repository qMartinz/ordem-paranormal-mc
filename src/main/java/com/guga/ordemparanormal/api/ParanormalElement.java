package com.guga.ordemparanormal.api;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.damagesource.DamageSource;

import javax.annotation.Nonnull;

public enum ParanormalElement implements StringRepresentable {
    FEAR("fear", ElementDamage.FEAR_DAMAGE, 0),
    BLOOD("blood", ElementDamage.BLOOD_DAMAGE, 1),
    KNOWLEDGE("knowledge", ElementDamage.KNOWLEDGE_DAMAGE, 2),
    DEATH("death", ElementDamage.DEATH_DAMAGE, 3),
    ENERGY("energy", ElementDamage.ENERGY_DAMAGE, 4),
    NONE("none", DamageSource.GENERIC, 5);
    private final String name;
    private final DamageSource equivalentDamage;
    public final int index;
    ParanormalElement(String name, DamageSource equivalentDamage, int index) {
        this.name = name;
        this.equivalentDamage = equivalentDamage;
        this.index = index;
    }
    public DamageSource getEquivalentDamage() {
        return equivalentDamage;
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
                case BLOOD -> {
                    if (this.equals(DEATH) || this.equals(KNOWLEDGE)) result = false;
                }
                case DEATH -> {
                    if (this.equals(ENERGY) || this.equals(BLOOD)) result = false;
                }
                case KNOWLEDGE -> {
                    if (this.equals(BLOOD) || this.equals(ENERGY)) result = false;
                }
                case ENERGY -> {
                    if (this.equals(KNOWLEDGE) || this.equals(DEATH)) result = false;
                }
                default -> result = true;
            }
        }

        return result;
    }
}
