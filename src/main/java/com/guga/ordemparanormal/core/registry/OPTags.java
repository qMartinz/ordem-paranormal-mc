package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class OPTags {
    public static final TagKey<Item> RAW_MEATS = TagUtil.itemTag(OrdemParanormal.MOD_ID, "raw_meats");
    public static final TagKey<Item> RITUAL_COMPONENTS = TagUtil.itemTag(OrdemParanormal.MOD_ID, "ritual_components");
    public static final TagKey<Item> BLOOD_FUEL = TagUtil.itemTag(OrdemParanormal.MOD_ID, "blood_fuel");
    public static final TagKey<Item> ENERGY_FUEL = TagUtil.itemTag(OrdemParanormal.MOD_ID, "energy_fuel");
    public static final TagKey<Item> DEATH_FUEL = TagUtil.itemTag(OrdemParanormal.MOD_ID, "death_fuel");
    public static final TagKey<Item> KNOWLEDGE_FUEL = TagUtil.itemTag(OrdemParanormal.MOD_ID, "knowledge_fuel");
}
