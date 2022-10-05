package com.guga.ordemparanormal.datagen.client.lang;

import com.guga.ordemparanormal.api.ElementDamage;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModPtBrProvider extends LanguageProvider {

	public ModPtBrProvider(DataGenerator gen) {
		super(gen, OrdemParanormal.MOD_ID, "pt_br");
	}

	private void addDescription(String key, String line1, String line2, String line3) {
		add(key + ".description.line_1", line1);
		add(key + ".description.line_2", line2);
		add(key + ".description.line_3", line3);
	}

	private void addDescription(String key, String line1, String line2) {
		add(key + ".description.line_1", line1);
		add(key + ".description.line_2", line2);
	}

	private void addRitual(AbstractRitual ritual, String name, String description) {
		add(ritual.getTranslationKey(), name);
		add(ritual.getTranslationKey() + ".description", description);
	}

	private void addPower(PlayerPower power, String name, String description) {
		add(power.getTranslationKey(), name);
		add(power.getTranslationKey() + ".description", description);
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
		add(OPItems.RITUAL_DESCARNAR.get(), "Livro amaldi�oado");
		add(OPItems.RITUAL_DECADENCIA.get(), "Caveira com s�mbolo");
		add(OPItems.RITUAL_CICATRIZACAO.get(), "Cristal espiral");
		add(OPItems.RITUAL_CONSUMIR_MANANCIAL.get(), "Pap�l amaldi�oado");
		add(OPItems.RITUAL_ARMADURA_SANGUE.get(), "Capacete enferrujado");

		// Componentes Ritualisticos
		add(OPItems.COMPONENTE_VAZIO.get(), "Saco vazio de ingredientes ritual�sticos");
		add(OPItems.COMPONENTE_SANGUE.get(), "Ingredientes ritualisticos de Sangue");
		add(OPItems.COMPONENTE_ENERGIA.get(), "Ingredientes ritualisticos de Energia");
		add(OPItems.COMPONENTE_MORTE.get(), "Ingredientes ritualisticos de Morte");
		add(OPItems.COMPONENTE_CONHECIMENTO.get(), "Ingredientes ritualisticos de Conhecimento");

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
		add(ParanormalElement.SANGUE.getTranslationKey(), "Sangue");
		add(ParanormalElement.MORTE.getTranslationKey(), "Morte");
		add(ParanormalElement.CONHECIMENTO.getTranslationKey(), "Conhecimento");
		add(ParanormalElement.ENERGIA.getTranslationKey(), "Energia");
		add(ParanormalElement.MEDO.getTranslationKey(), "Medo");
		add(ElementDamage.elementDmgTranslationKey(ParanormalElement.SANGUE), "Dano de Sangue");
		add(ElementDamage.elementDmgTranslationKey(ParanormalElement.MORTE), "Dano de Morte");
		add(ElementDamage.elementDmgTranslationKey(ParanormalElement.ENERGIA), "Dano de Energia");
		add(ElementDamage.elementDmgTranslationKey(ParanormalElement.CONHECIMENTO), "Dano de Conhecimento");
		add(ElementDamage.elementDmgTranslationKey(ParanormalElement.MEDO), "Dano de Medo");

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
				"�8Permite o uso de rituais mais poderosos.",
				"�8Aumenta o seu esfor�o m�ximo.");

		// Rituais
		add("ordemparanormal.rituals", "Rituais");
		add("ordemparanormal.ritual.ingredient", "Ingrediente");
		add("ordemparanormal.ritual.consumes", "Consome");

		addRitual(OPRituals.DESCARNAR, "Descarnar",
				"Descarna o alvo, causando dano de Sangue cont�nuo.");
		addRitual(OPRituals.DECADENCIA, "Decad�ncia",
				"Imbui seu pr�ximo ataque com espirais que inflingem apodrecimento.");
		addRitual(OPRituals.CICATRIZACAO, "Cicatriza��o",
				"Cicatriza as feridas do alvo ou de voc� mesmo.");
		addRitual(OPRituals.CONSUMIR_MANANCIAL, "Consumir Manancial",
				"Consome a vida ao redor, fortificando sua vida.");
		addRitual(OPRituals.ARMADURA_SANGUE, "Armadura de Sangue",
				"Usa seu sangue para endurecer sua pele, servindo como armadura.");
		addRitual(OPRituals.ARMA_ATROZ, "Arma Atroz",
				"Torna a arma que voc� est� segurando mais perigosa.");
		addRitual(OPRituals.ARMA_VELOZ, "Arma Veloz",
				"Torna a arma que voc� est� segurando mais r�pida.");
		addRitual(OPRituals.AMALDICOAR_ARMA, "Amaldi�oar Arma",
				"Amaldi�oa a arma que voc� est� segurando com sigilos de conhecimento.");
		addRitual(OPRituals.HEMOFAGIA, "Hemofagia",
				"Suga o sangue de seu alvo, transferindo a vitalidade dele para voc�.");
		addRitual(OPRituals.APRIMORAMENTO_FISICO, "Aprimoramento Fisico",
				"Seu corpo fica em condições fisicas extraordinarias");

		// Poderes
		add("ordemparanormal.nex.power_points", "Pontos de Poder");
		add("ordemparanormal.power.owned", "Poder adquirido");
		add("ordemparanormal.power.active", "Poder ativo");
		add("ordemparanormal.power.passive", "Poder passivo");
		add("ordemparanormal.power.requisites", "Requis�tos");

		addPower(OPPowers.AFINIDADE_SANGUE, "Afinidade com Sangue",
				"Voc� se conecta � entidade de Sangue, descartando a necessidade de ingredientes de Sangue e te tornando resistente a efeitos de Sangue, mas voc� se torna fraco a efeitos de Morte.");
		addPower(OPPowers.SANGUE_FERRO, "Sangue de Ferro",
				"Suas veias s�o banhadas pelo paranormal, te fornecendo 2 cora��es extras de vida.");
		addPower(OPPowers.SANGUE_FERRO_2, "Sangue de Ferro II",
				"Seu sangue � completamente irrigado pelo �dio e viol�ncia, te fornecendo 3 cora��es extras de vida.");
		addPower(OPPowers.PUNHO_ENRAIVECIDO, "Punho Enraivecido",
				"O seu Sangue endurece e cobre suas m�os. Ao utilizar esse poder, seus pr�ximos ataques com seus punhos causar�o dano extra.");
		addPower(OPPowers.PUNHO_ENRAIVECIDO_2, "Punho Enraivecido II",
				"O seu Sangue endurece e cobre suas m�os permanentemente. Todos os seus ataques com seus punhos causar�o dano extra.");
		addPower(OPPowers.SANGUE_VIVO, "Sangue Vivo",
				"Seu sangue sempre flui violentamente, te curando mais rapidamente quando ferido.");
		addPower(OPPowers.SANGUE_VISCERAL, "Sangue Visceral",
				"A dor desperta sentimentos que voc� nunca soube que existiam. Voc� causa mais dano quando ferido.");
		addPower(OPPowers.ADRENALINA, "Adrenalina",
				"Seu corpo � anormalmente cheio de adrenalina, te tornando mais �gil quando ferido.");
		addPower(OPPowers.ADRENALINA_2, "Adrenalina II",
				"Seu corpo todo � capaz de produz grandes doses de adrenalina, te tornando ainda mais �gil quando ferido.");
		addPower(OPPowers.FLAGELO, "Flagelo",
				"Voc� troca a sua pr�pria dor por poder, recebendo 2 pontos de esfor�o mas perdendo 4 cora��es de vida.");
		addPower(OPPowers.ABSORVER_AGONIA, "Absorver Agonia",
				"Voc� absorve a agonia daqueles em dor. Toda vez que ataca, h� uma chance de que voc� recupere at� 2 cora��es e meio de vida.");
		addPower(OPPowers.DIETA_ADAPTADA, "Dieta Adaptada",
				"Seu corpo se adaptou a absorver coisas que antes traziam malef�cios a sua sa�de.");
		addPower(OPPowers.DIETA_ADAPTADA_2, "Dieta Adaptada II",
				"Seu corpo se beneficia consumindo coisas grotescas e que fazem mal � sua sa�de, fornecendo efeitos positivos ao consumir tais itens.");
		addPower(OPPowers.VAMPIRISMO, "Vampirismo",
				"Voc� consome o sangue de seu alvo, se curando e causando dano a ele.");
		addPower(OPPowers.VAMPIRISMO_2, "Vampirismo II",
				"Intensifica o poder Vampirismo, curando mais vida e tamb�m recebendo velocidade tempor�ria.");
		addPower(OPPowers.MEDO_TANGIVEL, "Medo Tangivel",
				"Voc� aprende o ritual Medo Tang�vel, que pode conjurar utilizando esse poder.");

		addPower(OPPowers.AFINIDADE_ENERGIA, "Afinidade com Energia",
				"Voc� se conecta � entidade de Energia, descartando a necessidade de ingredientes de Energia e te tornando resistente a efeitos de Energia, mas voc� se torna fraco a efeitos de Conhecimento.");
		addPower(OPPowers.AFINIDADE_MORTE, "Afinidade com Morte",
				"Voc� se conecta � entidade de Morte, descartando a necessidade de ingredientes de Morte e te tornando resistente a efeitos de Morte, mas voc� se torna fraco a efeitos de Energia.");
		addPower(OPPowers.POTENCIAL_APRIMORADO, "Potencial Aprimorado",
				"Voc� consegue passar dos seus limites, se esfor�ando mais do que o normal, ganhando 2 pontos de esfor�o.");

		addPower(OPPowers.AFINIDADE_CONHECIMENTO, "Afinidade com Conhecimento",
				"Voc� se conecta � entidade de Conhecimento, descartando a necessidade de ingredientes de Conhecimento e te tornando resistente a efeitos de Conhecimento, mas voc� se torna fraco a efeitos de Sangue.");

		// Maldi��es
		add(OPCurses.ATROZ.getTranslationKey(), "Atroz");
		add(OPCurses.VELOZ.getTranslationKey(), "Veloz");
		add(OPCurses.DECADENTE.getTranslationKey(), "Decadente");
		add(OPCurses.AMALDICOAR_ARMA.getTranslationKey(), "Amaldi�oada");

		// Efeitos
		add(OPEffects.BLEED.get(), "Sangrando");
		add(OPEffects.LIFE_ABSORBED.get(), "Vida Absorvida");
		add(OPEffects.SKIN_REINFORCED.get(), "Pele Refor�ada");
		add(OPEffects.DECAY.get(), "Apodrecimento");

		// Atalhos de Teclado
		add("ordemparanormal.key_category", "Mod Ordem Paranormal");
		add("ordemparanormal.key.nex_screen", "Mostrar Atributos Paranormais");
		for (int i = 1; i < 6; i++) {
			add("ordemparanormal.key.power_" + i, "Usar slot de poder " + i);
		}

		// Livros dentro do jogo
		add("ordemparanormal.mansion_books.record_495",
				"O irm�o de Sangue, Lairon, voltou de sua expedi��o... Ele disse que encontrou um portal nas plan�cies da suposta \"Ordem\". N�s o recriamos e espiamos. Nunca mais ouviremos Lairon. Maldito bastardo! Pelo menos conseguimos recursos de l�.");
		add("ordemparanormal.mansion_books.record_001.page_1",
				"Dia 1: \nJerome me disse que o local � seguro para uma habita��o. O primeiro h�spede me contou algo terr�vel... Uma n�voa surgiu quando eu estava voltando da floresta, aquilo n�o estava na minha previs�o do tempo... \nEle estava com uma mordida imensa na perna e se recusou a falar sobre ela...");
		add("ordemparanormal.mansion_books.record_001.page_2", "Dia 2: \nQUE PORRA � ESSA?!");
		add("ordemparanormal.devil_church_books.blood_book.page_1",
				"O Sangue � a entidade do sentimento. Ele busca a intensidade: dor, obsess�o, paix�o, amor, fome, �dio - tudo que envolve sentir uma emo��o extrema agrada a entidade de Sangue.");
		add("ordemparanormal.devil_church_books.blood_book.page_2",
				"Tudo come�a pelo Sangue. O Sangue � o fluxo que banha a eternidade do Outro Lado.");

		// Outros
		add("ordemparanormal.nex.title", "Exposi��o Paranormal");
		add("ordemparanormal.nex.abbreviation", "NeX");
		add("ordemparanormal.health_points", "PV");
		add("ordemparanormal.effort_points", "PE");
		add("ordemparanormal.effort_points.full_name", "Pontos de Esfor�o");
	}

}
