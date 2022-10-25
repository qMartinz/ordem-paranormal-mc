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

public class ModEnUsProvider extends LanguageProvider {

	public ModEnUsProvider(DataGenerator gen) {
		super(gen, OrdemParanormal.MOD_ID, "en_us");
	}

	private void addRitual(AbstractRitual ritual, String name, String description) {
		add(ritual.getTranslationKey(), name);
		add(ritual.getTranslationKey() + ".description", description);
	}

	private void addPower(PlayerPower power, String name, String description) {
		add(power.getTranslationKey(), name);
		add(power.getTranslationKey() + ".description", description);
	}

	// Adicionar traduções em EN-US
	@Override
	protected void addTranslations() {
		// Grupo de itens
		add("itemGroup.ordemparanormal", "Paranormal Order");
		add("itemGroup.ordemparanormal.rituals", "Rituals");
		add("itemGroup.ordemparanormal.mobs", "Paranormal Creatures");

		// Sons
		add("subtitles.ordemparanormal.zumbi_sangue.growl", "Blood zombie growl");
		add("subtitles.ordemparanormal.zumbi_bestial.growl", "Bestial Blood zombie growl");

		// Itens
		add("ordemparanormal.ritual_item.ritual_learned", "§7Just an item with a strange symbol.");
		add("ordemparanormal.ritual_item.ritual_unknown", "§7Seems to emanate a strange presence...");
		add("ordemparanormal.ritual_item.cursed_with", "§7Cursed with the ritual");
		add(OPItems.ORGAO.get(), "Organ");
		add(OPItems.CINZAS.get(), "Ashes");
		add(OPBlocks.ALTAR_TRANSCENDER.get(), "Transcendence Altar");
		add(OPItems.PERGAMINHO_ANTIGO.get(), "Ancient Scroll");

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
		add(OPItems.RITUAL_TELEPORTE.get(), "Marked mirror");

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

		// Entidades
		add(OPEntities.BESTIAL.get(), "Bestial Blood zombie");
		add(OPEntities.ZUMBI_SANGUE.get(), "Blood zombie");
		add(OPEntities.VILLAGER_CORPO.get(), "Villager corpse");
		add(OPEntities.ZUMBI_SECO.get(), "Skeletal Blood zombie");
		add(OPEntities.ZUMBI_ESPINHENTO.get(), "Spiky Blood zombie");

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

		// Rituais
		add("ordemparanormal.rituals", "Rituals");
		add("ordemparanormal.ritual.ingredient", "Ingredient");
		add("ordemparanormal.ritual.consumes", "Consumes");

		addRitual(OPRituals.DESCARNAR, "Skinning",
				"Skins the target, dealing continuous blood damage.");
		addRitual(OPRituals.DECADENCIA, "Decay",
				"Imbues your next attack with spirals inflict decay.");
		addRitual(OPRituals.CICATRIZACAO, "Cicatrization",
				"Heals the your wounds or your target's.");
		addRitual(OPRituals.CONSUMIR_MANANCIAL, "Consume Lifeforce",
				"Consumes the surrounding life, fortifying yours.");
		addRitual(OPRituals.ARMADURA_SANGUE, "Blood Armor",
				"Uses your blood to harden your skin, serving as armor.");
		addRitual(OPRituals.ARMA_ATROZ, "Atrocious Weapon",
				"Makes the weapon you're holding more dangerous.");
		addRitual(OPRituals.ARMA_VELOZ, "Nimble Weapon",
				"Makes the weapon you're holding more swift.");
		addRitual(OPRituals.AMALDICOAR_ARMA, "Curse Weapon",
				"Curses the weapon you're holding with knowledge sigils.");
		addRitual(OPRituals.HEMOFAGIA, "Hematophagy",
				"Sucks the target's blood, transfering their vitality to you.");
		addRitual(OPRituals.APRIMORAMENTO_FISICO, "Physical Upgrade",
				"Makes your body reach extraordinary physical conditions, making you more resistant and powerful.");
		addRitual(OPRituals.LAMINA_MEDO, "Blade of Fear",
				"Conjures an impossible flame, a glimpse of something that shouldn't exist, on a blade that starts to deal lethal amounts of damage.");
		addRitual(OPRituals.VELOCIDADE_MORTAL, "Velocidade Mortal",
				"Alters the time perception of your body, that starts to reach extraordinary speeds.");
		addRitual(OPRituals.MEDO_TANGIVEL, "Tangible Fear",
				"Your body becomes a manifestation of Fear, making you immune to mundane effects. You become immune to any non-paranormal damage source.");
		addRitual(OPRituals.TRANSFERENCIA_VITAL, "Vitality Transfer",
				"Transfers your vitality to your target.");
		addRitual(OPRituals.SALTO_FANTASMA, "Phantom Jump",
				"Teleports you 10 blocks away in the direction you are looking.");
		addRitual(OPRituals.TELEPORTE, "Teleport",
				"If used while crouching, saves the current location, and then if used without crouching, teleports you to the saved location.");
		addRitual(OPRituals.ESPIRAIS_DA_PERDICAO, "Spirals of Doom",
				"When used it weakens the target");

		// Poderes
		add("ordemparanormal.nex.power_points", "Power Points");
		add("ordemparanormal.power.owned", "Power acquired");
		add("ordemparanormal.power.active", "Active power");
		add("ordemparanormal.power.passive", "Passive power");
		add("ordemparanormal.power.requisites", "Requisites");

		addPower(OPPowers.AFINIDADE_SANGUE, "Affinity with Blood",
				"You're connected with the entity of Blood, discarding the need for Blood ingredients and making you resistant to Blood effects, but also weak to Death effects.");
		addPower(OPPowers.SANGUE_FERRO, "Iron Blood",
				"Your veins are bathed by the paranormal, making you more resistant, providing 2 extra health hearts.");
		addPower(OPPowers.SANGUE_FERRO_2, "Iron Blood II",
				"Your blood is completely flooded with hate and violence, providing 3 extra health hearts.");
		addPower(OPPowers.PUNHO_ENRAIVECIDO, "Enraged Fist",
				"Your blood hardens on your hand. When using this power, your next attacks using your fists will deal extra damage.");
		addPower(OPPowers.PUNHO_ENRAIVECIDO_2, "Enraged Fist II",
				"Your blood hardens permanently on your hands. All your attacks with your fists will deal extra damage.");
		addPower(OPPowers.SANGUE_VIVO, "Living Blood",
				"Your blood is always flowing violently, healing you faster when hurt.");
		addPower(OPPowers.SANGUE_VISCERAL, "Visceral Blood",
				"Pain awakens feelings inside you that you never thought existed. You deal more damage when hurt");
		addPower(OPPowers.ADRENALINA, "Adrenaline",
				"Your body is abnormally filled with adrenaline during dangerous situations, making you more agile.");
		addPower(OPPowers.ADRENALINA_2, "Adrenaline II",
				"Your entire body is able to produce high doses of adrenaline, making you even more agile when hurt.");
		addPower(OPPowers.FLAGELO, "Scourge",
				"You exchange your own pain for power, receiving 2 effort points but losing 4 hearts of health.");
		addPower(OPPowers.ABSORVER_AGONIA, "Absorb Agony",
				"You absorb the agony of those in pain. Everytime you attack, there's a chance you may heal up to 2 and a half hearts of health.");
		addPower(OPPowers.DIETA_ADAPTADA, "Adapted Diet",
				"Your body adapted to absorb things that once brought you negative effects.");
		addPower(OPPowers.DIETA_ADAPTADA_2, "Adapted Diet II",
				"Your body benefits from consuming grotesque and unhealthy things, giving you positive effects from eating them.");
		addPower(OPPowers.VAMPIRISMO, "Vampirism",
				"You consume the blood of your target, dealing damage to them but healing yourself.");
		addPower(OPPowers.VAMPIRISMO_2, "Vampirism II",
				"Intensifies the power Vampirism, increasing the healing power and also receiving more speed.");
		addPower(OPPowers.MEDO_TANGIVEL, "Tangible Fear",
				"You learn the ritual Tangible Fear, which you can cast by using this power.");

		addPower(OPPowers.AFINIDADE_ENERGIA, "Affinity with Energy",
				"You're connected with the entity of Energy, discarding the need for Energy ingredients and making you resistant to Energy effects, but also weak to Knowledge effects.");

		addPower(OPPowers.AFINIDADE_MORTE, "Affinity with Death",
				"You're connected with the entity of Death, discarding the need for Death ingredients and making you resistant to Death effects, but also weak to Energy effects.");
		addPower(OPPowers.POTENCIAL_APRIMORADO, "Enhanced Potential",
				"You can go past your limits, spending more effort than normal, receiving 2 effort points.");
		addPower(OPPowers.POTENCIAL_APRIMORADO_2, "Enhanced Potential II",
				"You can go even further past your limits, receiving 3 effort points.");
		addPower(OPPowers.PUTREFATO, "Foul",
				"Your strikes, in addition to causing damage, also alter the time perception of your target's physical matter. Unarmed strikes might cause decay.");
		addPower(OPPowers.PUTREFATO_2, "Foul II",
				"Your strikes cause decay more frequently.");
		addPower(OPPowers.POTENCIAL_REAPROVEITADO, "Reused Potential",
				"You take advantage of lost moments when receiving damage, being able to recover effort points when getting hurt.");
		addPower(OPPowers.ESCAPAR_MORTE, "Swerve Death",
				"Your body can utilize lost moments to stop you from losing yours, saving you from fatal blows.");
		addPower(OPPowers.SURTO_TEMPORAL, "Temporal Surge",
				"Your time perception is altered with a brief impulse of adrenaline, increasing the speed of your attacks.");
		addPower(OPPowers.PERIMETRO_ESPIRAL, "Spiral Perimeter",
				"You drain the potential energy of those around you, slowing them down while also making your faster.");
		addPower(OPPowers.LEMBRAR_DA_MORTE, "Remember Death",
				"The presence of moments lost by time encourage you to go onward. When near any undead, you receive the Speed effect.");
		addPower(OPPowers.ABSORVER_ENTROPIA, "Absorb Entropy",
				"Death consumes all. When killing any being, you get an amount of hearts depending on the strength of that being.");

		addPower(OPPowers.AFINIDADE_CONHECIMENTO, "Affinity with Knowledge",
				"You're connected with the entity of Knowledge, discarding the need for Knowledge ingredients and making you resistant to Knowledge effects, but also weak to Blood effects.");

		// Maldições
		add(OPCurses.ATROZ.getTranslationKey(), "Atrocious");
		add(OPCurses.VELOZ.getTranslationKey(), "Nimble");
		add(OPCurses.DECADENTE.getTranslationKey(), "Decaying");
		add(OPCurses.AMALDICOADA.getTranslationKey(), "Cursed");

		// Efeitos
		add(OPEffects.BLEED.get(), "Bleeding");
		add(OPEffects.LIFE_ABSORBED.get(), "Life Absorbed");
		add(OPEffects.SKIN_REINFORCED.get(), "Skin Reinforced");
		add(OPEffects.DECAY.get(), "Decay");
		add(OPEffects.TEMPORAL_SURGE.get(), "Temporal Surge");
		add(OPEffects.ENRAGED_FIST.get(), "Enraged Fist");
		add(OPEffects.SWERVE_DEATH.get(), "Swerve Death");

		// Atalhos de Teclado
		add("ordemparanormal.key_category", "Paranormal Order Mod");
		add("ordemparanormal.key.nex_screen", "Show Paranormal Attributes");
		for (int i = 1; i < 6; i++) {
			add("ordemparanormal.key.power_" + i, "Use power slot " + i);
		}

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

		// Outros
		add("ordemparanormal.nex.title", "Paranormal Exposure");
		add("ordemparanormal.nex.abbreviation", "PeX");
		add("ordemparanormal.health_points", "HP");
		add("ordemparanormal.effort_points", "EP");
		add("ordemparanormal.effort_points.full_name", "Effort Points");
	}

}
