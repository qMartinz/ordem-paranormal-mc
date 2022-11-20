package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.loot.loot_conditions.CurseCondition;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class OPLootItemConditions {
    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITIONS = DeferredRegister.create(Registry.LOOT_CONDITION_TYPE.key(), OrdemParanormal.MOD_ID);
    public static final RegistryObject<LootItemConditionType> CURSE_CONDITION = register("curse", new CurseCondition.Serializer());
    private static RegistryObject<LootItemConditionType> register(String pRegistryName, Serializer<? extends LootItemCondition> pSerializer) {
        return LOOT_CONDITIONS.register(pRegistryName, () -> new LootItemConditionType(pSerializer));
    }
}
