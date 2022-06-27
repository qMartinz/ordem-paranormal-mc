package com.guga.ordemparanormal.datagen.client.lang;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPEntities;
import com.guga.ordemparanormal.core.registry.OPItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEnUsProvider extends LanguageProvider{

	public ModEnUsProvider(DataGenerator gen) {
		super(gen, OrdemParanormal.MOD_ID, "en_us");
	}

	// Adicionar traduções em EN-US
	@Override
	protected void addTranslations() {
		// Grupo de itens
		add("itemGroup.ordemparanormal", "Paranormal Order: MC");

		// Sons
		add("subtitles.ordemparanormal.zumbi_sangue.growl", "Blood zombie growl");
		add("subtitles.ordemparanormal.zumbi_bestial.growl", "Bestial Blood zombie growl");
		
		// Itens
		add(OPItems.GRIMORIO_ENERGIA.get(), "Energy grimoire");
		add(OPItems.GRIMORIO_SANGUE.get(), "Blood grimoire");
		add(OPItems.ORGAO.get(), "Organ");
		add(OPItems.CINZAS.get(), "Ashes");
		add(OPItems.RITUAL_DESCARNAR.get(), "Rip flesh ritual");
		
		// Ovos
		add(OPItems.BESTIAL_OVO.get(), "Bestial Blood zombie spawn egg");
		add(OPItems.ZUMBI_SANGUE_OVO.get(), "Blood zombie spawn egg");
		add(OPItems.ZUMBI_SECO_OVO.get(), "Skeletal Blood zombie spawn egg");
		add(OPItems.ZUMBI_ESPINHENTO_OVO.get(), "Spiky Blood zombie spawn egg");
		
		// Entidades
		add(OPEntities.BESTIAL.get(), "Bestial Blood zombie");
		add(OPEntities.ZUMBI_SANGUE.get(), "Blood zombie");
		add(OPEntities.VILLAGER_CORPO.get(), "Villager corpse");
		add(OPEntities.ZUMBI_SECO.get(), "Skeletal Blood zombie");
		add(OPEntities.ZUMBI_ESPINHENTO.get(), "Spiky Blood zombie");

		// Atributos paranormais
		add("op_attribute.strength", "Strength");
		add("op_attribute.vigor", "Vigor");
		add("op_attribute.will", "Will");

		// Atalhos de Teclado
		add("key.nex_screen", "Show Paranormal Attributes");

		// Livros dentro do jogo
		add("mansion_books.record_495", "Blood brother Lairon came back from his expedition... He said that he found a portal in the supposed \"Order's\" plains. We recreated it and watched it closely. We will never listen to Lairon again. Damned bastard! At least we got resources from that.");
		add("mansion_books.record_001.page_1", "Day 1: \nJerome told me this place is safe for my habitation. The first guest told me something horrible... When I was returning from the forest, a fog appeared. It wasn't in my weather predictions... \nHe had a big bite on the leg, and didn't want to talk about it...");
		add("mansion_books.record_001.page_2", "Day 2: \nWHAT THE FUCK IS THAT?!");

		// Outros
		add("nex.title", "Paranormal Exposure");
		add("nex.abbreviation", "PeX");
		add("nex.ability_points", "Ability Points");
		add("nex.lvlup_notif", "Você sente algo te chamando...");
	}

}
