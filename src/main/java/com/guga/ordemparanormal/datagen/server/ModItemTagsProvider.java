package com.guga.ordemparanormal.datagen.server;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPItems;
import com.guga.ordemparanormal.core.registry.OPTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, pBlockTagsProvider, OrdemParanormal.MOD_ID, existingFileHelper);
    }
    @Override
    protected void addTags() {
        tag(OPTags.RAW_MEATS).add(Items.BEEF, Items.PORKCHOP, Items.MUTTON, Items.RABBIT);

        tag(OPTags.RITUAL_COMPONENTS).add(OPItems.COMPONENTE_CONHECIMENTO.get(), OPItems.COMPONENTE_ENERGIA.get(), OPItems.COMPONENTE_MORTE.get(), OPItems.COMPONENTE_SANGUE.get());
        tag(OPTags.BLOOD_FUEL).addTag(OPTags.RAW_MEATS).add(OPItems.ORGAO.get());
        tag(OPTags.ENERGY_FUEL).add(Items.LAPIS_LAZULI, Items.AMETHYST_SHARD, Items.PRISMARINE_SHARD, Items.EMERALD, Items.QUARTZ);
        tag(OPTags.DEATH_FUEL).add(Items.BONE, Items.SAND, Items.RED_SAND, Items.GRAVEL, OPItems.CINZAS.get());
        tag(OPTags.KNOWLEDGE_FUEL).add(Items.GLASS, Items.GOLD_INGOT, Items.GLOWSTONE_DUST, Items.PAPER, Items.CANDLE);
    }
}