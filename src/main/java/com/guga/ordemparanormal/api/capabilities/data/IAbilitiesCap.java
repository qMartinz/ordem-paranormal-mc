package com.guga.ordemparanormal.api.capabilities.data;

import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public interface IAbilitiesCap extends INBTSerializable<CompoundTag> {
    Collection<AbstractRitual> getKnownRituals();
    void setKnownRituals(Collection<AbstractRitual> rituals);
    boolean learnRitual(AbstractRitual ritual);
    boolean forgetRitual(AbstractRitual ritual);
    boolean knowsRitual(AbstractRitual ritual);
    Collection<PlayerPower> getPowers();
    PlayerPower getPower(int slot);
    void setPowers(Collection<PlayerPower> power);
    boolean addPower(PlayerPower power);
    boolean removePower(PlayerPower power);
    boolean hasPower(PlayerPower ability);
    Map<Integer, PlayerPower> getActivePowers();
    PlayerPower getActivePower(int slot);
    void setActivePowers(Map<Integer, PlayerPower> powers);
    void setActivePower(PlayerPower power, int slot);
    void syncAttributeMod(Player player, Attribute attribute, UUID uuid);
    void syncAttributeMods(Player player);
    void clearPowers();
}
