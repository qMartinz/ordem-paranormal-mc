package com.guga.ordemparanormal.datagen.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.guga.ordemparanormal.core.registry.OPEntities;
import com.guga.ordemparanormal.core.registry.OPItems;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditions;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class ModEntityLoot extends EntityLoot {
    private final Map<ResourceLocation, LootTable.Builder> map = Maps.newHashMap();
    private final List<EntityType<?>> KNOWN_ENTITIES = Lists.newArrayList();
    @Override
    protected void addTables() {
        add(OPEntities.VILLAGER_CORPO.get(), new LootTable.Builder()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(OPItems.ORGAO.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0f, 3f)))
                                .apply(SmeltItemFunction.smelted()
                                        .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))));
    }
    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        this.addTables();
        map.forEach((consumer::accept));
    }
    @Override
    protected Iterable<EntityType<?>> getKnownEntities() {
        return KNOWN_ENTITIES;
    }
    @Override
    protected void add(EntityType<?> pEntityType, LootTable.Builder pLootTableBuilder) {
        this.map.put(pEntityType.getDefaultLootTable(), pLootTableBuilder);
    }
}
