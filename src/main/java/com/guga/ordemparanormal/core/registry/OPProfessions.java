package com.guga.ordemparanormal.core.registry;

import com.google.common.collect.ImmutableSet;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

public class OPProfessions {
    public static final Set<PoiType> POIS = new HashSet<>();
    public static final DeferredRegister<VillagerProfession> VILLARGER_PROFESSIONS = DeferredRegister.create(
            ForgeRegistries.PROFESSIONS, OrdemParanormal.MOD_ID);
    public static final RegistryObject<VillagerProfession> OCULTISTA_SANGUE = VILLARGER_PROFESSIONS.register("ocultista_sangue",
            () -> new VillagerProfession("ocultista_sangue", OPPois.MESA_SANGUE_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CLERIC));
    public static final RegistryObject<VillagerProfession> OCULTISTA_ENERGIA = VILLARGER_PROFESSIONS.register("ocultista_energia",
            () -> new VillagerProfession("ocultista_energia", OPPois.MESA_ENERGIA_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CLERIC));
    public static final RegistryObject<VillagerProfession> OCULTISTA_MORTE = VILLARGER_PROFESSIONS.register("ocultista_morte",
            () -> new VillagerProfession("ocultista_morte", OPPois.MESA_MORTE_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CLERIC));
    public static final RegistryObject<VillagerProfession> OCULTISTA_CONHECIMENTO = VILLARGER_PROFESSIONS.register("ocultista_conhecimento",
            () -> new VillagerProfession("ocultista_conhecimento", OPPois.MESA_CONHECIMENTO_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CLERIC));
    public static void register(IEventBus bus){
        VILLARGER_PROFESSIONS.register(bus);
    }
}
