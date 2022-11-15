package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.loot_functions.CurseRandomlyFunction;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class OPLootFunctions {
    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTIONS = DeferredRegister.create(Registry.LOOT_FUNCTION_REGISTRY, OrdemParanormal.MOD_ID);
    public static final RegistryObject<LootItemFunctionType> CURSE_RANDOMLY = register("curse_randomly",
            new CurseRandomlyFunction.Serializer());
    private static RegistryObject<LootItemFunctionType> register(String pId, Serializer<? extends LootItemFunction> pSerializer) {
        return LOOT_FUNCTIONS.register(pId, () -> new LootItemFunctionType(pSerializer));
    }
}
