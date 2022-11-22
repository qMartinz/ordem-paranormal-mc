package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.loot.loot_modifiers.ArdenteModifier;
import com.guga.ordemparanormal.common.loot.loot_modifiers.SortudaModifier;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OPLootModifiers {
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, OrdemParanormal.MOD_ID);
    public static final RegistryObject<ArdenteModifier.Serializer> ARDENTE_MODIFIER = LOOT_MODIFIERS.register("ardente", ArdenteModifier.Serializer::new);
    public static final RegistryObject<SortudaModifier.Serializer> SORTUDA_MODIFIER = LOOT_MODIFIERS.register("sortuda", SortudaModifier.Serializer::new);
}
