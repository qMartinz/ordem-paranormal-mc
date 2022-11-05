package com.guga.ordemparanormal.datagen;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.datagen.client.ModBlockstatesProvider;
import com.guga.ordemparanormal.datagen.client.ModItemModelProvider;
import com.guga.ordemparanormal.datagen.client.ModSoundDefinitionsProvider;
import com.guga.ordemparanormal.datagen.client.lang.ModEnUsProvider;
import com.guga.ordemparanormal.datagen.client.lang.ModPtBrProvider;
import com.guga.ordemparanormal.datagen.server.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Bus.MOD)
public class OPDataGeneration {
	
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();
		
		if (event.includeClient()) {
			// Geração de Data do Cliente
			generator.addProvider(new ModItemModelProvider(generator, helper));
			generator.addProvider(new ModSoundDefinitionsProvider(generator, helper));
			generator.addProvider(new ModPtBrProvider(generator));
			generator.addProvider(new ModEnUsProvider(generator));
			generator.addProvider(new ModBlockstatesProvider(generator, helper));
		}
		
		if (event.includeServer()) {
			// Geração de Data do Server
			generator.addProvider(new ModLootTableProvider(generator));
			generator.addProvider(new ModRecipeProvider(generator));
			ModBlockTagsProvider blocktags = new ModBlockTagsProvider(generator, helper);
			generator.addProvider(blocktags);
			generator.addProvider(new ModItemTagsProvider(generator, blocktags, helper));
		}	
	}
}
