package com.guga.ordemparanormal.common;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;


import java.util.Arrays;
import java.util.Collection;

public class OPCommonComponents {
    public static final Component NEW_LINE = Component.literal("\n");
    public static final Component NEX_TITLE = Component.translatable("ordemparanormal.nex.title");
    public static final Component NEX_ABBREVIATION = Component.translatable("ordemparanormal.nex.abbreviation");
    public static final Component HEALTH_POINTS = Component.translatable("ordemparanormal.health_points");
    public static final Component EFFORT_POINTS = Component.translatable("ordemparanormal.effort_points");
    public static final Component EFFORT_POINTS_FULL_NAME = Component.translatable("ordemparanormal.effort_points.full_name");
    public static final Component POWER_POINTS = Component.translatable("ordemparanormal.nex.power_points");
    public static final Component ACTIVE_POWER = Component.translatable("ordemparanormal.power.active");
    public static final Component PASSIVE_POWER = Component.translatable("ordemparanormal.power.passive");
    public static final Component OWNED_POWER = Component.translatable("ordemparanormal.power.owned");
    public static final Component REQUISITES = Component.translatable("ordemparanormal.power.requisites");
    public static final Component ATTRIBUTE_POINTS = Component.translatable("ordemparanormal.nex.attribute_points");
    public static final Component RITUALS = Component.translatable("ordemparanormal.rituals");
    public static final Component INGREDIENT = Component.translatable("ordemparanormal.ritual.ingredient");
    public static final Component RITUAL_LEARNED = Component.translatable("ordemparanormal.ritual_item.ritual_learned");
    public static final Component RITUAL_UNKNOWN = Component.translatable("ordemparanormal.ritual_item.ritual_unknown");
    public static final Component RITUAL_REQUIRES = Component.translatable("ordemparanormal.ritual_item.requires");
    public static final Component CURSED_WITH = Component.translatable("ordemparanormal.ritual_item.cursed_with");
    public static final Component CONSUMES = Component.translatable("ordemparanormal.ritual.consumes");
    public static Component joinLines(Component... pLines) {
        return joinLines(Arrays.asList(pLines));
    }
    public static Component joinLines(Collection<? extends Component> pLines) {
        return ComponentUtils.formatList(pLines, NEW_LINE);
    }
}
