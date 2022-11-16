package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.teamabnormals.blueprint.common.advancement.EmptyTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.resources.ResourceLocation;

public class OPTriggers {
    public static final EmptyTrigger ENTER_FOG = new EmptyTrigger(new ResourceLocation(OrdemParanormal.MOD_ID, "enter_fog"));
    public static final EmptyTrigger LEARN_RITUAL = new EmptyTrigger(new ResourceLocation(OrdemParanormal.MOD_ID, "learn_ritual"));
    public static final EmptyTrigger RECEIVE_POWER = new EmptyTrigger(new ResourceLocation(OrdemParanormal.MOD_ID, "receive_power"));
    public static final EmptyTrigger PARANORMAL_CREATURE = new EmptyTrigger(new ResourceLocation(OrdemParanormal.MOD_ID, "paranormal_creature"));
    public static <T extends CriterionTrigger<?>> T register(T trigger) {
        return CriteriaTriggers.register(trigger);
    }
    public static void init() {
        CriteriaTriggers.register(ENTER_FOG);
        CriteriaTriggers.register(LEARN_RITUAL);
        CriteriaTriggers.register(RECEIVE_POWER);
        CriteriaTriggers.register(PARANORMAL_CREATURE);
    }
}
