package com.guga.ordemparanormal.api.capabilities.data;

import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.UUID;

public interface INexCap extends INBTSerializable<CompoundTag> {
    int getNex();
    void setNex(int nex);
    int getNexPercent();
    double getNexXp();
    void setNexXp(double nexXp);
    void addNexXp(double amount);
    int getAttributePoints();
    void setAttributePoints(int attributePoints);
    int getPowerPoints();
    void setPowerPoints(int powerPoints);
    double getMaxEffort();
    void setMaxEffort(double maxEffort);
    double getCurrentEffort();
    void setCurrentEffort(double currentEffort);
    void addEffortModifier(AttributeModifier modifier);
    void removeEffortModifier(AttributeModifier modifier);
    void removeEffortModifier(UUID uuid);
    void clearEffortModifiers();
    boolean hasEffortModifier(UUID uuid);
    boolean hasEffortModifier(AttributeModifier modifier);
    int[] getAttributes();
    void setAttributes(int[] attributes);
    int getAttribute(ParanormalAttribute paranormalAttribute);
    void setAttribute(ParanormalAttribute paranormalAttribute, int level);
    void syncAttributeMods(Player player);
    void syncEffortMods();
    void setRitualSlots(int ritualSlots);
    int getRitualSlots();
    void setPowerCooldown(int ticks);
    int getPowerCooldown();
    void copyFrom(INexCap source);
    CompoundTag serializeNBT();
    void deserializeNBT(CompoundTag nbt);
}
