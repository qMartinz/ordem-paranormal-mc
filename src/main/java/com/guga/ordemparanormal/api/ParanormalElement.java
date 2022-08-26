package com.guga.ordemparanormal.api;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.damagesource.DamageSource;

import javax.annotation.Nonnull;

import static com.guga.ordemparanormal.api.ElementDamage.ENERGY_DAMAGE;

public enum ParanormalElement implements StringRepresentable {
    BLOOD("blood", ElementDamage.BLOOD_DAMAGE),
    KNOWLEDGE("knowledge", ElementDamage.KNOWLEDGE_DAMAGE),
    ENERGY("energy", ElementDamage.ENERGY_DAMAGE),
    DEATH("death", ElementDamage.DEATH_DAMAGE),
    FEAR("fear", ElementDamage.FEAR_DAMAGE),
    NONE("none", DamageSource.GENERIC);
    private final String name;
    private final DamageSource equivalentDamage;
    ParanormalElement(String name, DamageSource equivalentDamage) {
        this.name = name;
        this.equivalentDamage = equivalentDamage;
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
