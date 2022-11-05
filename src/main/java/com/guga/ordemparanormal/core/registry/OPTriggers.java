package com.guga.ordemparanormal.core.registry;

import com.teamabnormals.blueprint.common.advancement.EmptyTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.resources.ResourceLocation;

public class OPTriggers {
    public static final EmptyTrigger ENTER_FOG = register(new EmptyTrigger(new ResourceLocation("nex_5")));
    public static <T extends CriterionTrigger<?>> T register(T trigger) {
        return CriteriaTriggers.register(trigger);
    }
}
