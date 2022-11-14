package com.guga.ordemparanormal.datagen.client.lang;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.*;
import net.minecraft.data.DataGenerator;

public class ModPtBrProvider extends ModLangProvider {

	public ModPtBrProvider(DataGenerator gen) {
		super(gen, OrdemParanormal.MOD_ID, "pt_br");
	}
	// Adicionar traduções em PT-BR
	@Override
	protected void addTranslations() {
		// Sons
		addSubtitle("zumbi_sangue.growl", "Grunhido de zumbi de Sangue");
		addSubtitle("zumbi_bestial.growl", "Grunhido de zumbi de Sangue bestial");
		addSubtitle("zombie.convert", "Zumbi converte-se em zumbi de Sangue");
		addSubtitle("skeleton.convert", "Esqueleto converte-se em zumbi de Sangue");
		addSubtitle("corpse.death", "Corpo de Aldeão se desfaz");
		addSubtitle("corpse.convert", "Corpo de Aldeão converte-se em zumbi de Sangue");
		addSubtitle("corpse.hurt", "Corpo de Aldeão é ferido");
		addSubtitle("zumbi_sangue.hurt", "Zumbi de Sangue é ferido");
		addSubtitle("zumbi_sangue.death", "Zumbi de Sangue é morto");
		addSubtitle("zumbi_sangue.convert", "Zumbi de Sangue converte-se em zumbi de Sangue bestial");
		addSubtitle("ritual.learned", "Ritual é aprendido");
		addSubtitle("ritual.forgotten", "Ritual é esquecido");
		addSubtitle("ritual.used", "Ritual é utilizado");
		addSubtitle("ritual.failed", "Ritual falha");
		addSubtitle("blood_power_used", "Poder de Sangue é utilizado");
		addSubtitle("energy_power_used", "Poder de Energia é utilizado");
		addSubtitle("death_power_used", "Poder de Morte é utilizado");
		addSubtitle("knowledge_power_used", "Poder de Conhecimento é utilizado");
		addSubtitle("fear_power_used", "Poder de Medo é utilizado");
		addSubtitle("aberracao.death", "Aberração de carne morre");
		addSubtitle("aberracao.hurt", "Aberração de carne é ferida");
		addSubtitle("aberracao.idle", "Aberração de carne geme de dor");
		addSubtitle("aberracao.step", "Aberração de carne caminha");
		addSubtitle("aberracao.attack", "Aberração de carne ataca");
		addSubtitle("aberracao.abocanhar", "Aberração de carne abocanha");

		// -------------------------------------------------------------

		// Itens
		add("ordemparanormal.ritual_item.ritual_learned", "§7Apenas um item com um símbolo estranho.");
		add("ordemparanormal.ritual_item.ritual_unknown", "§7Parece emanar uma presença estranha...");
		add("ordemparanormal.ritual_item.cursed_with", "§7Amaldiçoado com o ritual ");
		add("ordemparanormal.ritual_item.requires", "§7Para entender esse item, você precisa de: ");
		add("ordemparanormal.patchouli.manual_ocultista.title", "Manual do Ocultismo");
		add("ordemparanormal.patchouli.manual_ocultista.landing_text", "Diário de Japa. $(br)Feito para aqueles que precisam de um empurrãozinho para entender o oculto e para compartilhar minhas experiências com o $(OL). $(br)Sinta-se livre para adicionar novas descobertas nesse livro... Ele é seu agora.");
		add(OPItems.ORGAO.get(), "Órgão");
		add(OPItems.CINZAS.get(), "Cinzas");
		add(OPBlocks.ALTAR_TRANSCENDER.get(), "Altar de transcendência");
		add(OPItems.PERGAMINHO_ANTIGO.get(), "Pergaminho antigo");
		add(OPBlocks.MESA_SANGUE.get(), "Mesa de maldições de Sangue");
		add(OPBlocks.MESA_MORTE.get(), "Mesa de maldições de Morte");
		add(OPBlocks.MESA_ENERGIA.get(), "Mesa de maldições de Energia");
		add(OPBlocks.MESA_CONHECIMENTO.get(), "Mesa de maldições de Conhecimento");

			// Grupo de itens
			add("itemGroup.ordemparanormal", "Ordem Paranormal");
			add("itemGroup.ordemparanormal.rituals", "Rituais");
			add("itemGroup.ordemparanormal.mobs", "Criaturas Paranormais");

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
			add(OPItems.RITUAL_ESPIRAIS_DA_PERDICAO.get(), "Espelho marcado");
			add(OPItems.RITUAL_LUZ.get(), "Fragmento brilhante");
			add(OPItems.RITUAL_INEXISTIR.get(), "Livro indescritível");
			add(OPItems.RITUAL_PERTUBACAO.get(), "Cruz curvada");

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
			add(OPItems.ABERRACAO_CARNE_OVO.get(), "Ovo gerador de aberração de carne");

		// -------------------------------------------------------------

		// Entidades
		add(OPEntities.BESTIAL.get(), "Zumbi de Sangue bestial");
		add(OPEntities.ZUMBI_SANGUE.get(), "Zumbi de Sangue");
		add(OPEntities.VILLAGER_CORPO.get(), "Corpo de Aldeão");
		add(OPEntities.ZUMBI_SECO.get(), "Zumbi de Sangue seco");
		add(OPEntities.ZUMBI_ESPINHENTO.get(), "Zumbi de Sangue espinhento");
		add(OPEntities.ABERRACAO_CARNE.get(), "Aberração de carne");

			// Profissões de Villagers
			add(OPProfessions.OCULTISTA_SANGUE.get(), "Ocultista de Sangue");
			add(OPProfessions.OCULTISTA_CONHECIMENTO.get(), "Ocultista de Conhecimento");
			add(OPProfessions.OCULTISTA_ENERGIA.get(), "Ocultista de Energia");
			add(OPProfessions.OCULTISTA_MORTE.get(), "Ocultista de Morte");

			// Entidades de Blocos
			add("ordemparanormal.block.block_entity.curse_table", "Maldições");

		// -------------------------------------------------------------

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

		// -------------------------------------------------------------

		// Rituais
		add("ordemparanormal.rituals", "Rituais");
		add("ordemparanormal.ritual.ingredient", "Ingrediente");
		add("ordemparanormal.ritual.consumes", "Consome");

		add(OPRituals.DESCARNAR, "Descarnar",
				"Descarna o alvo, causando dano de Sangue contínuo.");
		add(OPRituals.DECADENCIA, "Decadência",
				"Imbui seu próximo ataque com espirais que inflingem apodrecimento. Segure uma espada e mantenha os ingredientes no inventário para utilizar esse ritual.");
		add(OPRituals.CICATRIZACAO, "Cicatrização",
				"Cura três corações do alvo ou de você mesmo.");
		add(OPRituals.CONSUMIR_MANANCIAL, "Consumir Manancial",
				"Consome a vida ao redor, te dando três corações temporários de vida.");
		add(OPRituals.ARMADURA_SANGUE, "Armadura de Sangue",
				"Usa seu sangue para endurecer sua pele, te dando quatro pontos de armadura.");
		add(OPRituals.ARMA_ATROZ, "Arma Atroz",
				"Torna a arma que você está segurando mais perigosa, passando a causar mais dano. Segure uma espada e mantenha os ingredientes no inventário para utilizar esse ritual.");
		add(OPRituals.ARMA_VELOZ, "Arma Veloz",
				"Torna a arma que você está segurando mais rápida. Segure uma espada e mantenha os ingredientes no inventário para utilizar esse ritual.");
		add(OPRituals.AMALDICOAR_ARMA, "Amaldiçoar Arma",
				"Amaldiçoa a arma que você está segurando com sigilos de conhecimento, passando a causar mais dano. Segure uma espada e mantenha os ingredientes no inventário para utilizar esse ritual.");
		add(OPRituals.HEMOFAGIA, "Hemofagia",
				"Suga o sangue de seu alvo, transferindo quatro corações e meio dele para você.");
		add(OPRituals.APRIMORAMENTO_FISICO, "Aprimoramento Fisico",
				"Seu corpo atinge condições fisicas extraordinárias, se dando o efeito de força e resistência.");
		add(OPRituals.LAMINA_MEDO, "Lâmina do Medo",
				"Conjura uma chama impossível, um vislumbre de algo que não deveria existir, na lâmina que estiver segurando, que passa a causar danos letais. Segure uma espada para utilizar esse ritual.");
		add(OPRituals.VELOCIDADE_MORTAL, "Velocidade Mortal",
				"Altera a percepção do tempo de seu corpo, que passa a atingir velocidades extraordinárias, te dando o efeito de velocidade.");
		add(OPRituals.MEDO_TANGIVEL, "Medo Tangível",
				"Seu corpo se torna uma manifestação do medo, te tornando imune a efeitos mundanos. Você passa a ser imune a qualquer dano não-paranormal.");
		add(OPRituals.TRANSFERENCIA_VITAL, "Transferencia Vital",
				"Transfere quatro corações e meio para o alvo do ritual.");
		add(OPRituals.SALTO_FANTASMA, "Salto Fantasma",
				"Te teleporta 10 blocos na direção que está olhando.");
		add(OPRituals.TELEPORTE, "Teleporte",
				"Se usado enquanto agacha, salva sua a posição atual. Se usado sem estar agachando, teleporta você ou seu alvo para a posição salvada.");
		add(OPRituals.ESPIRAIS_DA_PERDICAO, "Espirais da Perdição",
				"Quando usado, enfraquece o alvo, causando o efeito de fraqueza.");
		add(OPRituals.LUZ, "Luz",
				"Cria uma orbe brilhante de pura energia. no local em que você está olhando. A orbe pode ser facilmente quebrada por punhos humanos e é intangível.");
		add(OPRituals.INEXISTIR, "Inexistir",
				"Distorce a existência de seu alvo, causando gravíssimos danos de Conhecimento.");
		add(OPRituals.PERTURBACAO, "Perturbação",
				"Confunde a mente do alvo, impedindo-o de se mover.");
		add(OPRituals.ALTERAR_MEMORIA, "Alterar Memória",
				"Altera as memórias do alvo. Villagers se tornam mais amigáveis e diminuem seus preços.");

		// Poderes
		add("ordemparanormal.nex.power_points", "Pontos de Poder");
		add("ordemparanormal.power.owned", "Poder adquirido");
		add("ordemparanormal.power.active", "Poder ativo");
		add("ordemparanormal.power.passive", "Poder passivo");
		add("ordemparanormal.power.requisites", "Requisítos");

		add(OPPowers.AFINIDADE_SANGUE, "Afinidade com Sangue",
				"Você se conecta à entidade de Sangue, descartando a necessidade de ingredientes de Sangue e te tornando resistente a efeitos de Sangue, mas você se torna fraco a efeitos de Morte.");
		add(OPPowers.SANGUE_FERRO, "Sangue de Ferro",
				"Suas veias são banhadas pelo paranormal, te fornecendo 2 corações extras de vida.");
		add(OPPowers.SANGUE_FERRO_2, "Sangue de Ferro II",
				"Seu sangue é completamente irrigado pelo ódio e violência, te fornecendo 3 corações extras de vida.");
		add(OPPowers.PUNHO_ENRAIVECIDO, "Punho Enraivecido",
				"O seu Sangue endurece e cobre suas mãos. Ao utilizar esse poder, seus próximos ataques com seus punhos causarão dano extra.");
		add(OPPowers.PUNHO_ENRAIVECIDO_2, "Punho Enraivecido II",
				"O seu Sangue endurece e cobre suas mãos permanentemente. Todos os seus ataques com seus punhos causarão dano extra.");
		add(OPPowers.SANGUE_VIVO, "Sangue Vivo",
				"Seu sangue sempre flui violentamente, te curando mais rapidamente quando ferido.");
		add(OPPowers.SANGUE_VISCERAL, "Sangue Visceral",
				"A dor desperta sentimentos que você nunca soube que existiam. Você causa mais dano quando ferido.");
		add(OPPowers.ADRENALINA, "Adrenalina",
				"Seu corpo é anormalmente cheio de adrenalina, te tornando mais ágil quando ferido.");
		add(OPPowers.ADRENALINA_2, "Adrenalina II",
				"Seu corpo todo é capaz de produz grandes doses de adrenalina, te tornando ainda mais ágil quando ferido.");
		add(OPPowers.FLAGELO, "Flagelo",
				"Você troca a sua própria dor por poder, recebendo 2 pontos de esforço mas perdendo 4 corações de vida.");
		add(OPPowers.ABSORVER_AGONIA, "Absorver Agonia",
				"Você absorve a agonia daqueles em dor. Toda vez que ataca, há uma chance de que você recupere até 2 corações e meio de vida.");
		add(OPPowers.DIETA_ADAPTADA, "Dieta Adaptada",
				"Seu corpo se adaptou a absorver coisas que antes traziam malefícios a sua saúde.");
		add(OPPowers.DIETA_ADAPTADA_2, "Dieta Adaptada II",
				"Seu corpo se beneficia consumindo coisas grotescas e que fazem mal à sua saúde, fornecendo efeitos positivos ao consumir tais itens.");
		add(OPPowers.VAMPIRISMO, "Vampirismo",
				"Você consome o sangue de seu alvo, se curando e causando dano a ele.");
		add(OPPowers.VAMPIRISMO_2, "Vampirismo II",
				"Intensifica o poder Vampirismo, curando mais vida e também recebendo velocidade temporária.");
		add(OPPowers.MEDO_TANGIVEL, "Medo Tangivel",
				"Você aprende o ritual Medo Tangível, que pode conjurar utilizando esse poder.");

		add(OPPowers.AFINIDADE_ENERGIA, "Afinidade com Energia",
				"Você se conecta à entidade de Energia, descartando a necessidade de ingredientes de Energia e te tornando resistente a efeitos de Energia, mas você se torna fraco a efeitos de Conhecimento.");
		add(OPPowers.CAMPO_PROTETOR, "Campo Protetor",
				"Aleatoriamente, você pode ser protegido por uma barreira paranormal quando estiver bloqueando um ataque com um escudo, repelindo o atacante e o envolvendo em chamas.");
		add(OPPowers.CAMPO_PROTETOR_2, "Campo Protetor II",
				"Aumenta a distância que os atacantes são repelidos e o tempo em que queimarão.");
		add(OPPowers.GRAVIDADE_DISTORCIDA, "Gravidade Distorcida",
				"Diminui a sua gravidade quando utilizado.");
		add(OPPowers.GRAVIDADE_DISTORCIDA_2, "Gravidade Distorcida II",
				"Diminui a sua gravidade ainda mais quando utilizado, por um período mais longo de tempo.");
		add(OPPowers.CASUALIDADE_FORTUITA, "Casualidade Fortuita",
				"Quando minerar, haverá a chance de você encontrar minerais raros que você antes não conseguia encontrar.");

		add(OPPowers.AFINIDADE_MORTE, "Afinidade com Morte",
				"Você se conecta à entidade de Morte, descartando a necessidade de ingredientes de Morte e te tornando resistente a efeitos de Morte, mas você se torna fraco a efeitos de Energia.");
		add(OPPowers.POTENCIAL_APRIMORADO, "Potencial Aprimorado",
				"Você consegue passar dos seus limites, se esforçando mais do que o normal, ganhando 2 pontos de esforço.");
		add(OPPowers.POTENCIAL_APRIMORADO_2, "Potencial Aprimorado II",
				"Você consegue passar ainda mais dos seus limites, ganhando 3 pontos de esforço.");
		add(OPPowers.PUTREFATO,"Putrefato",
				"Seus golpes, além de causar danos, também alteram a percepção de tempo da matéria física dos alvos. Ataques desarmados podem causar apodrecimento.");
		add(OPPowers.PUTREFATO_2,"Putrefato II",
				"Seus golpes desarmados passam a causar apodrecimento com mais frequência.");
		add(OPPowers.POTENCIAL_REAPROVEITADO, "Potencial Reaproveitado",
				"Você se aproveita de momentos perdidos ao receber danos, podendo recuperar pontos de esforço ao ser atingido.");
		add(OPPowers.ESCAPAR_MORTE, "Escapar da Morte",
				"Seu corpo consegue utilizar momentos perdidos para impedir que você perca os seus, te salvando de ataques fatais. Porém, não funciona caso já esteja prestes a morrer...");
		add(OPPowers.SURTO_TEMPORAL, "Surto Temporal",
				"Sua percepção do tempo é alterada com um breve impulso de adrenalina, acelerando seus ataques.");
		add(OPPowers.PERIMETRO_ESPIRAL, "Perimetro Espiral",
				"Você suga a energia potencial daqueles ao seu redor, deixando-os lentos, enquanto você se torna mais rápido.");
		add(OPPowers.LEMBRAR_DA_MORTE, "Lembrar da Morte",
				"A presença de momentos perdidos pelo tempo te incentivam a seguir em frente. Quando estiver perto de mortos-vivos, você ganha o efeito de velocidade.");
		add(OPPowers.ABSORVER_ENTROPIA, "Absorver Entropia",
				"A Morte consome tudo. Ao matar algum ser, você ganha uma quantidade de corações dependentes da força de tal ser.");
		add(OPPowers.LAMINA_MEDO, "Lâmina do Medo",
				"Você aprende o ritual Lâmina do Medo, que pode conjurar utilizando esse poder.");

		add(OPPowers.AFINIDADE_CONHECIMENTO, "Afinidade com Conhecimento",
				"Você se conecta à entidade de Conhecimento, descartando a necessidade de ingredientes de Conhecimento e te tornando resistente a efeitos de Conhecimento, mas você se torna fraco a efeitos de Sangue.");
		add(OPPowers.CONHECIMENTO_AMPLIADO, "Conhecimento Ampliado",
				"Expande a sua mente para níveis sobrenaturais, aumentando a quantidade de rituais que você se torna capaz de aprender.");
		add(OPPowers.CONHECIMENTO_AMPLIADO_2, "Conhecimento Ampliado II",
				"Expande a sua mente para níveis ainda maiores, aumentando a quantidade de rituais que você se torna capaz de aprender.");


		// Maldições
		add(OPCurses.ATROZ.getTranslationKey(), "Atroz");
		add(OPCurses.VELOZ.getTranslationKey(), "Veloz");
		add(OPCurses.DECADENTE.getTranslationKey(), "Decadente");
		add(OPCurses.AMALDICOADA.getTranslationKey(), "Amaldiçoada");
		add(OPCurses.LAMINA_MEDO.getTranslationKey(), "Lâmina do Medo");

		// -------------------------------------------------------------

		// Efeitos
		add(OPEffects.BLEED.get(), "Sangrando");
		add(OPEffects.LIFE_ABSORBED.get(), "Vida Absorvida");
		add(OPEffects.SKIN_REINFORCED.get(), "Pele Reforçada");
		add(OPEffects.DECAY.get(), "Apodrecimento");
		add(OPEffects.TEMPORAL_SURGE.get(), "Surto Temporal");
		add(OPEffects.ENRAGED_FIST.get(), "Punho Enraivecido");
		add(OPEffects.SWERVE_DEATH.get(), "Escapar da Morte");
		add(OPEffects.DISTORTED_GRAVITY.get(), "Gravidade Distorcida");
		add(OPEffects.TANGIBLE_FEAR.get(), "Medo Tangível");

		// -------------------------------------------------------------

		// Atalhos de Teclado
		add("ordemparanormal.key_category", "Mod Ordem Paranormal");
		for (int i = 1; i < 6; i++) {
			add("ordemparanormal.key.power_" + i, "Usar slot de poder " + i);
		}

		// -------------------------------------------------------------

		// Livros dentro do jogo
		add("ordemparanormal.mansion_books.record_495",
				"O irmão de Sangue, Lairon, voltou de sua expedição... Ele disse que encontrou um portal nas planícies da suposta \"Ordem\". Nós o recriamos e espiamos. Nunca mais ouviremos Lairon. Maldito bastardo! Pelo menos conseguimos recursos de lá.");
		add("ordemparanormal.mansion_books.record_001.page_1",
				"Dia 1: \nJerome me disse que o local é seguro para uma habitação. O primeiro hóspede me contou algo terrível... Uma névoa surgiu quando eu estava voltando da floresta, aquilo não estava na minha previsão do tempo... \nEle estava com uma mordida imensa na perna e se recusou a falar sobre ela...");
		add("ordemparanormal.mansion_books.record_001.page_2", "Dia 2: \nQUE PORRA É ESSA?!");
		add("ordemparanormal.devil_church_books.blood_book.page_1",
				"O Sangue é a entidade do sentimento. Ele busca a intensidade: dor, obsessão, paixão, amor, fome, ódio - tudo que envolve sentir uma emoção extrema agrada a entidade de Sangue.");
		add("ordemparanormal.devil_church_books.blood_book.page_2",
				"Tudo começa pelo Sangue. O Sangue é o fluxo que banha a eternidade do Outro Lado.");

		// -------------------------------------------------------------

		// Mensagem de mortes
		addDeath("paranormalBlood", "%1$s foi dilacerado pela intensidade do Sangue");
		addDeath("paranomalDeath", "%1$s foi consumido pela espiral da Morte");
		addDeath("paranormalEnergy", "%1$s foi fritado pelo caos da Energia");
		addDeath("paranormalKnowledge", "%1$s foi esquecido pela imensidade do Conhecimento");
		addDeath("paranormalFear", "%1$s descobriu o impossível");
		addDeath("medoCreature", "%2$s mostrou o impossível para %1$s");
		addDeath("sangueCreature", "%1$s foi dilacerado por %2$s");
		addDeath("conhecimentoCreature", "%1$s foi apagado da realidade por %2$s");
		addDeath("morteCreature", "%1$s foi consumido por %2$s");
		addDeath("energiaCreature", "%1$s foi distorcido para fora da realidade por %2$s");
		addDeath("medoCurse", "%1$s foi morto por um item amaldiçoado com uma chama impossível");
		addDeath("sangueCurse", "%1$s foi morto por um item amaldiçoado com a intesidade do Sangue");
		addDeath("conhecimentoCurse", "%1$s foi morto por um item amaldiçoado com a imensidão do Conhecimento");
		addDeath("morteCurse", "%1$s foi morto por um item amaldiçoado com a espiral da Morte");
		addDeath("energiaCurse", "%1$s foi morto por um item amaldiçoado com o caos da Energia");
		addDeath("sangueRitual", "%1$s foi dilacerado pela intensidade do Sangue convocada por %2$s");
		addDeath("morteRitual", "%1$s foi consumido pela espiral da Morte convocada por %2$s");
		addDeath("energiaRitual", "%1$s foi fritado pelo caos da Energia convocado por %2$s");
		addDeath("conhecimentoRitual", "%1$s foi esquecido pela imensidão do Conhecimento convocada por %2$s");
		addDeath("medoRitual", "%1$s descobriu o impossível convocado por %2$s");

			// Mensagem de mortes por poder
			addDeath("vampirismo", "%1$s teve seu sangue drenado por %2$s");

		// -------------------------------------------------------------

		//Mensagem de comandos
		add("ordemparanormal.commands.nex.attributes.points.add.success", "Pontos de atributo adicionados com sucesso.");
		add("ordemparanormal.commands.nex.attributes.points.remove.success", "Pontos de atributo removidos com sucesso.");
		add("ordemparanormal.commands.nex.attributes.vigor.add.success", "Pontos de Vigor adicionados com sucesso.");
		add("ordemparanormal.commands.nex.attributes.vigor.remove.success", "Pontos de Vigor removidos com sucesso.");
		add("ordemparanormal.commands.nex.attributes.strength.add.success", "Pontos de Força adicionados com sucesso.");
		add("ordemparanormal.commands.nex.attributes.strength.remove.success", "Pontos de Força removidos com sucesso.");
		add("ordemparanormal.commands.nex.attributes.presence.add.success", "Pontos de Presença adicionados com sucesso.");
		add("ordemparanormal.commands.nex.attributes.presence.remove.success", "Pontos de Presença removidos com sucesso.");
		add("ordemparanormal.commands.nex.powers.points.add.success", "Pontos de poder adicionados com sucesso.");
		add("ordemparanormal.commands.nex.powers.points.remove.success", "Pontos de poder removidos com sucesso.");
		add("ordemparanormal.commands.nex.powers.add.success", "%1$s adicionado com sucesso.");
		add("ordemparanormal.commands.nex.powers.remove.success", "%1$s removido com sucesso.");
		add("ordemparanormal.commands.nex.rituals.slots.add.success", "Slots de ritual adicionados com sucesso.");
		add("ordemparanormal.commands.nex.rituals.slots.remove.success", "Slots de ritual removidos com sucesso.");
		add("ordemparanormal.commands.nex.rituals.add.success", "%1$s adicionado com sucesso.");
		add("ordemparanormal.commands.nex.rituals.remove.success", "%1$s removido com sucesso.");

		// -------------------------------------------------------------

		// Progresso
		addAdvancement("root", "Outro Lado", "O paranormal não vem para a nossa realidade facilmente... Ou pelo menos era para ser dessa forma.");
		addAdvancement("enter_fog", "Falta de Visibilidade", "A atmosfera úmida criada por essa densa névoa é verdadeiramente assustadora... Isso sequer é natural?");
		addAdvancement("learn_ritual", "Entender o Outro Lado", "Aha! Eu finalmente entendi esse item amaldiçoado, era isso esse tempo todo! Eu só precisava TRANSCENDER...");
		addAdvancement("receive_power", "Capacidades do Outro Lado", "Que sensação estranha. Isso com certeza não é normal mas... É muito legal.");
		addAdvancement("paranormal_creature", "Abominações do Outro Lado", "Aquilo foi... O que sequer foi aquilo?! Eu achava que eram apenas contos, não pode ser...");

		// --------------------------------------------------------------

		// Outros
		add("ordemparanormal.nex.title", "Exposição Paranormal");
		add("ordemparanormal.nex.abbreviation", "NeX");
		add("ordemparanormal.health_points", "PV");
		add("ordemparanormal.effort_points", "PE");
		add("ordemparanormal.effort_points.full_name", "Pontos de Esforço");
	}

}
