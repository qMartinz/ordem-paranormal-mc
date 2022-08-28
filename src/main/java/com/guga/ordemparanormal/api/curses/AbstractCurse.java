package com.guga.ordemparanormal.api.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCurse {
    protected final String id;
    private final EquipmentSlot[] slots;
    private final ParanormalElement element;
    public final CurseCategory category;
    public final boolean temporary;

    public AbstractCurse(String id, ParanormalElement element, CurseCategory category, boolean temporary, EquipmentSlot... slots) {
        this.id = id;
        this.slots = slots;
        this.element = element;
        this.category = category;
        this.temporary = temporary;
    }
    public String getId() {
        return id;
    }
    public EquipmentSlot[] getSlots() {
        return slots;
    }
    public ParanormalElement getElement() {
        return element;
    }
    public CurseCategory getCategory() {
        return category;
    }
    public boolean isTemporary() {
        return temporary;
    }
    public final boolean isCompatibleWith(AbstractCurse pOther) {
        return this.checkCompatibility(pOther) && pOther.checkCompatibility(this);
    }
    protected boolean checkCompatibility(AbstractCurse pOther) {
        return this != pOther && this.getElement().isCompatible(pOther.getElement());
    }
    public String getTranslationKey(){
        return "ordemparanormal.curse." + this.getId();
    }
    public Component getDisplayName() {
        MutableComponent name = new TranslatableComponent(getTranslationKey());
        switch (element){
            case BLOOD -> name.withStyle(ChatFormatting.DARK_RED);
            case KNOWLEDGE -> name.withStyle(ChatFormatting.GOLD);
            case ENERGY -> name.withStyle(ChatFormatting.DARK_PURPLE);
            case DEATH -> name.withStyle(ChatFormatting.DARK_GRAY);
            case FEAR -> name.withStyle(ChatFormatting.WHITE);
            case NONE -> name.withStyle(ChatFormatting.GRAY);
        }

        return name;
    }
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget) {}
    public void doPostHurt(LivingEntity pTarget, Entity pAttacker) {}
    public void doTick(LivingEntity pUser) {}
    public int getMaxTicks() {
        return 0;
    }
    public int getDamageProtection(DamageSource pSource) {
        return 0;
    }
    public int getDamageBonus() {
        return 0;
    }
    public Map<AttributeModifier, Attribute> getAttributeModifiers(){
        return new HashMap<>();
    }
}
