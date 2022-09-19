package com.guga.ordemparanormal.common.item;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class RitualComponent extends Item {
    public final ParanormalElement element;
    public RitualComponent(ParanormalElement element) {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON).durability(15).tab(OrdemParanormal.RITUALS_TAB).setNoRepair());
        this.element = element;
    }
}
