package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.loot.loot_modifiers.ArdenteModifier;
import com.guga.ordemparanormal.common.loot.loot_modifiers.SortudaModifier;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OPLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, OrdemParanormal.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ARDENTE_MODIFIER =
            LOOT_MODIFIER_SERIALIZERS.register("ardente", ArdenteModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> SORTUDA_MODIFIER =
            LOOT_MODIFIER_SERIALIZERS.register("sortuda", SortudaModifier.CODEC);
    public static void register(IEventBus bus){
        LOOT_MODIFIER_SERIALIZERS.register(bus);
    }
}
