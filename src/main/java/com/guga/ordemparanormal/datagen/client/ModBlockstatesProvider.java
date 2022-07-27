package com.guga.ordemparanormal.datagen.client;

import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockstatesProvider extends BlockStateProvider {
    public ModBlockstatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, OrdemParanormal.MOD_ID, exFileHelper);
    }
    @Override
    protected void registerStatesAndModels() {
    }
}
