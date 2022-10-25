package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class OPPois {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(
            ForgeRegistries.POI_TYPES, OrdemParanormal.MOD_ID);
    public static final RegistryObject<PoiType> MESA_SANGUE_POI = POI_TYPES.register("mesa_sangue_poi",
            () -> new PoiType("mesa_sangue_poi", PoiType.getBlockStates(OPBlocks.MESA_SANGUE.get()), 1, 1));
    public static final RegistryObject<PoiType> MESA_MORTE_POI = POI_TYPES.register("mesa_morte_poi",
            () -> new PoiType("mesa_morte_poi", PoiType.getBlockStates(OPBlocks.MESA_MORTE.get()), 1, 1));
    public static final RegistryObject<PoiType> MESA_CONHECIMENTO_POI = POI_TYPES.register("mesa_conhecimento_poi",
            () -> new PoiType("mesa_conhecimento_poi", PoiType.getBlockStates(OPBlocks.MESA_CONHECIMENTO.get()), 1, 1));
    public static final RegistryObject<PoiType> MESA_ENERGIA_POI = POI_TYPES.register("mesa_energia_poi",
            () -> new PoiType("mesa_energia_poi", PoiType.getBlockStates(OPBlocks.MESA_ENERGIA.get()), 1, 1));
    public static void register(IEventBus bus){
        POI_TYPES.register(bus);
    }
    public static void registerPOIs(){
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, MESA_SANGUE_POI.get());
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, MESA_CONHECIMENTO_POI.get());
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, MESA_ENERGIA_POI.get());
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, MESA_MORTE_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exception){
            exception.printStackTrace();
        }
    }
}
