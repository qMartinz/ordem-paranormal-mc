package com.guga.ordemparanormal.datagen.client.lang;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPEntities;
import com.guga.ordemparanormal.core.registry.OPItems;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModPtBrProvider extends LanguageProvider{

	public ModPtBrProvider(DataGenerator gen) {
		super(gen, OrdemParanormal.MOD_ID, "pt_br");
	}

	@Override
	protected void addTranslations() {
		// Grupo de itens
		add("itemGroup.ordemparanormal", "Ordem Paranormal: MC");
		
		// Itens
		add(OPItems.GRIMORIO_ENERGIA.get(), "Grimório de Energia");
		add(OPItems.GRIMORIO_SANGUE.get(), "Grimório de Sangue");
		
		// Ovos
		add(OPItems.ABERRADO_OVO.get(), "Ovo gerador de aberrado");
		add(OPItems.BESTIAL_OVO.get(), "Ovo gerador de zumbi de Sangue bestial");
		add(OPItems.ZUMBI_SANGUE_OVO.get(), "Ovo gerador de zumbi de Sangue");
		
		// Entidades
		add(OPEntities.ABERRADO.get(), "Aberrado");
		add(OPEntities.BESTIAL.get(), "Zumbi de Sangue bestial");
		add(OPEntities.ZUMBI_SANGUE.get(), "Zumbi de Sangue");
	}

}
