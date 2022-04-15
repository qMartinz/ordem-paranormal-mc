package com.guga.ordemparanormal.datagen.client.lang;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPEntities;
import com.guga.ordemparanormal.core.registry.OPItems;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEnUsProvider extends LanguageProvider{

	public ModEnUsProvider(DataGenerator gen) {
		super(gen, OrdemParanormal.MOD_ID, "en_us");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addTranslations() {
		// Grupo de itens
		add("itemGroup.ordemparanormal", "Paranormal Order: MC");
		
		// Itens
		add(OPItems.GRIMORIO_ENERGIA.get(), "Energy grimoire");
		add(OPItems.GRIMORIO_SANGUE.get(), "Blood grimoire");
		
		// Ovos
		add(OPItems.ABERRADO_OVO.get(), "Aberrant spawn egg");
		add(OPItems.BESTIAL_OVO.get(), "Bestial Blood zombie spawn egg");
		add(OPItems.ZUMBI_SANGUE_OVO.get(), "Blood zombie spawn egg");
		
		// Entidades
		add(OPEntities.ABERRADO.get(), "Aberrant");
		add(OPEntities.BESTIAL.get(), "Bestial Blood zombie");
		add(OPEntities.ZUMBI_SANGUE.get(), "Blood zombie");
		add(OPEntities.VILLAGER_CORPO.get(), "Villager corpse");
	}

}
