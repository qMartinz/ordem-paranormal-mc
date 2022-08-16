package com.guga.ordemparanormal.api.capabilities.data;

import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.client.Overlay;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class PlayerNex implements INexCap{
    private int nex;
    private double nexXp;
    private int attributePoints;
    private int powerPoints;
    private double maxEffort = 4;
    private double currentEffort;
    private int[] attributes = new int[]{0, 0, 0};
    private int ritualSlots;
    private final UUID damageBonusModUUID = UUID.randomUUID();
    private final UUID healthBonusModUUID = UUID.randomUUID();
    public int getNex() {
        return nex;
    }
    public void setNex(int nex) {
        this.nex = nex;
    }
    public int getNexPercent() {
        return getNex() == 20 ? 99 : getNex()*5;
    }
    public double getNexXp() {
        return nexXp;
    }
    public void setNexXp(double nexXp) {
        this.nexXp = nexXp;
    }
    public void addNexXp(double amount){
        int oldLevel = this.getNex();
        this.nexXp += amount;
        int attPointsGained = 0;
        int ritualSlotsGained = 0;
        int abilityPointsGained = 0;

        while (this.nexXp >= (this.nex + 1) * 50 && this.nex < 20){
            this.nexXp -= (this.nex + 1) * 50;
            this.nex++;
            if ((nex - 1) % 2 == 0) attPointsGained++;
            if ((nex - 1) % 2 == 0) ritualSlotsGained++;
            if (nex % 2 == 0) abilityPointsGained++;
            if (nex == 20) powerPoints++;
            if (nex == 1) ritualSlotsGained += 2;
        }

        attributePoints += attPointsGained;
        ritualSlots += ritualSlotsGained;
        powerPoints += abilityPointsGained;

        if (attPointsGained > 0 || abilityPointsGained > 0) Overlay.showLvLUpNotification();
    }
    public int getAttributePoints() {
        return attributePoints;
    }
    public void setAttributePoints(int attributePoints) {
        this.attributePoints = attributePoints;
    }
    public int getPowerPoints() {
        return powerPoints;
    }
    public void setPowerPoints(int powerPoints) {
        this.powerPoints = powerPoints;
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

    /**
     * Sincroniza os modificadores de atributo dados pelos atributos paranormais
     *
     * @param player o jogador que deseja sincronizar
     */
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
        setMaxEffort(4 + attributes[ParanormalAttribute.PRESENCE.index]);
    }

    public void setRitualSlots(int ritualSlots) {
        this.ritualSlots = ritualSlots;
    }
    public int getRitualSlots() {
        return ritualSlots;
    }

    /**
     * Copia os dados de outra capability e os aplica para essa
     *
     * @param source a capability que você deseja extrair os dados
     */
    public void copyFrom(INexCap source){
        nex = source.getNex();
        nexXp = source.getNexXp();
        attributePoints = source.getAttributePoints();
        powerPoints = source.getPowerPoints();
        maxEffort = source.getMaxEffort();
        currentEffort = source.getCurrentEffort();
        attributes = source.getAttributes();
        ritualSlots = source.getRitualSlots();
    }
    public CompoundTag serializeNBT(){
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("nex_percent", nex);
        nbt.putDouble("nex_xp", nexXp);
        nbt.putInt("attribute_points", attributePoints);
        nbt.putInt("ability_points", powerPoints);

        CompoundTag effortTag = new CompoundTag();
        effortTag.putDouble("max_effort", maxEffort);
        effortTag.putDouble("current_effort", currentEffort);
        nbt.put("effort", effortTag);

        CompoundTag attributeTag = new CompoundTag();
        attributeTag.putInt("strength", attributes[0]);
        attributeTag.putInt("vigor", attributes[1]);
        attributeTag.putInt("presence", attributes[2]);
        nbt.put("paranormal_attributes", attributeTag);

        nbt.putInt("ritual_slots", ritualSlots);

        return nbt;
    }
    public void deserializeNBT(CompoundTag nbt){
        nex = nbt.getInt("nex_percent");
        nexXp = nbt.getDouble("nex_xp");
        attributePoints = nbt.getInt("attribute_points");
        powerPoints = nbt.getInt("ability_points");

        CompoundTag effortTag = nbt.getCompound("effort");
        maxEffort = effortTag.getDouble("max_effort");
        currentEffort = effortTag.getDouble("current_effort");

        CompoundTag attributeTag = nbt.getCompound("paranormal_attributes");
        attributes[0] = attributeTag.getInt("strength");
        attributes[1] = attributeTag.getInt("vigor");
        attributes[2] = attributeTag.getInt("presence");

        ritualSlots = nbt.getInt("ritual_slots");
    }
}
