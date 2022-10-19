package com.guga.ordemparanormal.datagen.client.lang;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModPtBrProvider extends LanguageProvider {

	public ModPtBrProvider(DataGenerator gen) {
		super(gen, OrdemParanormal.MOD_ID, "pt_br");
	}

	private void addRitual(AbstractRitual ritual, String name, String description) {
		add(ritual.getTranslationKey(), name);
		add(ritual.getTranslationKey() + ".description", description);
	}

	private void addPower(PlayerPower power, String name, String description) {
		add(power.getTranslationKey(), name);
		add(power.getTranslationKey() + ".description", description);
	}

	// Adicionar traduções em PT-BR
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
		add("ordemparanormal.ritual_item.ritual_learned", "§7Apenas um item com um símbolo estranho.");
		add("ordemparanormal.ritual_item.ritual_unknown", "§7Parece emanar uma presença estranha...");
		add("ordemparanormal.ritual_item.cursed_with", "§7Amaldiçoado com o ritual ");
		add(OPItems.ORGAO.get(), "Órgão");
		add(OPItems.CINZAS.get(), "Cinzas");
		add(OPBlocks.ALTAR_TRANSCENDER.get(), "Altar de Transcendência");
		add(OPItems.PERGAMINHO_ANTIGO.get(), "Pergaminho Antigo");

		// Itens de Rituais
		add(OPItems.RITUAL_DESCARNAR.get(), "Livro amaldiçoado");
		add(OPItems.RITUAL_DECADENCIA.get(), "Caveira com símbolo");
		add(OPItems.RITUAL_CICATRIZACAO.get(), "Cristal espiral");
		add(OPItems.RITUAL_CONSUMIR_MANANCIAL.get(), "Papel amaldiçoado");
		add(OPItems.RITUAL_ARMADURA_SANGUE.get(), "Capacete enferrujado");
		add(OPItems.RITUAL_AMALDICOAR_ARMA.get(), "Anel dourado enferrujado");
		add(OPItems.RITUAL_ARMA_ATROZ.get(), "Anel sangrento de prata");
		add(OPItems.RITUAL_ARMA_VELOZ.get(), "Laço amarrado");
		add(OPItems.RITUAL_HEMOFAGIA.get(), "Colar de sangue");
		add(OPItems.RITUAL_APRIMORAMENTO_FISICO.get(), "Pulseira espinhenta");
		add(OPItems.RITUAL_VELOCIDADE_MORTAL.get(), "Poema queimado");
		add(OPItems.RITUAL_TRANSFERENCIA_VITAL.get(), "Seringa enferrujada");
		add(OPItems.RITUAL_SALTO_FANTASMA.get(), "Relógio energizado");
		add(OPItems.RITUAL_TELEPORTE.get(), "Foto familiar");

		// Componentes Ritualisticos
		add(OPItems.COMPONENTE_VAZIO.get(), "Saco vazio de ingredientes ritualísticos");
		add(OPItems.COMPONENTE_SANGUE.get(), "Ingredientes ritualísticos de Sangue");
		add(OPItems.COMPONENTE_ENERGIA.get(), "Ingredientes ritualísticos de Energia");
		add(OPItems.COMPONENTE_MORTE.get(), "Ingredientes ritualísticos de Morte");
		add(OPItems.COMPONENTE_CONHECIMENTO.get(), "Ingredientes ritualísticos de Conhecimento");

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

		// Elementos
		add(ParanormalElement.SANGUE.getTranslationKey(), "Sangue");
		add(ParanormalElement.MORTE.getTranslationKey(), "Morte");
		add(ParanormalElement.CONHECIMENTO.getTranslationKey(), "Conhecimento");
		add(ParanormalElement.ENERGIA.getTranslationKey(), "Energia");
		add(ParanormalElement.MEDO.getTranslationKey(), "Medo");
		add(ParanormalDamageSource.DANO_SANGUE.elementDmgTranslationKey(), "Dano de Sangue");
		add(ParanormalDamageSource.DANO_MORTE.elementDmgTranslationKey(), "Dano de Morte");
		add(ParanormalDamageSource.DANO_ENERGIA.elementDmgTranslationKey(), "Dano de Energia");
		add(ParanormalDamageSource.DANO_CONHECIMENTO.elementDmgTranslationKey(), "Dano de Conhecimento");
		add(ParanormalDamageSource.DANO_MEDO.elementDmgTranslationKey(), "Dano de Medo");

		// Atributos paranormais
		add("ordemparanormal.nex.attribute_points", "Pontos de Atributo");
		add(ParanormalAttribute.STRENGTH.name, "Força");
		add(ParanormalAttribute.VIGOR.name, "Vigor");
		add(ParanormalAttribute.PRESENCE.name, "Presença");

		// Rituais
		add("ordemparanormal.rituals", "Rituais");
		add("ordemparanormal.ritual.ingredient", "Ingrediente");
		add("ordemparanormal.ritual.consumes", "Consome");

		addRitual(OPRituals.DESCARNAR, "Descarnar",
				"Descarna o alvo, causando dano de Sangue contínuo.");
		addRitual(OPRituals.DECADENCIA, "Decadência",
				"Imbui seu próximo ataque com espirais que inflingem apodrecimento.");
		addRitual(OPRituals.CICATRIZACAO, "Cicatrização",
				"Cicatriza as feridas do alvo ou de você mesmo.");
		addRitual(OPRituals.CONSUMIR_MANANCIAL, "Consumir Manancial",
				"Consome a vida ao redor, fortificando sua vida.");
		addRitual(OPRituals.ARMADURA_SANGUE, "Armadura de Sangue",
				"Usa seu sangue para endurecer sua pele, servindo como armadura.");
		addRitual(OPRituals.ARMA_ATROZ, "Arma Atroz",
				"Torna a arma que você está segurando mais perigosa.");
		addRitual(OPRituals.ARMA_VELOZ, "Arma Veloz",
				"Torna a arma que você está segurando mais rápida.");
		addRitual(OPRituals.AMALDICOAR_ARMA, "Amaldiçoar Arma",
				"Amaldiçoa a arma que você está segurando com sigilos de conhecimento.");
		addRitual(OPRituals.HEMOFAGIA, "Hemofagia",
				"Suga o sangue de seu alvo, transferindo a vitalidade dele para você.");
		addRitual(OPRituals.APRIMORAMENTO_FISICO, "Aprimoramento Fisico",
				"Seu corpo atinge condições fisicas extraordinárias, se tornando mais resistente e poderoso.");
		addRitual(OPRituals.LAMINA_MEDO, "Lâmina do Medo",
				"Conjura uma chama impossível, um vislumbre de algo que não deveria existir, em uma lâmina que passa a causar dano letais.");
		addRitual(OPRituals.VELOCIDADE_MORTAL, "Velocidade Mortal",
				"Altera a percepção do tempo de seu corpo, que passa a atingir velocidades extraordinárias.");
		addRitual(OPRituals.MEDO_TANGIVEL, "Medo Tangível",
				"Seu corpo se torna uma manifestação do medo, te tornando imune a efeitos mundanos. Você passa a ser imune a qualquer dano não-paranormal.");
		addRitual(OPRituals.TRANSFERENCIA_VITAL, "Transferencia Vital",
				"Transfere sua vitalidade para o alvo do ritual.");
		addRitual(OPRituals.SALTO_FANTASMA, "Phantom Jump",
				"Te teleporta 10 blocos na direção que está olhando.");
		addRitual(OPRituals.TELEPORTE, "Teleport",
				"Se usado enquanto agacha, salva sua a posição atual. Se usado sem estar agachando, te teleporta para a posição salvada.");

		// Poderes
		add("ordemparanormal.nex.power_points", "Pontos de Poder");
		add("ordemparanormal.power.owned", "Poder adquirido");
		add("ordemparanormal.power.active", "Poder ativo");
		add("ordemparanormal.power.passive", "Poder passivo");
		add("ordemparanormal.power.requisites", "Requisítos");

		addPower(OPPowers.AFINIDADE_SANGUE, "Afinidade com Sangue",
				"Você se conecta à entidade de Sangue, descartando a necessidade de ingredientes de Sangue e te tornando resistente a efeitos de Sangue, mas você se torna fraco a efeitos de Morte.");
		addPower(OPPowers.SANGUE_FERRO, "Sangue de Ferro",
				"Suas veias são banhadas pelo paranormal, te fornecendo 2 corações extras de vida.");
		addPower(OPPowers.SANGUE_FERRO_2, "Sangue de Ferro II",
				"Seu sangue é completamente irrigado pelo ódio e violência, te fornecendo 3 corações extras de vida.");
		addPower(OPPowers.PUNHO_ENRAIVECIDO, "Punho Enraivecido",
				"O seu Sangue endurece e cobre suas mãos. Ao utilizar esse poder, seus próximos ataques com seus punhos causarão dano extra.");
		addPower(OPPowers.PUNHO_ENRAIVECIDO_2, "Punho Enraivecido II",
				"O seu Sangue endurece e cobre suas mãos permanentemente. Todos os seus ataques com seus punhos causarão dano extra.");
		addPower(OPPowers.SANGUE_VIVO, "Sangue Vivo",
				"Seu sangue sempre flui violentamente, te curando mais rapidamente quando ferido.");
		addPower(OPPowers.SANGUE_VISCERAL, "Sangue Visceral",
				"A dor desperta sentimentos que você nunca soube que existiam. Você causa mais dano quando ferido.");
		addPower(OPPowers.ADRENALINA, "Adrenalina",
				"Seu corpo é anormalmente cheio de adrenalina, te tornando mais ágil quando ferido.");
		addPower(OPPowers.ADRENALINA_2, "Adrenalina II",
				"Seu corpo todo é capaz de produz grandes doses de adrenalina, te tornando ainda mais ágil quando ferido.");
		addPower(OPPowers.FLAGELO, "Flagelo",
				"Você troca a sua própria dor por poder, recebendo 2 pontos de esforço mas perdendo 4 corações de vida.");
		addPower(OPPowers.ABSORVER_AGONIA, "Absorver Agonia",
				"Você absorve a agonia daqueles em dor. Toda vez que ataca, há uma chance de que você recupere até 2 corações e meio de vida.");
		addPower(OPPowers.DIETA_ADAPTADA, "Dieta Adaptada",
				"Seu corpo se adaptou a absorver coisas que antes traziam malefícios a sua saúde.");
		addPower(OPPowers.DIETA_ADAPTADA_2, "Dieta Adaptada II",
				"Seu corpo se beneficia consumindo coisas grotescas e que fazem mal à sua saúde, fornecendo efeitos positivos ao consumir tais itens.");
		addPower(OPPowers.VAMPIRISMO, "Vampirismo",
				"Você consome o sangue de seu alvo, se curando e causando dano a ele.");
		addPower(OPPowers.VAMPIRISMO_2, "Vampirismo II",
				"Intensifica o poder Vampirismo, curando mais vida e também recebendo velocidade temporária.");
		addPower(OPPowers.MEDO_TANGIVEL, "Medo Tangivel",
				"Você aprende o ritual Medo Tangível, que pode conjurar utilizando esse poder.");

		addPower(OPPowers.AFINIDADE_ENERGIA, "Afinidade com Energia",
				"Você se conecta à entidade de Energia, descartando a necessidade de ingredientes de Energia e te tornando resistente a efeitos de Energia, mas você se torna fraco a efeitos de Conhecimento.");

		addPower(OPPowers.AFINIDADE_MORTE, "Afinidade com Morte",
				"Você se conecta à entidade de Morte, descartando a necessidade de ingredientes de Morte e te tornando resistente a efeitos de Morte, mas você se torna fraco a efeitos de Energia.");
		addPower(OPPowers.POTENCIAL_APRIMORADO, "Potencial Aprimorado",
				"Você consegue passar dos seus limites, se esforçando mais do que o normal, ganhando 2 pontos de esforço.");
		addPower(OPPowers.POTENCIAL_APRIMORADO_2, "Potencial Aprimorado II",
				"Você consegue passar ainda mais dos seus limites, ganhando 3 pontos de esforço.");
		addPower(OPPowers.PUTREFATO,"Putrefato",
				"Seus golpes, além de causar danos, também alteram a percepção de tempo da matéria física dos alvos. Ataques desarmados podem causar apodrecimento.");
		addPower(OPPowers.PUTREFATO_2,"Putrefato II",
				"Seus golpes desarmados passam a causar apodrecimento com mais frequência.");
		addPower(OPPowers.POTENCIAL_REAPROVEITADO, "Potencial Reaproveitado",
				"Você se aproveita de momentos perdidos ao receber danos, podendo recuperar pontos de esforço ao ser atingido.");
		addPower(OPPowers.ESCAPAR_MORTE, "Escapar da Morte",
				"Seu corpo consegue utilizar momentos perdidos para impedir que você perca os seus, te salvando de ataques fatais.");
		addPower(OPPowers.SURTO_TEMPORAL, "Surto Temporal",
				"Sua percepção do tempo é alterada com um breve impulso de adrenalina, acelerando seus ataques.");
		addPower(OPPowers.PERIMETRO_ESPIRAL, "Perimetro Espiral",
				"Você suga a energia potencial daqueles ao seu redor, deixando-os lentos, enquanto você se torna mais rápido.");
		addPower(OPPowers.LEMBRAR_DA_MORTE, "Lembrar da Morte",
				"A presença de momentos perdidos pelo tempo te incentivam a seguir em frente. Quando estiver perto de mortos-vivos, você ganha o efeito de velocidade.");
		addPower(OPPowers.ABSORVER_ENTROPIA, "Absorver Entropia",
				"A Morte consome tudo. Ao matar algum ser, você ganha uma quantidade de corações dependentes da força de tal ser.");

		addPower(OPPowers.AFINIDADE_CONHECIMENTO, "Afinidade com Conhecimento",
				"Você se conecta à entidade de Conhecimento, descartando a necessidade de ingredientes de Conhecimento e te tornando resistente a efeitos de Conhecimento, mas você se torna fraco a efeitos de Sangue.");

		// Maldições
		add(OPCurses.ATROZ.getTranslationKey(), "Atroz");
		add(OPCurses.VELOZ.getTranslationKey(), "Veloz");
		add(OPCurses.DECADENTE.getTranslationKey(), "Decadente");
		add(OPCurses.AMALDICOADA.getTranslationKey(), "Amaldiçoada");

		// Efeitos
		add(OPEffects.BLEED.get(), "Sangrando");
		add(OPEffects.LIFE_ABSORBED.get(), "Vida Absorvida");
		add(OPEffects.SKIN_REINFORCED.get(), "Pele Reforçada");
		add(OPEffects.DECAY.get(), "Apodrecimento");
		add(OPEffects.TEMPORAL_SURGE.get(), "Surto Temporal");
		add(OPEffects.ENRAGED_FIST.get(), "Punho Enraivecido");
		add(OPEffects.SWERVE_DEATH.get(), "Escapar da Morte");

		// Atalhos de Teclado
		add("ordemparanormal.key_category", "Mod Ordem Paranormal");
		add("ordemparanormal.key.nex_screen", "Mostrar Atributos Paranormais");
		for (int i = 1; i < 6; i++) {
			add("ordemparanormal.key.power_" + i, "Usar slot de poder " + i);
		}

		// Livros dentro do jogo
		add("ordemparanormal.mansion_books.record_495",
				"O irm�o de Sangue, Lairon, voltou de sua expedição... Ele disse que encontrou um portal nas planícies da suposta \"Ordem\". Nós o recriamos e espiamos. Nunca mais ouviremos Lairon. Maldito bastardo! Pelo menos conseguimos recursos de lá.");
		add("ordemparanormal.mansion_books.record_001.page_1",
				"Dia 1: \nJerome me disse que o local é seguro para uma habitação. O primeiro hóspede me contou algo terrível... Uma névoa surgiu quando eu estava voltando da floresta, aquilo não estava na minha previsão do tempo... \nEle estava com uma mordida imensa na perna e se recusou a falar sobre ela...");
		add("ordemparanormal.mansion_books.record_001.page_2", "Dia 2: \nQUE PORRA É ESSA?!");
		add("ordemparanormal.devil_church_books.blood_book.page_1",
				"O Sangue é a entidade do sentimento. Ele busca a intensidade: dor, obsessão, paixão, amor, fome, ódio - tudo que envolve sentir uma emoção extrema agrada a entidade de Sangue.");
		add("ordemparanormal.devil_church_books.blood_book.page_2",
				"Tudo começa pelo Sangue. O Sangue é o fluxo que banha a eternidade do Outro Lado.");

		// Outros
		add("ordemparanormal.nex.title", "Exposição Paranormal");
		add("ordemparanormal.nex.abbreviation", "NeX");
		add("ordemparanormal.health_points", "PV");
		add("ordemparanormal.effort_points", "PE");
		add("ordemparanormal.effort_points.full_name", "Pontos de Esforço");
	}

}
