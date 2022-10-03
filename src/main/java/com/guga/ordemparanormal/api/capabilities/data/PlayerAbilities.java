package com.guga.ordemparanormal.api.capabilities.data;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.util.NBTUtil;
import net.minecraft.nbt.CompoundTag;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerPowers implements IPowerCap {
    public Set<AbstractRitual> rituals = new HashSet<>();
    public Set<PlayerPower> powers = new HashSet<>();
    public Map<Integer, PlayerPower> activePowers = new HashMap<>();
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
