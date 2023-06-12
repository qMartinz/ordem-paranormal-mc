package com.guga.ordemparanormal.datagen;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.datagen.client.ModBlockstatesProvider;
import com.guga.ordemparanormal.datagen.client.ModItemModelProvider;
import com.guga.ordemparanormal.datagen.client.ModSoundDefinitionsProvider;
import com.guga.ordemparanormal.datagen.client.lang.ModEnUsProvider;
import com.guga.ordemparanormal.datagen.client.lang.ModPtBrProvider;
import com.guga.ordemparanormal.datagen.server.ModBlockTagsProvider;
import com.guga.ordemparanormal.datagen.server.ModItemTagsProvider;
import com.guga.ordemparanormal.datagen.server.ModLootTableProvider;
import com.guga.ordemparanormal.datagen.server.ModRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Bus.MOD)
public class OPDataGeneration {
	
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();
		
		if (event.includeClient()) {
			// Geração de Data do Cliente
			generator.addProvider(true, new ModItemModelProvider(generator, helper));
			generator.addProvider(true, new ModSoundDefinitionsProvider(generator, helper));
			generator.addProvider(true, new ModPtBrProvider(generator));
			generator.addProvider(true, new ModEnUsProvider(generator));
			generator.addProvider(true, new ModBlockstatesProvider(generator, helper));
		}
		
		if (event.includeServer()) {
			// Geração de Data do Server
			generator.addProvider(true, new ModLootTableProvider(generator));
			generator.addProvider(true, new ModRecipeProvider(generator));
			ModBlockTagsProvider blocktags = new ModBlockTagsProvider(generator, helper);
			generator.addProvider(true, blocktags);
			generator.addProvider(true, new ModItemTagsProvider(generator, blocktags, helper));
		}	
	}
}
