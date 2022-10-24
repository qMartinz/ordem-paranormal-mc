package com.guga.ordemparanormal.datagen.client;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPBlocks;
import com.guga.ordemparanormal.core.registry.OPItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
	public ModItemModelProvider(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, OrdemParanormal.MOD_ID, helper);
	}

	// Método para gerar modelo de item
	protected void oneLayerItem(Item item, ResourceLocation texture) {
		ResourceLocation itemTexture = new ResourceLocation(texture.getNamespace(), "item/" + texture.getPath());
		if (existingFileHelper.exists(itemTexture, PackType.CLIENT_RESOURCES, ".png", "textures")) {
			getBuilder(item.getRegistryName().getPath()).parent(getExistingFile(mcLoc("item/generated")))
					.texture("layer0", itemTexture);
		} else {
			System.out.println("Texture for " + item.getRegistryName().toString() + " not present at " + itemTexture);
		}
	}

	protected void oneLayerItem(Item item) {
		oneLayerItem(item, item.getRegistryName());
	}

	protected void oneLayerHandHeldItem(Item item, ResourceLocation texture) {
		ResourceLocation itemTexture = new ResourceLocation(texture.getNamespace(), "item/" + texture.getPath());
		if (existingFileHelper.exists(itemTexture, PackType.CLIENT_RESOURCES, ".png", "textures")) {
			getBuilder(item.getRegistryName().getPath()).parent(getExistingFile(mcLoc("item/handheld")))
					.texture("layer0", itemTexture);
		} else {
			System.out.println("Texture for " + item.getRegistryName().toString() + " not present at " + itemTexture);
		}
	}

	protected void oneLayerHandHeldItem(Item item) {
		oneLayerHandHeldItem(item, item.getRegistryName());
	}

	protected void multipleLayerItem(Item item, ResourceLocation... textures) {
		for (int i = 0; i < textures.length; i++) {
			ResourceLocation itemTexture = new ResourceLocation(textures[i].getNamespace(),
					"item/" + textures[i].getPath());
			if (existingFileHelper.exists(itemTexture, PackType.CLIENT_RESOURCES, ".png", "textures")) {
				getBuilder(item.getRegistryName().getPath()).parent(getExistingFile(mcLoc("item/generated")))
						.texture("layer" + i, itemTexture);
			} else {
				System.out.println("Texture " + i + " for " + item.getRegistryName().toString() + " not present at "
						+ itemTexture);
			}
		}
	}

	protected void multipleLayerHandHeldItem(Item item, ResourceLocation... textures) {
		for (int i = 0; i < textures.length; i++) {
			ResourceLocation itemTexture = new ResourceLocation(textures[i].getNamespace(),
					"item/" + textures[i].getPath());
			if (existingFileHelper.exists(itemTexture, PackType.CLIENT_RESOURCES, ".png", "textures")) {
				getBuilder(item.getRegistryName().getPath()).parent(getExistingFile(mcLoc("item/handheld")))
						.texture("layer" + i, itemTexture);
			} else {
				System.out.println("Texture " + i + " for " + item.getRegistryName().toString() + " not present at "
						+ itemTexture);
			}
		}
	}

	protected void simpleBlockItem(Item item) {
		getBuilder(item.getRegistryName().toString())
				.parent(getExistingFile(modLoc("block/" + item.getRegistryName().getPath())));
	}

	protected void spawnEggItem(Item item) {
		getBuilder(item.getRegistryName().getPath()).parent(getExistingFile(mcLoc("item/template_spawn_egg")));
	}

	// Registrar modelos de itens
	@Override
	protected void registerModels() {
		// Ovos
		spawnEggItem(OPItems.BESTIAL_OVO.get());
		spawnEggItem(OPItems.ZUMBI_SANGUE_OVO.get());
		spawnEggItem(OPItems.ZUMBI_SECO_OVO.get());
		spawnEggItem(OPItems.ZUMBI_ESPINHENTO_OVO.get());

		// Itens
		oneLayerItem(OPItems.ORGAO.get());
		oneLayerItem(OPItems.CINZAS.get());
		oneLayerItem(OPItems.PERGAMINHO_ANTIGO.get());

		// Itens de Rituais
		oneLayerItem(OPItems.RITUAL_DESCARNAR.get());
		oneLayerItem(OPItems.RITUAL_DECADENCIA.get());
		oneLayerItem(OPItems.RITUAL_CICATRIZACAO.get());
		oneLayerItem(OPItems.RITUAL_CONSUMIR_MANANCIAL.get());
		oneLayerItem(OPItems.RITUAL_ARMADURA_SANGUE.get());
		oneLayerItem(OPItems.RITUAL_ARMA_ATROZ.get());
		oneLayerItem(OPItems.RITUAL_ARMA_VELOZ.get());
		oneLayerItem(OPItems.RITUAL_AMALDICOAR_ARMA.get());
		oneLayerItem(OPItems.RITUAL_HEMOFAGIA.get());
		oneLayerItem(OPItems.RITUAL_APRIMORAMENTO_FISICO.get());
		oneLayerItem(OPItems.RITUAL_VELOCIDADE_MORTAL.get());
		oneLayerItem(OPItems.RITUAL_TRANSFERENCIA_VITAL.get());
		oneLayerItem(OPItems.RITUAL_SALTO_FANTASMA.get());
		oneLayerItem(OPItems.RITUAL_TELEPORTE.get());

		// Componentes Ritualisticos
		oneLayerItem(OPItems.COMPONENTE_VAZIO.get());
		oneLayerItem(OPItems.COMPONENTE_SANGUE.get());
		oneLayerItem(OPItems.COMPONENTE_MORTE.get());
		oneLayerItem(OPItems.COMPONENTE_CONHECIMENTO.get());
		oneLayerItem(OPItems.COMPONENTE_ENERGIA.get());

		// Blocos
		simpleBlockItem(OPBlocks.ALTAR_TRANSCENDER.get().asItem());
	}

}
