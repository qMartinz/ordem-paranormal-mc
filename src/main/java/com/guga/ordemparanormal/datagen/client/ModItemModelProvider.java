package com.guga.ordemparanormal.datagen.client;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPItems;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider{

	public ModItemModelProvider(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, OrdemParanormal.MOD_ID, helper);
	}
	
	// M�todo para gerar modelo de item
	protected void oneLayerItem(Item item, ResourceLocation texture) {
		ResourceLocation itemTexture = new ResourceLocation(texture.getNamespace(), "item/" + texture.getPath());
		if (existingFileHelper.exists(itemTexture, PackType.CLIENT_RESOURCES, ".png", "textures")) {
			getBuilder(item.getRegistryName().getPath()).parent(getExistingFile(mcLoc("item/generated")))
				.texture("layer0", itemTexture);
		} else {
			System.out.println("Texture for " + item.getRegistryName().toString() + " not present at " + itemTexture.toString());
		}
	}
	
	protected void oneLayerItem(Item item) {
		oneLayerItem(item, item.getRegistryName());
	}
	
	protected void spawnEggItem(Item item) {
		getBuilder(item.getRegistryName().getPath()).parent(getExistingFile(mcLoc("item/template_spawn_egg")));
	}

	// Registrar modelos de itens
	@Override
	protected void registerModels() {
		// Ovos
		spawnEggItem(OPItems.ABERRADO_OVO.get());
		spawnEggItem(OPItems.BESTIAL_OVO.get());
		spawnEggItem(OPItems.ZUMBI_SANGUE_OVO.get());
		
		// Itens
		oneLayerItem(OPItems.GRIMORIO_ENERGIA.get());
		oneLayerItem(OPItems.GRIMORIO_SANGUE.get());
		oneLayerItem(OPItems.ORGAO.get());
		oneLayerItem(OPItems.CINZAS.get());
		oneLayerItem(OPItems.RITUAL_DESCARNAR.get());
	}

}
