package com.guga.ordemparanormal.datagen.client;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockstatesProvider extends BlockStateProvider {
    public ModBlockstatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, OrdemParanormal.MOD_ID, exFileHelper);
    }
    @Override
    protected void registerStatesAndModels() {
        horizontalBlock(OPBlocks.MESA_SANGUE.get(),
                new ModelFile.ExistingModelFile(
                        new ResourceLocation(OrdemParanormal.MOD_ID, "block/mesa_sangue"),
                        models().existingFileHelper), 270);
        horizontalBlock(OPBlocks.MESA_CONHECIMENTO.get(),
                new ModelFile.ExistingModelFile(
                        new ResourceLocation(OrdemParanormal.MOD_ID, "block/mesa_conhecimento"),
                        models().existingFileHelper), 270);
        horizontalBlock(OPBlocks.MESA_MORTE.get(),
                new ModelFile.ExistingModelFile(
                        new ResourceLocation(OrdemParanormal.MOD_ID, "block/mesa_morte"),
                        models().existingFileHelper), 270);
        horizontalBlock(OPBlocks.MESA_ENERGIA.get(),
                new ModelFile.ExistingModelFile(
                        new ResourceLocation(OrdemParanormal.MOD_ID, "block/mesa_energia"),
                        models().existingFileHelper), 270);
        simpleBlock(OPBlocks.LUZ.get(),
                new ModelFile.ExistingModelFile(
                        new ResourceLocation(OrdemParanormal.MOD_ID, "block/luz"),
                        models().existingFileHelper));
    }
}
