package com.guga.ordemparanormal.datagen.client.lang;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEnUsProvider extends ModLangProvider {

	public ModEnUsProvider(DataGenerator gen) {
		super(gen, OrdemParanormal.MOD_ID, "en_us");
	}
	// Adicionar traduções em EN-US
	@Override
	protected void addTranslations() {
		// Sons
		add("subtitles.ordemparanormal.zumbi_sangue.growl", "Blood zombie growl");
		add("subtitles.ordemparanormal.zumbi_bestial.growl", "Bestial Blood zombie growl");
		addSubtitle("zombie.convert", "Zombie converts to Blood zombie");
		addSubtitle("skeleton.convert", "Skeleton converts to Blood zombie");
		addSubtitle("corpse_death", "Villager corpse is dissipated");
		addSubtitle("corpse.convert", "Villager corpse converts to Blood zombie");
		addSubtitle("corpse.hurt", "Villager corpse is hurt");
		addSubtitle("zumbi_sangue.hurt", "Blood zombie is hurt");
		addSubtitle("zumbi_sangue.death", "Blood zombie is killed");
		addSubtitle("zumbi_sangue.convert", "Blood zombie converts to bestial Blood zombie");
		addSubtitle("ritual.learned", "Ritual is learned");
		addSubtitle("ritual.forgotten", "Ritual is forgotten");
		addSubtitle("ritual.used", "Ritual is used");
		addSubtitle("ritual.failed", "Ritual fails");
		addSubtitle("blood_power_used", "Blood power is used");
		addSubtitle("energy_power_used", "Energy power is used");
		addSubtitle("death_power_used", "Death power is used");
		addSubtitle("knowledge_power_used", "Knowledge power is used");
		addSubtitle("fear_power_used", "Fear power is used");
		addSubtitle("aberracao.death", "Flesh aberration dies");
		addSubtitle("aberracao.hurt", "Flesh aberration is hurt");
		addSubtitle("aberracao.idle", "Flesh aberration moans in pain");
		addSubtitle("aberracao.step", "Flesh aberration walking");
		addSubtitle("aberracao.attack", "Flesh aberration attacks");
		addSubtitle("aberracao.abocanhar", "Flesh aberration bites");

		// -------------------------------------------------------------

		// Itens
		add("ordemparanormal.ritual_item.ritual_learned", "§7Just an item with a strange symbol.");
		add("ordemparanormal.ritual_item.ritual_unknown", "§7Seems to emanate a strange presence...");
		add("ordemparanormal.ritual_item.cursed_with", "§7Cursed with the ritual ");
		add("ordemparanormal.ritual_item.requires", "§7To understand this item, you require: ");
		add("ordemparanormal.patchouli.manual_ocultista.title", "Occultism Handbook");
		add("ordemparanormal.patchouli.manual_ocultista.landing_text", "Japa's Diary. $(br)Made for those who need a little push to understand the occult, and to share my experiences with the $(OS).$(br)Feel free to add new discoveries to this book... It's yours now.");
		add(OPItems.ORGAO.get(), "Organ");
		add(OPItems.CINZAS.get(), "Ashes");
		add(OPBlocks.ALTAR_TRANSCENDER.get(), "Transcendence altar");
		add(OPItems.PERGAMINHO_ANTIGO.get(), "Ancient scroll");
		add(OPBlocks.MESA_SANGUE.get(), "Blood curse table");
		add(OPBlocks.MESA_ENERGIA.get(), "Energy curse table");
		add(OPBlocks.MESA_MORTE.get(), "Death curse table");
		add(OPBlocks.MESA_CONHECIMENTO.get(), "Knowledge curse table");

			// Grupo de itens
			add("itemGroup.ordemparanormal", "Paranormal Order");
			add("itemGroup.ordemparanormal.rituals", "Rituals");
			add("itemGroup.ordemparanormal.mobs", "Paranormal Creatures");

			// Itens de Rituais
			add(OPItems.RITUAL_DESCARNAR.get(), "Cursed book");
			add(OPItems.RITUAL_DECADENCIA.get(), "Skull with symbol");
			add(OPItems.RITUAL_CICATRIZACAO.get(), "Spiral crystal");
			add(OPItems.RITUAL_CONSUMIR_MANANCIAL.get(), "Cursed papers");
			add(OPItems.RITUAL_ARMADURA_SANGUE.get(), "Rusty helmet");
			add(OPItems.RITUAL_AMALDICOAR_ARMA.get(), "Rusty gold ring");
			add(OPItems.RITUAL_ARMA_ATROZ.get(), "Bloody silver ring");
			add(OPItems.RITUAL_ARMA_VELOZ.get(), "Tied bow");
			add(OPItems.RITUAL_HEMOFAGIA.get(), "Blood necklace");
			add(OPItems.RITUAL_APRIMORAMENTO_FISICO.get(), "Prickly bracelet");
			add(OPItems.RITUAL_VELOCIDADE_MORTAL.get(), "Burnt poem");
			add(OPItems.RITUAL_TRANSFERENCIA_VITAL.get(), "Rusty syringe");
			add(OPItems.RITUAL_SALTO_FANTASMA.get(), "Energized Clock");
			add(OPItems.RITUAL_TELEPORTE.get(), "Familiar photo");
			add(OPItems.RITUAL_ESPIRAIS_DA_PERDICAO.get(), "Marked mirror");
			add(OPItems.RITUAL_LUZ.get(), "Sparkling fragment");
			add(OPItems.RITUAL_INEXISTIR.get(), "Indescribable book");
			add(OPItems.RITUAL_PERTUBACAO.get(), "Curved cross");

			// Componentes Ritualisticos
			add(OPItems.COMPONENTE_VAZIO.get(), "Empty ritualistic ingredient pouch");
			add(OPItems.COMPONENTE_SANGUE.get(), "Blood ritualistic ingredients");
			add(OPItems.COMPONENTE_ENERGIA.get(), "Energy ritualistic ingredients");
			add(OPItems.COMPONENTE_MORTE.get(), "Death ritualistic ingredients");
			add(OPItems.COMPONENTE_CONHECIMENTO.get(), "Knowledge ritualistic ingredients");

			// Ovos
			add(OPItems.BESTIAL_OVO.get(), "Bestial Blood zombie spawn egg");
			add(OPItems.ZUMBI_SANGUE_OVO.get(), "Blood zombie spawn egg");
			add(OPItems.ZUMBI_SECO_OVO.get(), "Skeletal Blood zombie spawn egg");
			add(OPItems.ZUMBI_ESPINHENTO_OVO.get(), "Spiky Blood zombie spawn egg");
			add(OPItems.ABERRACAO_CARNE_OVO.get(), "Flesh aberration spawn egg");

		// -------------------------------------------------------------

		// Entidades
		add(OPEntities.BESTIAL.get(), "Bestial Blood zombie");
		add(OPEntities.ZUMBI_SANGUE.get(), "Blood zombie");
		add(OPEntities.VILLAGER_CORPO.get(), "Villager corpse");
		add(OPEntities.ZUMBI_SECO.get(), "Skeletal Blood zombie");
		add(OPEntities.ZUMBI_ESPINHENTO.get(), "Spiky Blood zombie");
		add(OPEntities.ABERRACAO_CARNE.get(), "Flesh aberration");

			// Profissões de Villagers
			add(OPProfessions.OCULTISTA_SANGUE.get(), "Blood Occultist");
			add(OPProfessions.OCULTISTA_ENERGIA.get(), "Energy Occultist");
			add(OPProfessions.OCULTISTA_CONHECIMENTO.get(), "Knowledge Occultist");
			add(OPProfessions.OCULTISTA_MORTE.get(), "Death Occultist");

		// -------------------------------------------------------------

		// Elementos
		add(ParanormalElement.SANGUE.getTranslationKey(), "Blood");
		add(ParanormalElement.MORTE.getTranslationKey(), "Death");
		add(ParanormalElement.CONHECIMENTO.getTranslationKey(), "Knowledge");
		add(ParanormalElement.ENERGIA.getTranslationKey(), "Energy");
		add(ParanormalElement.MEDO.getTranslationKey(), "Fear");
		add(ParanormalDamageSource.DANO_SANGUE.elementDmgTranslationKey(), "Blood damage");
		add(ParanormalDamageSource.DANO_MORTE.elementDmgTranslationKey(), "Death damage");
		add(ParanormalDamageSource.DANO_ENERGIA.elementDmgTranslationKey(), "Energy damage");
		add(ParanormalDamageSource.DANO_CONHECIMENTO.elementDmgTranslationKey(), "Knowledge damage");
		add(ParanormalDamageSource.DANO_MEDO.elementDmgTranslationKey(), "Fear damage");

		// Atributos paranormais
		add("ordemparanormal.nex.attribute_points", "Attribute Points");
		add(ParanormalAttribute.STRENGTH.name, "Strength");
		add(ParanormalAttribute.VIGOR.name, "Vigor");
		add(ParanormalAttribute.PRESENCE.name, "Presence");

		// -------------------------------------------------------------

		// Rituais
		add("ordemparanormal.rituals", "Rituals");
		add("ordemparanormal.ritual.ingredient", "Ingredient");
		add("ordemparanormal.ritual.consumes", "Consumes");

		add(OPRituals.DESCARNAR, "Skinning",
				"Skins the target, dealing continuous blood damage.");
		add(OPRituals.DECADENCIA, "Decay",
				"Imbues your next attack with spirals inflict decay. Hold a sword and keep the ingredients in your inventory to use this ritual.");
		add(OPRituals.CICATRIZACAO, "Cicatrization",
				"Heals three hearts for you or your target.");
		add(OPRituals.CONSUMIR_MANANCIAL, "Consume Lifeforce",
				"Consumes the surrounding life, giving you three temporary hearts of life.");
		add(OPRituals.ARMADURA_SANGUE, "Blood Armor",
				"Uses your blood to harden your skin, giving you four armor points.");
		add(OPRituals.ARMA_ATROZ, "Atrocious Weapon",
				"Makes the weapon you're holding more dangerous, now dealing more damage. Hold a sword and keep the ingredients in your inventory to use this ritual.");
		add(OPRituals.ARMA_VELOZ, "Nimble Weapon",
				"Makes the weapon you're holding more swift. Hold a sword and keep the ingredients in your inventory to use this ritual.");
		add(OPRituals.AMALDICOAR_ARMA, "Curse Weapon",
				"Curses the weapon you're holding with knowledge sigils, now dealing more damage. Hold a sword and keep the ingredients in your inventory to use this ritual.");
		add(OPRituals.HEMOFAGIA, "Hematophagy",
				"Sucks the target's blood, transfering four and a half hearts to you.");
		add(OPRituals.APRIMORAMENTO_FISICO, "Physical Upgrade",
				"Makes your body reach extraordinary physical conditions, giving you resistance and strength effects.");
		add(OPRituals.LAMINA_MEDO, "Fear's Blade",
				"Conjures an impossible flame, a glimpse of something that shouldn't exist, on the blade that you're holding, which begins to deal lethal amounts of damage. Hold a sword to use this ritual.");
		add(OPRituals.VELOCIDADE_MORTAL, "Deadly Velocity",
				"Alters the time perception of your body, that starts to reach extraordinary speeds, giving you the effect of speed.");
		add(OPRituals.MEDO_TANGIVEL, "Tangible Fear",
				"Your body becomes a manifestation of Fear, making you immune to mundane effects. You become immune to any non-paranormal damage source.");
		add(OPRituals.TRANSFERENCIA_VITAL, "Vitality Transfer",
				"Transfers four and a half hearts to your target.");
		add(OPRituals.SALTO_FANTASMA, "Phantom Jump",
				"Teleports you 10 blocks away in the direction you are looking.");
		add(OPRituals.TELEPORTE, "Teleport",
				"If used while crouching, saves the current location, and then if used without crouching, teleports you or your target to the saved location.");
		add(OPRituals.ESPIRAIS_DA_PERDICAO, "Spirals of Doom",
				"When used, weakens the target, causing the weakness effect.");
		add(OPRituals.LUZ, "Light",
				"Creates a shiny orb of pure energy. The orb can be easily broken by human fists and is intangible.");
		add(OPRituals.INEXISTIR, "Unexist",
				"Distorts your target's existance, dealing serious amounts of Knowledge damage.");
		add(OPRituals.PERTURBACAO, "Disturbance",
				"Confunses the mind of your target, making it impossible for them to move.");
		add(OPRituals.ALTERAR_MEMORIA, "Alter Memories",
				"Distorts the memories of your target. Villagers are more friendly towards you, lowering their prices.");

		// Poderes
		add("ordemparanormal.nex.power_points", "Power Points");
		add("ordemparanormal.power.owned", "Power acquired");
		add("ordemparanormal.power.active", "Active power");
		add("ordemparanormal.power.passive", "Passive power");
		add("ordemparanormal.power.requisites", "Requisites");

		add(OPPowers.AFINIDADE_SANGUE, "Affinity with Blood",
				"You're connected with the entity of Blood, discarding the need for Blood ingredients and making you resistant to Blood effects, but also weak to Death effects.");
		add(OPPowers.SANGUE_FERRO, "Iron Blood",
				"Your veins are bathed by the paranormal, making you more resistant, providing 2 extra health hearts.");
		add(OPPowers.SANGUE_FERRO_2, "Iron Blood II",
				"Your blood is completely flooded with hate and violence, providing 3 extra health hearts.");
		add(OPPowers.PUNHO_ENRAIVECIDO, "Enraged Fist",
				"Your blood hardens on your hand. When using this power, your next attacks using your fists will deal extra damage.");
		add(OPPowers.PUNHO_ENRAIVECIDO_2, "Enraged Fist II",
				"Your blood hardens permanently on your hands. All your attacks with your fists will deal extra damage.");
		add(OPPowers.SANGUE_VIVO, "Living Blood",
				"Your blood is always flowing violently, healing you faster when hurt.");
		add(OPPowers.SANGUE_VISCERAL, "Visceral Blood",
				"Pain awakens feelings inside you that you never thought existed. You deal more damage when hurt");
		add(OPPowers.ADRENALINA, "Adrenaline",
				"Your body is abnormally filled with adrenaline during dangerous situations, making you more agile.");
		add(OPPowers.ADRENALINA_2, "Adrenaline II",
				"Your entire body is able to produce high doses of adrenaline, making you even more agile when hurt.");
		add(OPPowers.FLAGELO, "Scourge",
				"You exchange your own pain for power, receiving 2 effort points but losing 4 hearts of health.");
		add(OPPowers.ABSORVER_AGONIA, "Absorb Agony",
				"You absorb the agony of those in pain. Everytime you attack, there's a chance you may heal up to 2 and a half hearts of health.");
		add(OPPowers.DIETA_ADAPTADA, "Adapted Diet",
				"Your body adapted to absorb things that once brought you negative effects.");
		add(OPPowers.DIETA_ADAPTADA_2, "Adapted Diet II",
				"Your body benefits from consuming grotesque and unhealthy things, giving you positive effects from eating them.");
		add(OPPowers.VAMPIRISMO, "Vampirism",
				"You consume the blood of your target, dealing damage to them but healing yourself.");
		add(OPPowers.VAMPIRISMO_2, "Vampirism II",
				"Intensifies the power Vampirism, increasing the healing power and also receiving more speed.");
		add(OPPowers.MEDO_TANGIVEL, "Tangible Fear",
				"You learn the ritual Tangible Fear, which you can cast by using this power.");

		add(OPPowers.AFINIDADE_ENERGIA, "Affinity with Energy",
				"You're connected with the entity of Energy, discarding the need for Energy ingredients and making you resistant to Energy effects, but also weak to Knowledge effects.");
		add(OPPowers.CAMPO_PROTETOR, "Protective Field",
				"Randomly, you might be protected by a paranormal barrier around your body while blocking an attack with a shield. The barrier will repel any attackers that hit you and set them on fire.");
		add(OPPowers.CAMPO_PROTETOR_2, "Protective Field II",
				"Increases the distance the attacker is repelled and the amount of time the attacker will burn.");
		add(OPPowers.GRAVIDADE_DISTORCIDA, "Distorted Gravity",
				"Lowers your gravity when used.");
		add(OPPowers.GRAVIDADE_DISTORCIDA_2, "Distorted Gravity II",
				"Lowers your gravity even more when used, and for a longer period of time.");
		add(OPPowers.CASUALIDADE_FORTUITA, "Fortuitous Chance",
				"When mining, there is a chance you might find rare minerals that you couldn't find before.");

		add(OPPowers.AFINIDADE_MORTE, "Affinity with Death",
				"You're connected with the entity of Death, discarding the need for Death ingredients and making you resistant to Death effects, but also weak to Energy effects.");
		add(OPPowers.POTENCIAL_APRIMORADO, "Enhanced Potential",
				"You can go past your limits, spending more effort than normal, receiving 2 effort points.");
		add(OPPowers.POTENCIAL_APRIMORADO_2, "Enhanced Potential II",
				"You can go even further past your limits, receiving 3 effort points.");
		add(OPPowers.PUTREFATO,"Foul",
				"Your strikes, in addition to causing damage, also alter the time perception of your target's physical matter. Unarmed strikes might cause decay.");
		add(OPPowers.PUTREFATO_2,"Foul II",
				"Your strikes cause decay more frequently.");
		add(OPPowers.POTENCIAL_REAPROVEITADO, "Reused Potential",
				"You take advantage of lost moments when receiving damage, being able to recover effort points when getting hurt.");
		add(OPPowers.ESCAPAR_MORTE, "Swerve Death",
				"Your body can utilize lost moments to stop you from losing yours, saving you from fatal blows. But, it doesn't save you if you're already near death...");
		add(OPPowers.SURTO_TEMPORAL, "Temporal Surge",
				"Your time perception is altered with a brief impulse of adrenaline, increasing the speed of your attacks.");
		add(OPPowers.PERIMETRO_ESPIRAL, "Spiral Perimeter",
				"You drain the potential energy of those around you, slowing them down while also making your faster.");
		add(OPPowers.LEMBRAR_DA_MORTE, "Remember Death",
				"The presence of moments lost by time encourage you to go onward. When near any undead, you receive the Speed effect.");
		add(OPPowers.ABSORVER_ENTROPIA, "Absorb Entropy",
				"Death consumes all. When killing any being, you get an amount of hearts depending on the strength of that being.");
		add(OPPowers.LAMINA_MEDO, "Fear's Blade",
				"You learn the ritual Fear's Blade, which you can cast by using this power.");

		add(OPPowers.AFINIDADE_CONHECIMENTO, "Affinity with Knowledge",
				"You're connected with the entity of Knowledge, discarding the need for Knowledge ingredients and making you resistant to Knowledge effects, but also weak to Blood effects.");
		add(OPPowers.CONHECIMENTO_AMPLIADO, "Amplified Knowledge",
				"Expands your mind to supernatural levels, increasing the amount of rituals that you can learn.");
		add(OPPowers.CONHECIMENTO_AMPLIADO_2, "Amplified Knowledge II",
				"Expands your mind to even higher levels, increasing the amount of rituals that you can learn.");

		// Maldições
		add(OPCurses.ATROZ.getTranslationKey(), "Atrocious");
		add(OPCurses.VELOZ.getTranslationKey(), "Nimble");
		add(OPCurses.DECADENTE.getTranslationKey(), "Decaying");
		add(OPCurses.AMALDICOADA.getTranslationKey(), "Cursed");
		add(OPCurses.LAMINA_MEDO.getTranslationKey(), "Blade of Fear");

		// -------------------------------------------------------------

		// Efeitos
		add(OPEffects.BLEED.get(), "Bleeding");
		add(OPEffects.LIFE_ABSORBED.get(), "Life Absorbed");
		add(OPEffects.SKIN_REINFORCED.get(), "Skin Reinforced");
		add(OPEffects.DECAY.get(), "Decay");
		add(OPEffects.TEMPORAL_SURGE.get(), "Temporal Surge");
		add(OPEffects.ENRAGED_FIST.get(), "Enraged Fist");
		add(OPEffects.SWERVE_DEATH.get(), "Swerve Death");
		add(OPEffects.DISTORTED_GRAVITY.get(), "Distorted Gravity");
		add(OPEffects.TANGIBLE_FEAR.get(), "Tangible Fear");

		// -------------------------------------------------------------

		// Atalhos de Teclado
		add("ordemparanormal.key_category", "Paranormal Order Mod");
		for (int i = 1; i < 6; i++) {
			add("ordemparanormal.key.power_" + i, "Use power slot " + i);
		}

		// -------------------------------------------------------------

		// Livros dentro do jogo
		add("ordemparanormal.mansion_books.record_495",
				"Blood brother Lairon came back from his expedition... He said that he found a portal in the supposed \"Order's\" plains. We recreated it and watched it closely. We will never listen to Lairon again. Damned bastard! At least we got resources from that.");
		add("ordemparanormal.mansion_books.record_001.page_1",
				"Day 1: \nJerome told me this place is safe for my habitation. The first guest told me something horrible... When I was returning from the forest, a fog appeared. It wasn't in my weather predictions... \nHe had a big bite on the leg, and didn't want to talk about it...");
		add("ordemparanormal.mansion_books.record_001.page_2", "Day 2: \nWHAT THE FUCK IS THAT?!");
		add("ordemparanormal.devil_church_books.blood_book.page_1",
				"Blood is the entity of emotion. It searches for intensity: pain, obsession, passion, love, hunger, hate - everything that involves feeling an extreme emotion pleases the entity of Blood.");
		add("ordemparanormal.devil_church_books.blood_book.page_2",
				"Everything begins with Blood. Blood is the flow that bathes the eternity of the Other Side.");

		// -------------------------------------------------------------

		// Mensagem de mortes
		addDeath("paranormalBlood", "%1$s was torn apart by the intensity of Blood");
		addDeath("paranomalDeath", "%1$s was consumed by the spiral of Death");
		addDeath("paranormalEnergy", "%1$s was fried by the chaos of Energy");
		addDeath("paranormalKnowledge", "%1$s was forgotten by the immensity of Knowledge");
		addDeath("paranormalFear", "%1$s discovered the impossible");
		addDeath("medoCreature", "%2$s showed the impossible to %1$s");
		addDeath("sangueCreature", "%1$s was torn apart by %2$s");
		addDeath("conhecimentoCreature", "%1$s was erased from reality by %2$s");
		addDeath("morteCreature", "%1$s was consumed by %2$s");
		addDeath("energiaCreature", "%1$s was distorted out of reality by %2$s");
		addDeath("medoCurse", "%1$s was killed by an item cursed with an impossible flame");
		addDeath("sangueCurse", "%1$s was killed by an item cursed with the intensity of Blood");
		addDeath("conhecimentoCurse", "%1$s was killed by an item cursed with the immensity of Knowledge");
		addDeath("morteCurse", "%1$s was killed by an item cursed with the spiral of Death");
		addDeath("energiaCurse", "%1$s was killed by an item cursed with the chaos of Energy");
		addDeath("sangueRitual", "%1$s was torn apart by the intensity of Blood summoned by %2$s");
		addDeath("morteRitual", "%1$s was consumed by the spiral of Death summoned by %2$s");
		addDeath("energiaRitual", "%1$s was fried by the chaos of Energy summoned by %2$s");
		addDeath("conhecimentoRitual", "%1$s was forgotten by the immensity of Knowledge summoned by %2$s");
		addDeath("medoRitual", "%1$s discovered the impossible summoned by %2$s");

			// Mensagem de mortes por poder
			addDeath("vampirismo", "%1$s had its blood drained out by %2$s");

		// -------------------------------------------------------------

		// Progresso
		addAdvancement("root", "Other Side", "The paranormal doesn't come to our reality easily... Or atleast it should be that way.");
		addAdvancement("enter_fog", "Lack of Visibility", "The humid atmosphere created by this dense fog is truly terrifying... Is this even natural?");
		addAdvancement("learn_ritual", "Understand the Other Side", "Aha! I finally understand this cursed item, that was it the entire time! All I needed to do was TRANSCEND...");
		addAdvancement("receive_power", "Capabilities of the Other Side", "What a strange sensation. This is definitely not normal but... it's very cool.");
		addAdvancement("paranormal_creature", "Abominations of the Other Side", "That was... What even was that?! I thought they were just tales, it can't be...");
		addAdvancement("amaldicoar_arma", "Curse Weapon", "Learn the ritual \"Amaldiçoar Arma\".");
		addAdvancement("aprimoramento_fisico", "Physical Upgrade", "Learn the ritual \"Physical Upgrade\".");
		addAdvancement("arma_atroz", "Atrocious Weapon", "Learn the ritual \"Atrocious Weapon\".");
		addAdvancement("arma_veloz", "Nimble Weapon", "Learn the ritual \"Nimble Weapon\".");
		addAdvancement("armadura_sangue", "Blood Armor", "Learn the ritual \"Blood Armor\".");
		addAdvancement("cicatrizacao", "Cicatrization", "Learn the ritual \"Cicatrization\".");
		addAdvancement("consumir_manancial", "Consume Lifeforce", "Learn the ritual \"Consume Lifeforce\".");
		addAdvancement("decadencia", "Decay", "Learn the ritual \"Decay\".");
		addAdvancement("descarnar", "Skinning", "Learn the ritual \"Skinning\".");
		addAdvancement("espirais_da_perdicao", "Spirals of Doom", "Learn the ritual \"Spirals of Doom\".");
		addAdvancement("hemofagia", "Hematophagy", "Learn the ritual \"Hematophagy\".");
		addAdvancement("inexistir", "Unexist", "Learn the ritual \"Unexist\".");
		addAdvancement("luz", "Light", "Learn the ritual \"Light\".");
		addAdvancement("perturbacao", "Disturbance", "Learn the ritual \"Disturbance\".");
		addAdvancement("salto_fantasma", "Phantom Jump", "Learn the ritual \"Phantom Jump\".");
		addAdvancement("teleporte", "Teleport", "Learn the ritual \"Teleport\".");
		addAdvancement("transferencia_vital", "Vitality Transfer", "Learn the ritual \"Vitality Transfer\".");
		addAdvancement("velocidade_mortal", "Deadly Velocity", "Learn the ritual \"Deadly Velocity\".");

		// --------------------------------------------------------------

		// Outros
		add("ordemparanormal.nex.title", "Paranormal Exposure");
		add("ordemparanormal.nex.abbreviation", "PeX");
		add("ordemparanormal.health_points", "HP");
		add("ordemparanormal.effort_points", "EP");
		add("ordemparanormal.effort_points.full_name", "Effort Points");
	}

}
