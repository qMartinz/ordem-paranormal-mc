package com.guga.ordemparanormal.api.capabilities.data;

import com.guga.ordemparanormal.api.ability.PlayerAbility;
import com.guga.ordemparanormal.api.ritual.AbstractRitual;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Collection;

public interface IPlayerCap extends INBTSerializable<CompoundTag> {
    Collection<AbstractRitual> getKnownRituals();
    void setKnownRituals(Collection<AbstractRitual> rituals);
    boolean learnRitual(AbstractRitual ritual);
    boolean forgetRitual(AbstractRitual ritual);
    boolean knowsRitual(AbstractRitual ritual);
    Collection<PlayerAbility> getAbilities();
    void setAbilities(Collection<PlayerAbility> abilities);
    boolean addAbility(PlayerAbility ability);
    boolean removeAbility(PlayerAbility ability);
    boolean hasAbility(PlayerAbility ability);
}
