package com.guga.ordemparanormal.datagen.client.lang;

import com.guga.ordemparanormal.api.ritual.ParanormalElement;
import com.guga.ordemparanormal.common.ritual.SkinningRitual;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPEntities;
import com.guga.ordemparanormal.core.registry.OPItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModPtBrProvider extends LanguageProvider{

	public ModPtBrProvider(DataGenerator gen) {
		super(gen, OrdemParanormal.MOD_ID, "pt_br");
	}

	// Adicionar tradu��es em PT-BR
	@Override
	protected void addTranslations() {
		// Grupo de itens
		add("itemGroup.ordemparanormal", "Ordem Paranormal: MC");

		// Sons
		add("subtitles.ordemparanormal.zumbi_sangue.growl", "Grunhido de zumbi de Sangue");
		add("subtitles.ordemparanormal.zumbi_bestial.growl", "Grunhido de zumbi de Sangue bestial");
		
		// Itens
		add(OPItems.GRIMORIO_ENERGIA.get(), "Grim�rio de Energia");
		add(OPItems.GRIMORIO_SANGUE.get(), "Grim�rio de Sangue");
		add(OPItems.ORGAO.get(), "�rg�o");
		add(OPItems.CINZAS.get(), "Cinzas");
		add(OPItems.RITUAL_DESCARNAR.get(), "Livro com s�mbolo");
		
		// Ovos
		add(OPItems.BESTIAL_OVO.get(), "Ovo gerador de zumbi de Sangue bestial");
		add(OPItems.ZUMBI_SANGUE_OVO.get(), "Ovo gerador de zumbi de Sangue");
		add(OPItems.ZUMBI_SECO_OVO.get(), "Ovo gerador de zumbi de Sangue seco");
		add(OPItems.ZUMBI_ESPINHENTO_OVO.get(), "Ovo gerador de zumbi de Sangue espinhento");
		
		// Entidades
		add(OPEntities.BESTIAL.get(), "Zumbi de Sangue bestial");
		add(OPEntities.ZUMBI_SANGUE.get(), "Zumbi de Sangue");
		add(OPEntities.VILLAGER_CORPO.get(), "Corpo de Alde�o");
		add(OPEntities.ZUMBI_SECO.get(), "Zumbi de Sangue seco");
		add(OPEntities.ZUMBI_ESPINHENTO.get(), "Zumbi de Sangue espinhento");

		// Elementos
		add(ParanormalElement.BLOOD.getTranslationKey(), "Sangue");
		add(ParanormalElement.DEATH.getTranslationKey(), "Morte");
		add(ParanormalElement.KNOWLEDGE.getTranslationKey(), "Conhecimento");
		add(ParanormalElement.ENERGY.getTranslationKey(), "Energia");
		add(ParanormalElement.FEAR.getTranslationKey(), "Medo");

		// Atributos paranormais
		add("ordemparanormal.op_attribute.strength", "For�a");
		add("ordemparanormal.op_attribute.vigor", "Vigor");
		add("ordemparanormal.op_attribute.will", "Presen�a");

		// Rituais
		add(new SkinningRitual().getTranslationKey(), "Descarnar");

		// Atalhos de Teclado
		add("ordemparanormal.key_category", "Mod Ordem Paranormal");
		add("ordemparanormal.key.nex_screen", "Mostrar Atributos Paranormais");

		// Livros dentro do jogo
		add("ordemparanormal.mansion_books.record_495", "O irm�o de Sangue, Lairon, voltou de sua expedi��o... Ele disse que encontrou um portal nas plan�cies da suposta \"Ordem\". N�s o recriamos e espiamos. Nunca mais ouviremos Lairon. Maldito bastardo! Pelo menos conseguimos recursos de l�.");
		add("ordemparanormal.mansion_books.record_001.page_1", "Dia 1: \nJerome me disse que o local � seguro para uma habita��o. O primeiro h�spede me contou algo terr�vel... Uma n�voa surgiu quando eu estava voltando da floresta, aquilo n�o estava na minha previs�o do tempo... \nEle estava com uma mordida imensa na perna e se recusou a falar sobre ela...");
		add("ordemparanormal.mansion_books.record_001.page_2", "Dia 2: \nQUE PORRA � ESSA?!");

		// Outros
		add("ordemparanormal.nex.title", "Exposi��o Paranormal");
		add("ordemparanormal.nex.abbreviation", "NeX");
		add("ordemparanormal.nex.ability_points", "Pontos de Habilidade");
	}

}
