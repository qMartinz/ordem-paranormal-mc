package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.worldgen.processors.WaterloggingFixProcessor;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public class OPProcessors {
    public static StructureProcessorType<WaterloggingFixProcessor> WATERLOGGING_FIX_PROCESSOR = () -> WaterloggingFixProcessor.CODEC;
    public static void register(){
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(OrdemParanormal.MOD_ID, "waterlogging_fix_processor"), WATERLOGGING_FIX_PROCESSOR);
    }
}
