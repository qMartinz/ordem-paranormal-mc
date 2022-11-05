package com.guga.ordemparanormal.common.triggers;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;

public class NewRitualTrigger extends SimpleCriterionTrigger<NewRitualTrigger.TriggerInstance> {
    private static final ResourceLocation ID = new ResourceLocation(OrdemParanormal.MOD_ID, "new_ritual");
    @Override
    public ResourceLocation getId() {
        return ID;
    }
    @Override
    protected NewRitualTrigger.TriggerInstance createInstance(JsonObject pJson, EntityPredicate.Composite pPlayer, DeserializationContext pContext) {
        String id = GsonHelper.getAsString(pJson, "ritual");

        return new TriggerInstance(pPlayer, id);
    }
    public void trigger(ServerPlayer pPlayer, AbstractRitual ritual) {
        trigger(pPlayer, inst -> inst.matches(ritual));
    }
    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final String ritualId;
        public TriggerInstance(EntityPredicate.Composite pPlayer, String ritualId) {
            super(NewRitualTrigger.ID, pPlayer);
            this.ritualId = ritualId;
        }
        public boolean matches(AbstractRitual ritual) {
            return this.ritualId.equals(ritual.getId());
        }
        public JsonObject serializeToJson(SerializationContext pConditions) {
            JsonObject jsonObj = super.serializeToJson(pConditions);
            jsonObj.addProperty("ritual", ritualId);
            return jsonObj;
        }
    }
}
