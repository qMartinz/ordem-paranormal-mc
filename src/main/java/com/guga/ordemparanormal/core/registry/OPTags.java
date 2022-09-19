package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class OPTags {
    public static final TagKey<Item> RAW_MEATS = TagUtil.itemTag(OrdemParanormal.MOD_ID, "raw_meats");
    public static final TagKey<Item> RITUAL_COMPONENTS = TagUtil.itemTag(OrdemParanormal.MOD_ID, "ritual_components");
}
