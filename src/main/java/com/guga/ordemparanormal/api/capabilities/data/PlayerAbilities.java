package com.guga.ordemparanormal.api.capabilities.data;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.ability.PlayerAbility;
import com.guga.ordemparanormal.api.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.util.NBTUtil;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.nbt.CompoundTag;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayerAbilities implements IPlayerCap{
    public Set<AbstractRitual> rituals = new HashSet<>();
    public Set<PlayerAbility> abilities = new HashSet<>();

    public PlayerAbilities(){}

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
    public Collection<PlayerAbility> getAbilities() {
        return abilities;
    }

    @Override
    public void setAbilities(Collection<PlayerAbility> abilities) {
        this.abilities = new HashSet<>(abilities);
    }

    @Override
    public boolean addAbility(PlayerAbility ability) {
        return abilities.add(ability);
    }

    @Override
    public boolean removeAbility(PlayerAbility ability) {
        return abilities.remove(ability);
    }

    @Override
    public boolean hasAbility(PlayerAbility ability) {
        return abilities.contains(ability);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        NBTUtil.writeStrings(tag, "ritual", rituals.stream().map(s -> s.getName()).collect(Collectors.toList()));
        NBTUtil.writeStrings(tag, "ability", abilities.stream().map(s -> s.getName()).collect(Collectors.toList()));

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        OrdemParanormalAPI api = OrdemParanormalAPI.getInstance();

        System.out.println("Rituals in NBT: "+nbt.toString());
        System.out.println("Rituals before: "+rituals.toString());
        Set<AbstractRitual> ritualsFound = new HashSet<>();
        for(String s : NBTUtil.readStrings(nbt, "ritual")){
            if(api.getRitualMap().containsKey(s)){
                rituals.add(api.getRitualMap().get(s));
                ritualsFound.add(api.getRitualMap().get(s));
            }
        }
        System.out.println("Rituals found: "+ritualsFound.toString());
        System.out.println("Rituals after: "+rituals.toString());

        for(String s : NBTUtil.readStrings(nbt, "ability")){
            if(api.getAbilityMap().containsKey(s)){
                abilities.add(api.getAbilityMap().get(s));
            }
        }
    }

    public static PlayerAbilities deserialize(CompoundTag tag){
        PlayerAbilities cap = new PlayerAbilities();
        cap.deserializeNBT(tag);
        return cap;
    }
}
