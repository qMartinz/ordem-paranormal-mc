package com.guga.ordemparanormal.datagen.server;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, OrdemParanormal.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.NEEDS_STONE_TOOL).add(OPBlocks.ALTAR_TRANSCENDER.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(OPBlocks.ALTAR_TRANSCENDER.get());
        tag(BlockTags.NEEDS_STONE_TOOL).add(OPBlocks.MESA_MORTE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(OPBlocks.MESA_MORTE.get());
        tag(BlockTags.NEEDS_STONE_TOOL).add(OPBlocks.MESA_SANGUE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(OPBlocks.MESA_SANGUE.get());
        tag(BlockTags.NEEDS_STONE_TOOL).add(OPBlocks.MESA_ENERGIA.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(OPBlocks.MESA_ENERGIA.get());
        tag(BlockTags.NEEDS_STONE_TOOL).add(OPBlocks.MESA_CONHECIMENTO.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(OPBlocks.MESA_CONHECIMENTO.get());
    }
}
