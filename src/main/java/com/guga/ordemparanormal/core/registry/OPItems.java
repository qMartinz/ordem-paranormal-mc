package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.common.item.RitualComponent;
import com.guga.ordemparanormal.common.item.RitualItem;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class OPItems {
	public static final Helper HELPER = OrdemParanormal.REGISTRY_HELPER.getItemSubHelper();

	// Registrar itens
	public static final RegistryObject<Item> GRIMORIO_SANGUE = HELPER.createGSangue();
	public static final RegistryObject<Item> GRIMORIO_ENERGIA = HELPER.createGEnergia();
	public static final RegistryObject<Item> ORGAO = HELPER.createOrgao();
	public static final RegistryObject<Item> CINZAS = HELPER.createCinzas();

	// Itens de rituais
	public static final RegistryObject<RitualItem> RITUAL_DESCARNAR = HELPER.createRDescarnar();
	public static final RegistryObject<RitualItem> RITUAL_DECADENCIA = HELPER.createRDecadencia();
	public static final RegistryObject<RitualItem> RITUAL_CICATRIZACAO = HELPER.createRCicatrizacao();
	public static final RegistryObject<RitualItem> RITUAL_CONSUMIR_MANANCIAL = HELPER.createRConsumirManancial();
	public static final RegistryObject<RitualItem> RITUAL_ARMADURA_SANGUE = HELPER.createRArmaduraSangue();
	public static final RegistryObject<RitualItem> RITUAL_ARMA_ATROZ = HELPER.createRArmaAtroz();
	public static final RegistryObject<RitualItem> RITUAL_ARMA_VELOZ = HELPER.createRArmaVeloz();
	public static final RegistryObject<RitualItem> RITUAL_AMALDICOAR_ARMA = HELPER.createRAmaldicoarArma();
	public static final RegistryObject<RitualItem> RITUAL_HEMOFAGIA = HELPER.createRHemofagia();
	public static final RegistryObject<RitualItem> RITUAL_APRIMORAMENTO_FISICO = HELPER.createRAprimoramentoFisico();
	public static final RegistryObject<RitualItem> RITUAL_VELOCIDADE_MORTAL = HELPER.createRVelocidadeMortal();
	public static final RegistryObject<RitualItem> RITUAL_LAMINA_MEDO = HELPER.createRLaminaMedo();

	// Componentes ritualisticos
	public static final RegistryObject<RitualComponent> COMPONENTE_SANGUE = HELPER
			.createComponenteRitualistico(ParanormalElement.SANGUE);
	public static final RegistryObject<RitualComponent> COMPONENTE_ENERGIA = HELPER
			.createComponenteRitualistico(ParanormalElement.ENERGIA);
	public static final RegistryObject<RitualComponent> COMPONENTE_MORTE = HELPER
			.createComponenteRitualistico(ParanormalElement.MORTE);
	public static final RegistryObject<RitualComponent> COMPONENTE_CONHECIMENTO = HELPER
			.createComponenteRitualistico(ParanormalElement.CONHECIMENTO);
	public static final RegistryObject<RitualComponent> COMPONENTE_VAZIO = HELPER
			.createComponenteRitualistico(ParanormalElement.NONE);

	// Ovos geradores
	public static final RegistryObject<ForgeSpawnEggItem> ZUMBI_SANGUE_OVO = HELPER.createSpawnEggItem(
			"zumbi_sangue", OPEntities.ZUMBI_SANGUE::get, 3670016, 7999247);
	public static final RegistryObject<ForgeSpawnEggItem> BESTIAL_OVO = HELPER.createSpawnEggItem(
			"zumbi_bestial", OPEntities.BESTIAL::get, 7999247, 13875596);
	public static final RegistryObject<ForgeSpawnEggItem> ZUMBI_SECO_OVO = HELPER.createSpawnEggItem(
			"zumbi_seco", OPEntities.ZUMBI_SECO::get, 7999247, 2428687);

	public static final RegistryObject<ForgeSpawnEggItem> ZUMBI_ESPINHENTO_OVO = HELPER.createSpawnEggItem(
			"zumbi_espinhento", OPEntities.ZUMBI_ESPINHENTO::get, 7999247, 11493703);

	public static class Helper extends ItemSubRegistryHelper {
		// Ajudante para registrar itens

		@Override
		public RegistryObject<ForgeSpawnEggItem> createSpawnEggItem(String entityName,
				Supplier<EntityType<? extends Mob>> supplier, int primaryColor, int secondaryColor) {
			return this.deferredRegister.register(entityName + "_spawn_egg", () -> new ForgeSpawnEggItem(supplier,
					primaryColor, secondaryColor, new Item.Properties().tab(OrdemParanormal.MOBS_TAB)));
		}

		public Helper(RegistryHelper parent) {
			super(parent, parent.getItemSubHelper().getDeferredRegister());
		}

		// Itens
		private RegistryObject<Item> createGSangue() {
			return this.deferredRegister.register("grimorio_sangue", () -> new Item(
					new Item.Properties().stacksTo(1).rarity(Rarity.RARE).tab(OrdemParanormal.OP_TAB)));
		}

		private RegistryObject<Item> createGEnergia() {
			return this.deferredRegister.register("grimorio_energia", () -> new Item(
					new Item.Properties().stacksTo(1).rarity(Rarity.RARE).tab(OrdemParanormal.OP_TAB)));
		}

		private RegistryObject<Item> createOrgao() {
			return this.deferredRegister
					.register("orgao",
							() -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON)
									.tab(OrdemParanormal.OP_TAB)
									.food(new FoodProperties.Builder().nutrition(1).saturationMod(0.2F)
											.effect(() -> new MobEffectInstance(MobEffects.HUNGER, 800), 1F).build())));
		}

		private RegistryObject<Item> createCinzas() {
			return this.deferredRegister
					.register("cinzas",
							() -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON)
									.tab(OrdemParanormal.OP_TAB).fireResistant()));
		}

		private RegistryObject<RitualItem> createRDescarnar() {
			return this.deferredRegister.register("livro_amaldicoado", () -> new RitualItem(OPRituals.DESCARNAR));
		}

		private RegistryObject<RitualItem> createRDecadencia() {
			return this.deferredRegister.register("cranio_simbolo", () -> new RitualItem(OPRituals.DECADENCIA));
		}

		private RegistryObject<RitualItem> createRCicatrizacao() {
			return this.deferredRegister.register("cristal_espiral", () -> new RitualItem(OPRituals.CICATRIZACAO));
		}

		private RegistryObject<RitualItem> createRConsumirManancial() {
			return this.deferredRegister.register("papel_amaldicoado",
					() -> new RitualItem(OPRituals.CONSUMIR_MANANCIAL));
		}

		private RegistryObject<RitualItem> createRArmaduraSangue() {
			return this.deferredRegister.register("capacete_enferrujado",
					() -> new RitualItem(OPRituals.ARMADURA_SANGUE));
		}

		private RegistryObject<RitualItem> createRArmaAtroz() {
			return this.deferredRegister.register("ritual_arma_atroz", () -> new RitualItem(OPRituals.ARMA_ATROZ));
		}

		private RegistryObject<RitualItem> createRArmaVeloz() {
			return this.deferredRegister.register("ritual_arma_veloz", () -> new RitualItem(OPRituals.ARMA_VELOZ));
		}

		private RegistryObject<RitualItem> createRAmaldicoarArma() {
			return this.deferredRegister.register("ritual_amaldicoar_arma",
					() -> new RitualItem(OPRituals.AMALDICOAR_ARMA));
		}

		private RegistryObject<RitualItem> createRHemofagia() {
			return this.deferredRegister.register("ritual_hemofagia", () -> new RitualItem(OPRituals.HEMOFAGIA));
		}

		private RegistryObject<RitualItem> createRAprimoramentoFisico() {
			return this.deferredRegister.register("ritual_aprimoramento_fisico",
					() -> new RitualItem(OPRituals.APRIMORAMENTO_FISICO));
		}

		private RegistryObject<RitualItem> createRVelocidadeMortal() {
			return this.deferredRegister.register("ritual_velocidade_mortal",
					() -> new RitualItem(OPRituals.VELOCIDADE_MORTAL));
		}

		private RegistryObject<RitualItem> createRLaminaMedo() {
			return this.deferredRegister.register("ritual_lamina_medo",
					() -> new RitualItem(OPRituals.LAMINA_MEDO));
		}

		private RegistryObject<RitualComponent> createComponenteRitualistico(ParanormalElement elemento) {
			return this.deferredRegister.register("componente_" + elemento.getSerializedName(),
					() -> new RitualComponent(elemento));
		}
	}
}
