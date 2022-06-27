package com.guga.ordemparanormal.common.capabilities.nexplayer;

import com.guga.ordemparanormal.client.NexOverlay;
import com.guga.ordemparanormal.common.abilities.ParanormalAttribute;
import com.guga.ordemparanormal.common.network.SyncNex;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nonnull;
import java.util.UUID;

import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE;

public class NexModel implements INBTSerializable<CompoundTag> {
    private final Player player;
    private final Minecraft minecraft = Minecraft.getInstance();
    public int nexLevel;
    public double nexXP;
    public final int maxNex = 99;
    public int abilityPoints;
    public double maxEffortPoints = 4;
    public double curEffortPoints = 4;
    public int[] attributes = new int[]{0, 0, 0};
    private static final UUID damageBonusModUUID = UUID.randomUUID();
    private static final UUID healthBonusModUUID = UUID.randomUUID();
    public NexModel(@Nonnull Player player) {
        this.player = player;
    }
    public int getNexLevel(){ return this.nexLevel; }
    public void setNexLevel(int level){
        int oldLevel = this.getNexLevel();
        int pointsGained = 0;

        while (this.nexLevel != level){
            if (this.nexLevel < level) {
                this.nexLevel++;
                if (nexLevel % 5 == 0) pointsGained++;
            } else {
                this.nexLevel--;
                if (nexLevel % 5 == 0) pointsGained--;
            }
        }

        if (level == 99 && oldLevel < level) pointsGained++;

        abilityPoints += pointsGained;

        if (pointsGained > 0) NexOverlay.showLvLUpNotification();

        onDataUpdated();
    }
    public boolean isMaxNex(){ return this.nexLevel == this.maxNex; }
    public double getNexXp(){ return this.nexXP; }
    public void giveNexXP(double amount){
        int oldLevel = this.getNexLevel();
        this.nexXP += amount;
        int pointsGained = 0;

        while (this.nexXP >= (this.nexLevel + 1) * 10){
            this.nexXP -= (this.nexLevel + 1) * 10;
            this.nexLevel++;
            if (nexLevel % 5 == 0) pointsGained++;
        }

        if (nexLevel == 99) pointsGained++;

        abilityPoints += pointsGained;

        if (pointsGained > 0) NexOverlay.showLvLUpNotification();

        onDataUpdated();
    }
    public double getEffortPoints() {
        return this.curEffortPoints;
    }
    public double getMaxEffortPoints(){
        return this.maxEffortPoints;
    }
    public void setCurEffortPoints(double amount){
        this.curEffortPoints = Math.min(maxEffortPoints, Math.max(amount, 0));
    }
    public void setMaxEffortPoints(double amount){
        this.maxEffortPoints = Math.max(amount, 0);
    }
    public int getAbilityPoints(){
        return this.abilityPoints;
    }
    public void setAbilityPoints(int points){
        int oldPoints = this.getAbilityPoints();
        this.abilityPoints = points;

        if (points > oldPoints) NexOverlay.showLvLUpNotification();
        onDataUpdated();
    }
    public int getAttribute(ParanormalAttribute paranormalAttribute) {
        return attributes[paranormalAttribute.index];
    }
    public void setAttribute(ParanormalAttribute paranormalAttribute, int level) {
        attributes[paranormalAttribute.index] = level;
        syncAttributeMods();

        onDataUpdated();
    }
    public void increaseAttribute(ParanormalAttribute paranormalAttribute){
        attributes[paranormalAttribute.index]++;
        syncAttributeMods();

        onDataUpdated();
    }

    public void syncAttributeMods(){
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
        setMaxEffortPoints(4 + attributes[ParanormalAttribute.WILL.index]);
    }
    public static NexModel get(Player player){
        return player.getCapability(NexCapability.INSTANCE).orElseThrow(() ->
                new IllegalArgumentException("Player " + player.getName().getContents() + " does not have a NeX Model.")
        );
    }
    public static NexModel get() {
        return Minecraft.getInstance().player.getCapability(NexCapability.INSTANCE).orElseThrow(() ->
                new IllegalArgumentException("Player does not have a NeX Model!")
        );
    }
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("nex_level", this.nexLevel);
        nbt.putDouble("nex_xp", this.nexXP);
        nbt.putInt("ability_points", this.abilityPoints);

        nbt.putInt("strength", attributes[0]);
        nbt.putInt("vigor", attributes[1]);
        nbt.putInt("will", attributes[2]);

        nbt.putDouble("max_effort", this.maxEffortPoints);
        nbt.putDouble("current_effort", this.curEffortPoints);

        return nbt;
    }
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.nexLevel = nbt.getInt("nex_level");
        this.nexXP = nbt.getDouble("nex_xp");
        this.abilityPoints = nbt.getInt("ability_points");

        attributes[0] = nbt.getInt("strength");
        attributes[1] =  nbt.getInt("vigor");
        attributes[2] =  nbt.getInt("will");

        this.maxEffortPoints = nbt.getDouble("max_effort");
        this.curEffortPoints = nbt.getDouble("current_effort");
    }

    public void onDataUpdated() {
        if (!player.level.isClientSide)
            OrdemParanormal.network.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new SyncNex(this.serializeNBT()));
    }
}