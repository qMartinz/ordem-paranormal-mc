package com.guga.ordemparanormal.api.capabilities.data;

import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.client.NexOverlay;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class PlayerNex {
    private int nexPercent;
    private double nexXp;
    private int abilityPoints;
    private double maxEffort = 4;
    private double currentEffort;
    private int[] attributes = new int[]{0, 0, 0};
    private final UUID damageBonusModUUID = UUID.randomUUID();
    private final UUID healthBonusModUUID = UUID.randomUUID();
    public int getNexPercent() {
        return nexPercent;
    }
    public void setNexPercent(int nexPercent) {
        this.nexPercent = nexPercent;
    }
    public double getNexXp() {
        return nexXp;
    }
    public void setNexXp(double nexXp) {
        this.nexXp = nexXp;
    }
    public void addNexXp(double amount){
        int oldLevel = this.getNexPercent();
        this.nexXp += amount;
        int pointsGained = 0;

        while (this.nexXp >= (this.nexPercent + 1) * 10){
            this.nexXp -= (this.nexPercent + 1) * 10;
            this.nexPercent++;
            if (nexPercent % 5 == 0) pointsGained++;
        }

        if (nexPercent == 99) pointsGained++;

        abilityPoints += pointsGained;

        if (pointsGained > 0) NexOverlay.showLvLUpNotification();
    }
    public int getAbilityPoints() {
        return abilityPoints;
    }
    public void setAbilityPoints(int abilityPoints) {
        this.abilityPoints = abilityPoints;
    }
    public double getMaxEffort() {
        return maxEffort;
    }
    public void setMaxEffort(double maxEffort) {
        this.maxEffort = Math.max(maxEffort, 1);;
    }
    public double getCurrentEffort() {
        return currentEffort;
    }
    public void setCurrentEffort(double currentEffort) {
        this.currentEffort = Math.min(maxEffort, Math.max(currentEffort, 0));
    }
    public int[] getAttributes() {
        return attributes;
    }
    public void setAttributes(int[] attributes) {
        this.attributes = attributes;
    }
    public int getAttribute(ParanormalAttribute paranormalAttribute) {
        return attributes[paranormalAttribute.index];
    }
    public void setAttribute(ParanormalAttribute paranormalAttribute, int level) {
        attributes[paranormalAttribute.index] = level;
    }
    public void syncAttributeMods(Player player){
        //Strength
        // Modificador de dano
        if (player.getAttribute(Attributes.ATTACK_DAMAGE).getModifier(damageBonusModUUID) == null){
            player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(new AttributeModifier(damageBonusModUUID, "OPDamageBonus", attributes[ParanormalAttribute.STRENGTH.index], AttributeModifier.Operation.ADDITION));
        } else {
            player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(damageBonusModUUID);
            player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(new AttributeModifier(damageBonusModUUID, "OPDamageBonus", attributes[ParanormalAttribute.STRENGTH.index], AttributeModifier.Operation.ADDITION));
        }

        //Vigor
        // Modificador de vida
        if (player.getAttribute(Attributes.MAX_HEALTH).getModifier(healthBonusModUUID) == null){
            player.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(new AttributeModifier(healthBonusModUUID, "OPHealthBonus", attributes[ParanormalAttribute.VIGOR.index]*2, AttributeModifier.Operation.ADDITION));
        } else {
            player.getAttribute(Attributes.MAX_HEALTH).removeModifier(healthBonusModUUID);
            player.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(new AttributeModifier(healthBonusModUUID, "OPHealthBonus", attributes[ParanormalAttribute.VIGOR.index]*2, AttributeModifier.Operation.ADDITION));
        }

        //Will
        setMaxEffort(4 + attributes[ParanormalAttribute.WILL.index]);
    }
    public void copyFrom(PlayerNex source){
        nexPercent = source.nexPercent;
        nexXp = source.nexXp;
        abilityPoints = source.abilityPoints;
        maxEffort = source.maxEffort;
        currentEffort = source.currentEffort;
        attributes = source.attributes;
    }
    public CompoundTag saveNBTData(){
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("nex_percent", nexPercent);
        nbt.putDouble("nex_xp", nexXp);
        nbt.putInt("ability_points", abilityPoints);

        CompoundTag effortTag = new CompoundTag();
        effortTag.putDouble("max_effort", maxEffort);
        effortTag.putDouble("current_effort", currentEffort);
        nbt.put("effort", effortTag);

        CompoundTag attributeTag = new CompoundTag();
        attributeTag.putInt("strength", attributes[0]);
        attributeTag.putInt("vigor", attributes[1]);
        attributeTag.putInt("will", attributes[2]);
        nbt.put("paranormal_attributes", attributeTag);

        return nbt;
    }
    public void loadNBTData(CompoundTag nbt){
        nexPercent = nbt.getInt("nex_percent");
        nexXp = nbt.getDouble("nex_xp");
        abilityPoints = nbt.getInt("ability_points");

        CompoundTag effortTag = nbt.getCompound("effort");
        maxEffort = effortTag.getDouble("max_effort");
        currentEffort = effortTag.getDouble("current_effort");

        CompoundTag attributeTag = nbt.getCompound("paranormal_attributes");
        attributes[0] = attributeTag.getInt("strength");
        attributes[1] = attributeTag.getInt("vigor");
        attributes[2] = attributeTag.getInt("will");
    }
}
