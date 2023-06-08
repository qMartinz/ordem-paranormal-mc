package com.guga.ordemparanormal.common.item;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.core.registry.OPCreativeTabs;
import com.guga.ordemparanormal.core.registry.OPSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import javax.annotation.Nullable;

public class RitualComponent extends Item {
    public final ParanormalElement element;
    public RitualComponent(ParanormalElement element) {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON).durability(15).tab(OPCreativeTabs.RITUALS_TAB).setNoRepair());
        this.element = element;
    }
    @Nullable
    public SoundEvent getUsedSound(){
        return switch (this.element){
            case MEDO, NONE -> null;
            case SANGUE -> OPSounds.INGREDIENT_BLOOD.get();
            case CONHECIMENTO -> OPSounds.INGREDIENT_KNOWLEDGE.get();
            case MORTE -> OPSounds.INGREDIENT_DEATH.get();
            case ENERGIA -> OPSounds.INGREDIENT_ENERGY.get();
        };
    }
}
