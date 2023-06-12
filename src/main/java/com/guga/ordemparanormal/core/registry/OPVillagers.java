package com.guga.ordemparanormal.core.registry;

import com.google.common.collect.ImmutableSet;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class OPVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(
            ForgeRegistries.POI_TYPES, OrdemParanormal.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLARGER_PROFESSIONS = DeferredRegister.create(
            ForgeRegistries.VILLAGER_PROFESSIONS, OrdemParanormal.MOD_ID);

    public static final RegistryObject<PoiType> MESA_SANGUE_POI = POI_TYPES.register("mesa_sangue_poi",
            () -> new PoiType(getBlockStates(OPBlocks.MESA_SANGUE.get()), 1, 1));
    public static final RegistryObject<PoiType> MESA_MORTE_POI = POI_TYPES.register("mesa_morte_poi",
            () -> new PoiType(getBlockStates(OPBlocks.MESA_MORTE.get()), 1, 1));
    public static final RegistryObject<PoiType> MESA_CONHECIMENTO_POI = POI_TYPES.register("mesa_conhecimento_poi",
            () -> new PoiType(getBlockStates(OPBlocks.MESA_CONHECIMENTO.get()), 1, 1));
    public static final RegistryObject<PoiType> MESA_ENERGIA_POI = POI_TYPES.register("mesa_energia_poi",
            () -> new PoiType(getBlockStates(OPBlocks.MESA_ENERGIA.get()), 1, 1));

    public static final RegistryObject<VillagerProfession> OCULTISTA_SANGUE = VILLARGER_PROFESSIONS.register("ocultista_sangue",
            () -> new VillagerProfession("ocultista_sangue", (x) -> x.get() == MESA_SANGUE_POI.get(),
                    (x) -> x.get() == MESA_SANGUE_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_CLERIC));
    public static final RegistryObject<VillagerProfession> OCULTISTA_ENERGIA = VILLARGER_PROFESSIONS.register("ocultista_energia",
            () -> new VillagerProfession("ocultista_energia", (x) -> x.get() == MESA_ENERGIA_POI.get(),
                    (x) -> x.get() == MESA_ENERGIA_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_CLERIC));
    public static final RegistryObject<VillagerProfession> OCULTISTA_MORTE = VILLARGER_PROFESSIONS.register("ocultista_morte",
            () -> new VillagerProfession("ocultista_morte", (x) -> x.get() == MESA_MORTE_POI.get(),
                    (x) -> x.get() == MESA_MORTE_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_CLERIC));
    public static final RegistryObject<VillagerProfession> OCULTISTA_CONHECIMENTO = VILLARGER_PROFESSIONS.register("ocultista_conhecimento",
            () -> new VillagerProfession("ocultista_conhecimento", (x) -> x.get() == MESA_CONHECIMENTO_POI.get(),
                    (x) -> x.get() == MESA_CONHECIMENTO_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_CLERIC));
    public static void registerPois(){
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class)
                    .invoke(null, MESA_SANGUE_POI.get());
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class)
                    .invoke(null, MESA_ENERGIA_POI.get());
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class)
                    .invoke(null, MESA_MORTE_POI.get());
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class)
                    .invoke(null, MESA_CONHECIMENTO_POI.get());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void register(IEventBus bus){
        VILLARGER_PROFESSIONS.register(bus);
        POI_TYPES.register(bus);
    }
    private static Set<BlockState> getBlockStates(Block block) {
        return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
    }
}
