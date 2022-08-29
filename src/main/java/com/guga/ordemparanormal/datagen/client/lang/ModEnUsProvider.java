package com.guga.ordemparanormal.datagen.client.lang;

import com.guga.ordemparanormal.api.ElementDamage;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEnUsProvider extends LanguageProvider{

	public ModEnUsProvider(DataGenerator gen) {
		super(gen, OrdemParanormal.MOD_ID, "en_us");
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
		add(OPItems.GRIMORIO_ENERGIA.get(), "Energy grimoire");
		add(OPItems.GRIMORIO_SANGUE.get(), "Blood grimoire");
		add(OPItems.ORGAO.get(), "Organ");
		add(OPItems.CINZAS.get(), "Ashes");
		add(OPBlocks.ALTAR_TRANSCENDER.get(), "Transcendence Altar");

		// Itens de Rituais
		add(OPItems.RITUAL_DESCARNAR.get(), "Cursed book");
		add(OPItems.RITUAL_DECADENCIA.get(), "Skull with symbol");
		add(OPItems.RITUAL_CICATRIZACAO.get(), "Spiral crystal");
		add(OPItems.RITUAL_CONSUMIR_MANANCIAL.get(), "Cursed papers");
		add(OPItems.RITUAL_ARMADURA_SANGUE.get(), "Rusty helmet");
		
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
		add(ParanormalElement.BLOOD.getTranslationKey(), "Blood");
		add(ParanormalElement.DEATH.getTranslationKey(), "Death");
		add(ParanormalElement.KNOWLEDGE.getTranslationKey(), "Knowledge");
		add(ParanormalElement.ENERGY.getTranslationKey(), "Energy");
		add(ParanormalElement.FEAR.getTranslationKey(), "Fear");
		add(ElementDamage.elementDmgTranslationKey(ParanormalElement.BLOOD), "Blood damage");
		add(ElementDamage.elementDmgTranslationKey(ParanormalElement.DEATH), "Death damage");
		add(ElementDamage.elementDmgTranslationKey(ParanormalElement.ENERGY), "Energy damage");
		add(ElementDamage.elementDmgTranslationKey(ParanormalElement.KNOWLEDGE), "Knowledge damage");
		add(ElementDamage.elementDmgTranslationKey(ParanormalElement.FEAR), "Fear damage");

		// Atributos paranormais
		add("ordemparanormal.nex.attribute_points", "Attribute Points");
		add(ParanormalAttribute.STRENGTH.name, "Strength");
		add(ParanormalAttribute.VIGOR.name, "Vigor");
		add(ParanormalAttribute.PRESENCE.name, "Presence");
		addDescription(ParanormalAttribute.STRENGTH.name,
				"§6Strength §rdetermines how strong the Paranormal has made you.",
				"§8Increases the speed in wich you break blocks.",
				"§8Increases the damage of your attacks.");
		addDescription(ParanormalAttribute.VIGOR.name,
				"§4Vigor §rdetermines how resistant the Paranormal has made you.",
				"§8Increases how much your hunger is filled by eating.",
				"§8Increases your maximum health.");
		addDescription(ParanormalAttribute.PRESENCE.name,
				"§9Presence §rdetermines how powerful the Paranormal has made you.",
				"§8Allows the use of more powerful rituals.",
				"§8Increases your maximum effort.");

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

		// Poderes
		add("ordemparanormal.nex.power_points", "Power Points");
		add("ordemparanormal.power.owned", "Power acquired");
		add("ordemparanormal.power.active", "Active power");
		add("ordemparanormal.power.passive", "Passive power");

		addPower(OPPowers.TEST_POWER, "Test 1",
				"Test line 1",
				"Test line 2");

		addPower(OPPowers.TEST_POWER_2, "Test 2",
				"Test line 1",
				"Test line 2");

		addPower(OPPowers.TEST_POWER_3, "Test 3",
				"Test line 1",
				"Test line 2");

		addPower(OPPowers.TEST_POWER_4, "Test 4",
				"Test line 1",
				"Test line 2");

		addPower(OPPowers.TEST_POWER_5,"Test 5",
				"Test line 1",
				"Test line 2");

		addPower(OPPowers.TEST_POWER_6,"Test 6",
				"Test line 1",
				"Test line 2");

		addPower(OPPowers.TEST_POWER_7,"Test 7",
				"Test line 1",
				"Test line 2");

		addPower(OPPowers.TEST_POWER_8,"Test 8",
				"Test line 1",
				"Test line 2");

		// Maldições
		add(OPCurses.ATROZ.getTranslationKey(), "Atrocious");
		add(OPCurses.VELOZ.getTranslationKey(), "Nimble");
		add(OPCurses.DECADENTE.getTranslationKey(), "Decaying");
		add(OPCurses.AMALDICOAR_ARMA.getTranslationKey(), "Cursed");

		// Efeitos
		add(OPEffects.BLEED.get(), "Bleeding");
		add(OPEffects.LIFE_ABSORBED.get(), "Life Absorbed");
		add(OPEffects.SKIN_REINFORCED.get(), "Skin Reinforced");
		add(OPEffects.DECAY.get(), "Decay");

		// Atalhos de Teclado
		add("ordemparanormal.key_category", "Paranormal Order Mod");
		add("ordemparanormal.key.nex_screen", "Show Paranormal Attributes");
		for (int i = 1; i < 6; i++){
			add("ordemparanormal.key.power_" + i, "Use power slot " + i);
		}

		// Livros dentro do jogo
		add("ordemparanormal.mansion_books.record_495", "Blood brother Lairon came back from his expedition... He said that he found a portal in the supposed \"Order's\" plains. We recreated it and watched it closely. We will never listen to Lairon again. Damned bastard! At least we got resources from that.");
		add("ordemparanormal.mansion_books.record_001.page_1", "Day 1: \nJerome told me this place is safe for my habitation. The first guest told me something horrible... When I was returning from the forest, a fog appeared. It wasn't in my weather predictions... \nHe had a big bite on the leg, and didn't want to talk about it...");
		add("ordemparanormal.mansion_books.record_001.page_2", "Day 2: \nWHAT THE FUCK IS THAT?!");
		add("ordemparanormal.devil_church_books.blood_book.page_1", "Blood is the entity of emotion. It searches for intensity: pain, obsession, passion, love, hunger, hate - everything that involves feeling an extreme emotion pleases the entity of Blood.");
		add("ordemparanormal.devil_church_books.blood_book.page_2", "Everything begins with Blood. Blood is the flow that bathes the eternity of the Other Side.");

		// Outros
		add("ordemparanormal.nex.title", "Paranormal Exposure");
		add("ordemparanormal.nex.abbreviation", "PeX");
		add("ordemparanormal.health_points", "HP");
		add("ordemparanormal.effort_points", "EP");
		add("ordemparanormal.effort_points.full_name", "Effort Points");
	}

}
