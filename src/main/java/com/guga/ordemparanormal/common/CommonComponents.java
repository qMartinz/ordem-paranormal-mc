package com.guga.ordemparanormal.common;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.Arrays;
import java.util.Collection;

public class CommonComponents {
    public static final Component NEW_LINE = new TextComponent("\n");
    public static final Component NEX_TITLE = new TranslatableComponent("ordemparanormal.nex.title");
    public static final Component NEX_ABBREVIATION = new TranslatableComponent("ordemparanormal.nex.abbreviation");
    public static final Component HEALTH_POINTS = new TranslatableComponent("ordemparanormal.health_points");
    public static final Component EFFORT_POINTS = new TranslatableComponent("ordemparanormal.effort_points");
    public static final Component EFFORT_POINTS_FULL_NAME = new TranslatableComponent("ordemparanormal.effort_points.full_name");
    public static final Component POWER_POINTS = new TranslatableComponent("ordemparanormal.nex.power_points");
    public static final Component ACTIVE_POWER = new TranslatableComponent("ordemparanormal.power.active");
    public static final Component PASSIVE_POWER = new TranslatableComponent("ordemparanormal.power.passive");
    public static final Component OWNED_POWER = new TranslatableComponent("ordemparanormal.power.owned");
    public static final Component REQUISITES = new TranslatableComponent("ordemparanormal.power.requisites");
    public static final Component ATTRIBUTE_POINTS = new TranslatableComponent("ordemparanormal.nex.attribute_points");
    public static final Component RITUALS = new TranslatableComponent("ordemparanormal.rituals");
    public static final Component INGREDIENT = new TranslatableComponent("ordemparanormal.ritual.ingredient");
    public static final Component RITUAL_LEARNED = new TranslatableComponent("ordemparanormal.ritual_item.ritual_learned");
    public static final Component RITUAL_UNKNOWN = new TranslatableComponent("ordemparanormal.ritual_item.ritual_unknown");
    public static final Component RITUAL_REQUIRES = new TranslatableComponent("ordemparanormal.ritual_item.requires");
    public static final Component CURSED_WITH = new TranslatableComponent("ordemparanormal.ritual_item.cursed_with");
    public static final Component CONSUMES = new TranslatableComponent("ordemparanormal.ritual.consumes");
    public static Component joinLines(Component... pLines) {
        return joinLines(Arrays.asList(pLines));
    }
    public static Component joinLines(Collection<? extends Component> pLines) {
        return ComponentUtils.formatList(pLines, NEW_LINE);
    }
}
