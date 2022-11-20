package com.guga.ordemparanormal.common.loot.loot_conditions;

import com.google.common.collect.Lists;
import com.google.gson.*;
import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseHelper;
import com.guga.ordemparanormal.api.curses.CurseInstance;
import com.guga.ordemparanormal.core.registry.OPLootItemConditions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import java.util.List;
import java.util.stream.Collectors;

public class CurseCondition implements LootItemCondition {
    private final List<AbstractCurse> curses;
    public CurseCondition(List<AbstractCurse> curses) {
        this.curses = curses;
    }
    @Override
    public LootItemConditionType getType() {
        return OPLootItemConditions.CURSE_CONDITION.get();
    }
    @Override
    public boolean test(LootContext lootContext) {
        ItemStack tool = lootContext.getParamOrNull(LootContextParams.TOOL);
        if (tool != null){
            return CurseHelper.getCurses(tool).stream().map(CurseInstance::getCurse).collect(Collectors.toSet()).containsAll(curses);
        } else return false;
    }
    public static class Builder implements LootItemCondition.Builder {
        private final List<AbstractCurse> curses;

        public Builder(List<AbstractCurse> curses) {
            this.curses = curses;
        }

        public CurseCondition build() {
            return new CurseCondition(this.curses);
        }
    }
    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<CurseCondition> {
        /**
         * Serialize the value by putting its data into the JsonObject.
         */
        public void serialize(JsonObject pJson, CurseCondition pValue, JsonSerializationContext pContext) {
            if (!pValue.curses.isEmpty()) {
                JsonArray jsonarray = new JsonArray();

                for(AbstractCurse curse : pValue.curses) {
                    ResourceLocation id = curse.getId();
                    if (id == null) {
                        throw new IllegalArgumentException("Can't serialize curse " + curse);
                    }

                    jsonarray.add(new JsonPrimitive(id.toString()));
                }

                pJson.add("curses", jsonarray);
            }
        }

        /**
         * Deserialize a value by reading it from the JsonObject.
         */
        public CurseCondition deserialize(JsonObject pObject, JsonDeserializationContext context) {
            List<AbstractCurse> list = Lists.newArrayList();
            if (pObject.has("curses")) {
                for(JsonElement jsonelement : GsonHelper.getAsJsonArray(pObject, "curses")) {
                    ResourceLocation s = ResourceLocation.tryParse(GsonHelper.convertToString(jsonelement, "curse"));
                    AbstractCurse curse = OrdemParanormalAPI.getInstance().getCurse(s);
                    list.add(curse);
                }
            }

            return new CurseCondition(list);
        }
    }
}
