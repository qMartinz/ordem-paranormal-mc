package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.worldgen.processors.WaterloggingFixProcessor;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class OPProcessors {
    public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSORS = DeferredRegister.create(Registry.STRUCTURE_PROCESSOR_REGISTRY, OrdemParanormal.MOD_ID);
    public static final RegistryObject<StructureProcessorType<WaterloggingFixProcessor>> WATERLOGGING_FIX_PROCESSOR = STRUCTURE_PROCESSORS.register("waterlogging_fix_processor",
            () -> () -> WaterloggingFixProcessor.CODEC);
}
