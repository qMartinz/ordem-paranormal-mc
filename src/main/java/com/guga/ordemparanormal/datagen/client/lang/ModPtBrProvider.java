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

	// Adicionar traduções em PT-BR
	@Override
	protected void addTranslations() {
		// Grupo de itens
		add("itemGroup.ordemparanormal", "Ordem Paranormal: MC");

		// Sons
		add("sounds.ordemparanormal.zumbi_sangue.growl", "Grunhido de zumbi de Sangue");
		add("sounds.ordemparanormal.zumbi_bestial.growl", "Grunhido de zumbi de Sangue bestial");
		
		// Itens
		add(OPItems.GRIMORIO_ENERGIA.get(), "Grimório de Energia");
		add(OPItems.GRIMORIO_SANGUE.get(), "Grimório de Sangue");
		add(OPItems.ORGAO.get(), "Órgão");
		add(OPItems.CINZAS.get(), "Cinzas");
		add(OPItems.RITUAL_DESCARNAR.get(), "Ritual de descarnar");
		
		// Ovos
		add(OPItems.BESTIAL_OVO.get(), "Ovo gerador de zumbi de Sangue bestial");
		add(OPItems.ZUMBI_SANGUE_OVO.get(), "Ovo gerador de zumbi de Sangue");
		add(OPItems.ZUMBI_SECO_OVO.get(), "Ovo gerador de zumbi de Sangue seco");
		add(OPItems.ZUMBI_ESPINHENTO_OVO.get(), "Ovo gerador de zumbi de Sangue espinhento");
		
		// Entidades
		add(OPEntities.BESTIAL.get(), "Zumbi de Sangue bestial");
		add(OPEntities.ZUMBI_SANGUE.get(), "Zumbi de Sangue");
		add(OPEntities.VILLAGER_CORPO.get(), "Corpo de Aldeão");
		add(OPEntities.ZUMBI_SECO.get(), "Zumbi de Sangue seco");
		add(OPEntities.ZUMBI_ESPINHENTO.get(), "Zumbi de Sangue espinhento");

		// Outros
		add("nex.title", "Exposição Paranormal");
		add("nex.abbreviation", "NeX");
	}

}
