package com.guga.ordemparanormal.datagen.client.lang;

import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.powers.ParanormalElement;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModPtBrProvider extends LanguageProvider{

	public ModPtBrProvider(DataGenerator gen) {
		super(gen, OrdemParanormal.MOD_ID, "pt_br");
	}
	private void addDescription(String key, String line1, String line2, String line3){
		add(key + ".description.line_1", line1);
		add(key + ".description.line_2", line2);
		add(key + ".description.line_3", line3);
	}
	private void addDescription(String key, String line1, String line2){
		add(key + ".description.line_1", line1);
		add(key + ".description.line_2", line2);
	}
	private void addRitual(AbstractRitual ritual, String name, String description){
		add(ritual.getTranslationKey(), name);
		add(ritual.getTranslationKey() + ".description", description);
	}
	private void addPower(PlayerPower power, String name, String description1, String description2){
		add(power.getTranslationKey(), name);
		add(power.getTranslationKey() + ".description.line_1", description1);
		add(power.getTranslationKey() + ".description.line_2", description2);
	}

	// Adicionar tradu��es em PT-BR
	@Override
	protected void addTranslations() {
		// Grupo de itens
		add("itemGroup.ordemparanormal", "Ordem Paranormal");
		add("itemGroup.ordemparanormal.rituals", "Rituais");
		add("itemGroup.ordemparanormal.mobs", "Criaturas Paranormais");

		// Sons
		add("subtitles.ordemparanormal.zumbi_sangue.growl", "Grunhido de zumbi de Sangue");
		add("subtitles.ordemparanormal.zumbi_bestial.growl", "Grunhido de zumbi de Sangue bestial");
		
		// Itens
		add("ordemparanormal.ritual_item.ritual_learned", "�7Apenas um item com um s�mbolo estranho.");
		add("ordemparanormal.ritual_item.ritual_unknown", "�7Parece emanar uma presen�a estranha...");
		add("ordemparanormal.ritual_item.cursed_with", "�7Amaldi�oado com o ritual ");
		add(OPItems.GRIMORIO_ENERGIA.get(), "Grim�rio de Energia");
		add(OPItems.GRIMORIO_SANGUE.get(), "Grim�rio de Sangue");
		add(OPItems.ORGAO.get(), "�rg�o");
		add(OPItems.CINZAS.get(), "Cinzas");
		add(OPBlocks.ALTAR_TRANSCENDER.get(), "Altar de Transcend�ncia");

		// Itens de Rituais
		add(OPItems.RITUAL_DESCARNAR.get(), "Livro com s�mbolo");
		add(OPItems.RITUAL_DECADENCIA.get(), "Caveira com s�mbolo");
		add(OPItems.RITUAL_CICATRIZACAO.get(), "Cristal com espiral");
		add(OPItems.RITUAL_CONSUMIR_MANANCIAL.get(), "Pap�l com s�mbolo");
		add(OPItems.RITUAL_ARMADURA_SANGUE.get(), "Capacete enferrujado");
		
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
		add("ordemparanormal.nex.attribute_points", "Pontos de Atributo");
		add(ParanormalAttribute.STRENGTH.name, "For�a");
		add(ParanormalAttribute.VIGOR.name, "Vigor");
		add(ParanormalAttribute.PRESENCE.name, "Presen�a");
		addDescription(ParanormalAttribute.STRENGTH.name,
				"�6For�a �rdetermina o qu�o forte o Paranormal te tornou.",
				"�8Aumenta a velocidade em que voc� quebra blocos.",
				"�8Aumenta o dano de seus ataques.");
		addDescription(ParanormalAttribute.VIGOR.name,
				"�4Vigor �rdetermina o qu�o resistente o Paranormal te tornou.",
				"�8Aumenta o quanto comida sacia sua fome.",
				"�8Aumenta a sua vida m�xima.");
		addDescription(ParanormalAttribute.PRESENCE.name,
				"�9Presen�a �rdetermina o qu�o poderoso o Paranormal te tornou.",
				"�8Aumenta o dano de seus rituais.",
				"�8Aumenta o seu esfor�o m�ximo.");

		// Rituais
		add("ordemparanormal.rituals", "Rituais");
		add("ordemparanormal.ritual.ingredient", "Ingrediente");
		add("ordemparanormal.ritual.consumes", "Consome");

		addRitual(OPAPI.DESCARNAR, "Descarnar",
				"Descarna o alvo, causando dano de Sangue cont�nuo.");

		addRitual(OPAPI.DECADENCIA, "Decad�ncia",
				"Apodrece o alvo, causando dano de Morte e o enfraquecendo.");

		addRitual(OPAPI.CICATRIZACAO, "Cicatriza��o",
				"Cicatriza as feridas do alvo ou de voc� mesmo.");

		addRitual(OPAPI.CONSUMIR_MANANCIAL, "Consumir Manancial",
				"Consome a vida ao redor, fortificando sua vida.");

		addRitual(OPAPI.ARMADURA_SANGUE, "Armadura de Sangue",
				"Usa seu sangue para endurecer sua pele, servindo como armadura.");

		// Poderes
		add("ordemparanormal.nex.power_points", "Pontos de Poder");
		add("ordemparanormal.power.owned", "Poder adquirido");
		add("ordemparanormal.power.active", "Poder ativo");
		add("ordemparanormal.power.passive", "Poder passivo");

		addPower(OPAPI.TEST_POWER, "Test 1",
				"Test line 1",
				"Test line 2");

		addPower(OPAPI.TEST_POWER_2, "Test 2",
				"Test line 1",
				"Test line 2");

		addPower(OPAPI.TEST_POWER_3, "Test 3",
				"Test line 1",
				"Test line 2");

		addPower(OPAPI.TEST_POWER_4, "Test 4",
				"Test line 1",
				"Test line 2");

		addPower(OPAPI.TEST_POWER_5,"Test 5",
				"Test line 1",
				"Test line 2");

		addPower(OPAPI.TEST_POWER_6,"Test 6",
				"Test line 1",
				"Test line 2");

		addPower(OPAPI.TEST_POWER_7,"Test 7",
				"Test line 1",
				"Test line 2");

		addPower(OPAPI.TEST_POWER_8,"Test 8",
				"Test line 1",
				"Test line 2");

		// Efeitos
		add(OPEffects.BLEED.get(), "Sangrando");
		add(OPEffects.LIFE_ABSORBED.get(), "Vida Absorvida");
		add(OPEffects.SKIN_REINFORCED.get(), "Pele Refor�ada");

		// Atalhos de Teclado
		add("ordemparanormal.key_category", "Mod Ordem Paranormal");
		add("ordemparanormal.key.nex_screen", "Mostrar Atributos Paranormais");
		for (int i = 1; i < 6; i++){
			add("ordemparanormal.key.power_" + i, "Usar slot de poder " + i);
		}

		// Livros dentro do jogo
		add("ordemparanormal.mansion_books.record_495", "O irm�o de Sangue, Lairon, voltou de sua expedi��o... Ele disse que encontrou um portal nas plan�cies da suposta \"Ordem\". N�s o recriamos e espiamos. Nunca mais ouviremos Lairon. Maldito bastardo! Pelo menos conseguimos recursos de l�.");
		add("ordemparanormal.mansion_books.record_001.page_1", "Dia 1: \nJerome me disse que o local � seguro para uma habita��o. O primeiro h�spede me contou algo terr�vel... Uma n�voa surgiu quando eu estava voltando da floresta, aquilo n�o estava na minha previs�o do tempo... \nEle estava com uma mordida imensa na perna e se recusou a falar sobre ela...");
		add("ordemparanormal.mansion_books.record_001.page_2", "Dia 2: \nQUE PORRA � ESSA?!");
		add("ordemparanormal.devil_church_books.blood_book.page_1", "O Sangue � a entidade do sentimento. Ele busca a intensidade: dor, obsess�o, paix�o, amor, fome, �dio - tudo que envolve sentir uma emo��o extrema agrada a entidade de Sangue.");
		add("ordemparanormal.devil_church_books.blood_book.page_2", "Tudo come�a pelo Sangue. O Sangue � o fluxo que banha a eternidade do Outro Lado.");

		// Outros
		add("ordemparanormal.nex.title", "Exposi��o Paranormal");
		add("ordemparanormal.nex.abbreviation", "NeX");
		add("ordemparanormal.health_points", "PV");
		add("ordemparanormal.effort_points", "PE");
		add("ordemparanormal.effort_points.full_name", "Pontos de Esfor�o");
	}

}
