package com.guga.ordemparanormal.api.capabilities.data;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.abilities.power.AttributeModPower;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.util.NBTUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerAbilities implements IAbilitiesCap {
    public Set<AbstractRitual> rituals = new HashSet<>();
    public Set<PlayerPower> powers = new HashSet<>();
    public Map<Integer, PlayerPower> activePowers = new HashMap<>();
    private final UUID healthBonusModUUID = UUID.randomUUID();
    private final UUID knockbackResistBonusModUUID = UUID.randomUUID();
    private final UUID speedBonusModUUID = UUID.randomUUID();
    private final UUID damageBonusModUUID = UUID.randomUUID();
    private final UUID knockbackBonusModUUID = UUID.randomUUID();
    private final UUID attackSpeedBonusModUUID = UUID.randomUUID();
    private final UUID toughnessBonusModUUID = UUID.randomUUID();
    @Override
    public Collection<AbstractRitual> getKnownRituals() {
        return rituals;
    }
    @Override
    public void setKnownRituals(Collection<AbstractRitual> rituals) {
        this.rituals = new HashSet<>(rituals);
    }
    @Override
    public boolean learnRitual(AbstractRitual ritual) {
        return rituals.add(ritual);
    }
    @Override
    public boolean forgetRitual(AbstractRitual ritual) {
        return rituals.remove(ritual);
    }
    @Override
    public boolean knowsRitual(AbstractRitual ritual) {
        return rituals.contains(ritual);
    }
    @Override
    public Collection<PlayerPower> getPowers() {
        return powers;
    }
    @Override
    public PlayerPower getPower(int slot) {
        if (powers.stream().toList().get(slot) == null){
            return PlayerPower.EMPTY;
        }
        return powers.stream().toList().get(slot);
    }
    @Override
    public void setPowers(Collection<PlayerPower> powers) {
        this.powers = new HashSet<>(powers);
    }
    @Override
    public boolean addPower(PlayerPower power){
        return powers.add(power);
    }
    @Override
    public boolean removePower(PlayerPower power){
        return powers.remove(power);
    }
    @Override
    public boolean hasPower(PlayerPower ability) {
        return powers.contains(ability);
    }
    @Override
    public Map<Integer, PlayerPower> getActivePowers() {
        return activePowers;
    }
    @Override
    public PlayerPower getActivePower(int slot){
        if (activePowers.get(slot) == null){
            return PlayerPower.EMPTY;
        }
        return activePowers.get(slot);
    }
    @Override
    public void setActivePowers(Map<Integer, PlayerPower> powers) {
        this.activePowers = new HashMap<>(powers);
    }
    @Override
    public void setActivePower(PlayerPower ability, int slot) {
        activePowers.values().removeIf(ab -> ab.equals(ability));
        activePowers.put(slot, ability);
    }
    @Override
    public void syncAttributeMod(Player player, Attribute attribute, UUID uuid) {
        double attributeOriginal = player.getAttribute(attribute).getValue();
        List<AttributeModPower> powers = new ArrayList<>(this.powers.stream()
                .filter(p -> p instanceof AttributeModPower)
                .map(power -> (AttributeModPower) power).toList());

        List<AttributeModifier> modifiers = powers.stream().map(p -> p.getAttributeModifiers().get(attribute)).toList();

        if (!modifiers.isEmpty()) {
            double d0 = attributeOriginal;

            for (AttributeModifier attributemodifier : modifiers.stream().filter(m -> m != null && m.getOperation() == AttributeModifier.Operation.ADDITION).toList()) {
                d0 += attributemodifier.getAmount();
            }

            double d1 = d0;

            for (AttributeModifier attributemodifier1 : modifiers.stream().filter(m -> m != null && m.getOperation() == AttributeModifier.Operation.MULTIPLY_BASE).toList()) {
                d1 += d0 * attributemodifier1.getAmount();
            }

            for (AttributeModifier attributemodifier2 : modifiers.stream().filter(m -> m != null && m.getOperation() == AttributeModifier.Operation.MULTIPLY_TOTAL).toList()) {
                d1 *= 1.0D + attributemodifier2.getAmount();
            }

            AttributeModifier modifier = new AttributeModifier(uuid, "paranormal_powers_mod", d1 - attributeOriginal, AttributeModifier.Operation.ADDITION);
            if (player.getAttribute(attribute).getModifier(uuid) == null) {
                player.getAttribute(attribute).addTransientModifier(modifier);
            } else {
                player.getAttribute(attribute).removeModifier(uuid);
                player.getAttribute(attribute).addTransientModifier(modifier);
            }
        }
    }
    @Override
    public void syncAttributeMods(Player player) {
        syncAttributeMod(player, Attributes.MAX_HEALTH, healthBonusModUUID);
        syncAttributeMod(player, Attributes.KNOCKBACK_RESISTANCE, knockbackResistBonusModUUID);
        syncAttributeMod(player, Attributes.MOVEMENT_SPEED, speedBonusModUUID);
        syncAttributeMod(player, Attributes.ATTACK_DAMAGE, damageBonusModUUID);
        syncAttributeMod(player, Attributes.ATTACK_KNOCKBACK, knockbackBonusModUUID);
        syncAttributeMod(player, Attributes.ATTACK_SPEED, attackSpeedBonusModUUID);
        syncAttributeMod(player, Attributes.ARMOR_TOUGHNESS, toughnessBonusModUUID);
    }
    @Override
    public void clearPowers() {
        this.rituals.clear();
        this.powers.clear();
        this.activePowers.clear();
    }
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        NBTUtil.writeStrings(tag, "ritual", rituals.stream().map(AbstractRitual::getId).collect(Collectors.toList()));
        NBTUtil.writeStrings(tag, "power", powers.stream().map(PlayerPower::getId).collect(Collectors.toList()));

        for (int i = 0; i < 5; i++){
            if (activePowers.containsKey(i)) {
                tag.putString("active_power_" + i, getActivePower(i).getId());
            }
        }

        return tag;
    }
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        clearPowers();
        OrdemParanormalAPI api = OrdemParanormalAPI.getInstance();

        for(String s : NBTUtil.readStrings(nbt, "ritual")){
            if(api.getRitualMap().containsKey(s)){
                rituals.add(api.getRitualMap().get(s));
            }
        }

        for(String s : NBTUtil.readStrings(nbt, "power")){
            if(api.getPowerMap().containsKey(s)){
                powers.add(api.getPowerMap().get(s));
            }
        }

        for (int i = 0; i < 5; i++){
            if (nbt.contains("active_power_" + i) && powers.contains(OrdemParanormalAPI.getInstance().getPower(nbt.getString("active_power_" + i)))){
                this.setActivePower(OrdemParanormalAPI.getInstance().getPower(nbt.getString("active_power_" + i)), i);
            }
        }
    }
}
