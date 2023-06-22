package com.guga.ordemparanormal.common.loot.loot_functions;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.*;
import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseHelper;
import com.guga.ordemparanormal.core.registry.OPLootFunctions;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CurseRandomlyFunction extends LootItemConditionalFunction {
    private static final Logger LOGGER = LogUtils.getLogger();
    final List<AbstractCurse> curses;
    protected CurseRandomlyFunction(LootItemCondition[] pConditions, Collection<AbstractCurse> pPossibleCurses) {
        super(pConditions);
        this.curses = ImmutableList.copyOf(pPossibleCurses);
    }
    @Override
    protected ItemStack run(ItemStack pStack, LootContext pContext) {
        RandomSource random = pContext.getRandom();
        AbstractCurse curse;
        if (this.curses.isEmpty()) {
            List<AbstractCurse> list = OrdemParanormalAPI.getInstance().getCurseMap().values().stream().filter(c -> c.canCurse(pStack) && !c.isTemporary()).toList();
            if (list.isEmpty()) {
                LOGGER.warn("Couldn't find a compatible curse for {}", pStack);
                return pStack;
            }

            curse = list.get(random.nextInt(list.size()));
        } else {
            curse = this.curses.get(random.nextInt(this.curses.size()));
        }

        return CurseHelper.addCurse(pStack, curse);
    }
    public static CurseRandomlyFunction.Builder randomCurse() {
        return new CurseRandomlyFunction.Builder();
    }
    @Override
    public LootItemFunctionType getType() {
        return OPLootFunctions.CURSE_RANDOMLY.get();
    }
    public static class Builder extends LootItemConditionalFunction.Builder<CurseRandomlyFunction.Builder> {
        private final Set<AbstractCurse> curses = Sets.newHashSet();
        protected CurseRandomlyFunction.Builder getThis() {
            return this;
        }
        public CurseRandomlyFunction.Builder withCurse(AbstractCurse curse) {
            this.curses.add(curse);
            return this;
        }
        public LootItemFunction build() {
            return new CurseRandomlyFunction(this.getConditions(), this.curses);
        }
    }
    public static class Serializer extends LootItemConditionalFunction.Serializer<CurseRandomlyFunction> {
        /**
         * Serialize the value by putting its data into the JsonObject.
         */
        public void serialize(JsonObject pJson, CurseRandomlyFunction pValue, JsonSerializationContext pSerializationContext) {
            super.serialize(pJson, pValue, pSerializationContext);
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
        public CurseRandomlyFunction deserialize(JsonObject pObject, JsonDeserializationContext pDeserializationContext, LootItemCondition[] pConditions) {
            List<AbstractCurse> list = Lists.newArrayList();
            if (pObject.has("curses")) {
                for(JsonElement jsonelement : GsonHelper.getAsJsonArray(pObject, "curses")) {
                    ResourceLocation s = ResourceLocation.tryParse(GsonHelper.convertToString(jsonelement, "curse"));
                    AbstractCurse curse = OrdemParanormalAPI.getInstance().getCurse(s);
                    list.add(curse);
                }
            }

            return new CurseRandomlyFunction(pConditions, list);
        }
    }
}
