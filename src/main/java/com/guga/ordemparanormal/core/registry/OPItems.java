package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.common.item.*;
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
	public static final RegistryObject<Item> ORGAO = HELPER.createOrgao();
	public static final RegistryObject<Item> CINZAS = HELPER.createCinzas();
	public static final RegistryObject<Item> PERGAMINHO_ANTIGO = HELPER.createPergaminhoAntigo();
	// public static final RegistryObject<Item> GIZ = HELPER.createGiz();
	public static final RegistryObject<Bidente> BIDENTE = HELPER.createBidente();
	public static final RegistryObject<PerolaSangue> PEROLA_SANGUE = HELPER.createPerolaSangue();

	// Itens de rituais
	public static final RegistryObject<RitualItem> RITUAL_DESCARNAR = HELPER.createRItem("livro_amaldicoado",
			OPRituals.DESCARNAR);
	public static final RegistryObject<RitualItem> RITUAL_DECADENCIA = HELPER.createRItem("cranio_simbolo",
			OPRituals.DECADENCIA);
	public static final RegistryObject<RitualItem> RITUAL_CICATRIZACAO = HELPER.createRItem("cristal_espiral",
			OPRituals.CICATRIZACAO);
	public static final RegistryObject<RitualItem> RITUAL_CONSUMIR_MANANCIAL = HELPER.createRItem("papel_amaldicoado",
			OPRituals.CONSUMIR_MANANCIAL);
	public static final RegistryObject<RitualItem> RITUAL_ARMADURA_SANGUE = HELPER.createRItem("capacete_enferrujado",
			OPRituals.ARMADURA_SANGUE);
	public static final RegistryObject<RitualItem> RITUAL_ARMA_ATROZ = HELPER.createRItem("anel_sangrento_prata",
			OPRituals.ARMA_ATROZ);
	public static final RegistryObject<RitualItem> RITUAL_ARMA_VELOZ = HELPER.createRItem("laco_amarrado",
			OPRituals.ARMA_VELOZ);
	public static final RegistryObject<RitualItem> RITUAL_AMALDICOAR_ARMA = HELPER.createRItem(
			"anel_dourado_enferrujado",
			OPRituals.AMALDICOAR_ARMA);
	public static final RegistryObject<RitualItem> RITUAL_HEMOFAGIA = HELPER.createRItem("colar_sangue",
			OPRituals.HEMOFAGIA);
	public static final RegistryObject<RitualItem> RITUAL_APRIMORAMENTO_FISICO = HELPER.createRItem(
			"pulseira_espinhenta",
			OPRituals.APRIMORAMENTO_FISICO);
	public static final RegistryObject<RitualItem> RITUAL_VELOCIDADE_MORTAL = HELPER.createRItem("poema_queimado",
			OPRituals.VELOCIDADE_MORTAL);
	public static final RegistryObject<RitualItem> RITUAL_TRANSFERENCIA_VITAL = HELPER.createRItem(
			"seringa_enferrujada",
			OPRituals.TRANSFERENCIA_VITAL);
	public static final RegistryObject<RitualItem> RITUAL_SALTO_FANTASMA = HELPER.createRItem("relogio_energizado",
			OPRituals.SALTO_FANTASMA);
	public static final RegistryObject<RitualItem> RITUAL_TELEPORTE = HELPER.createRItem("foto_familiar",
			OPRituals.TELEPORTE);
	public static final RegistryObject<RitualItem> RITUAL_ESPIRAIS_DA_PERDICAO = HELPER.createRItem("espelho_marcado",
			OPRituals.ESPIRAIS_DA_PERDICAO);
	public static final RegistryObject<RitualItem> RITUAL_PERTUBACAO = HELPER.createRItem("cruz_curvada",
			OPRituals.PERTURBACAO);
	public static final RegistryObject<RitualItem> RITUAL_INEXISTIR = HELPER.createRItem("livro_indescritivel",
			OPRituals.INEXISTIR);
	public static final RegistryObject<RitualItem> RITUAL_LUZ = HELPER.createRItem("fragmento_brilhante",
			OPRituals.LUZ);
	public static final RegistryObject<RitualItem> RITUAL_PURGATORIO = HELPER.createRItem("purgatorio",
			OPRituals.PURGATORIO);
	//public static final RegistryObject<RitualItem> RITUAL_TRANSFIGURAR_TERRA = HELPER.createRItem("tranfigurar_terra",
			//OPRituals.TRANSFIGURAR_TERRA);
	//public static final RegistryObject<RitualItem> RITUAL_CINERARIA = HELPER.createRItem("cineraria",
			//OPRituals.CINERARIA);
	//public static final RegistryObject<RitualItem> RITUAL_REJEITAR_NEVOA = HELPER.createRItem("rejeitar_nevoa",
			//OPRituals.REJEITAR_NEVOA);
	//public static final RegistryObject<RitualItem> RITUAL_ZERAR_ENTROPIA = HELPER.createRItem("zerar_entropia",
			//OPRituals.ZERAR_ENTROPIA);
	//public static final RegistryObject<RitualItem> RITUAL_ALTERAR_MEMORIA =
	        //HELPER.createRItem("alterar_memoria", OPRituals.ALTERAR_MEMORIA);

	// Componentes ritualisticos
	public static final RegistryObject<RitualComponent> COMPONENTE_SANGUE = HELPER
			.createComponenteRitualistico(ParanormalElement.SANGUE);
	public static final RegistryObject<RitualComponent> COMPONENTE_ENERGIA = HELPER
			.createComponenteRitualistico(ParanormalElement.ENERGIA);
	public static final RegistryObject<RitualComponent> COMPONENTE_MORTE = HELPER
			.createComponenteRitualistico(ParanormalElement.MORTE);
	public static final RegistryObject<RitualComponent> COMPONENTE_CONHECIMENTO = HELPER
			.createComponenteRitualistico(ParanormalElement.CONHECIMENTO);
	public static final RegistryObject<Item> COMPONENTE_VAZIO = HELPER
			.createItem("componente_vazio",
					() -> new Item(new Item.Properties().stacksTo(16).tab(OPCreativeTabs.RITUALS_TAB)));

	// Ovos geradores
	public static final RegistryObject<ForgeSpawnEggItem> ZUMBI_SANGUE_OVO = HELPER.createSpawnEggItem(
			"zumbi_sangue", OPEntities.ZUMBI_SANGUE::get, 3670016, 7999247);
	public static final RegistryObject<ForgeSpawnEggItem> BESTIAL_OVO = HELPER.createSpawnEggItem(
			"zumbi_bestial", OPEntities.BESTIAL::get, 7999247, 13875596);
	public static final RegistryObject<ForgeSpawnEggItem> ZUMBI_SECO_OVO = HELPER.createSpawnEggItem(
			"zumbi_seco", OPEntities.ZUMBI_SECO::get, 7999247, 2428687);
	public static final RegistryObject<ForgeSpawnEggItem> ZUMBI_ESPINHENTO_OVO = HELPER.createSpawnEggItem(
			"zumbi_espinhento", OPEntities.ZUMBI_ESPINHENTO::get, 7999247, 11493703);
	public static final RegistryObject<ForgeSpawnEggItem> ABERRACAO_CARNE_OVO = HELPER.createSpawnEggItem(
			"aberracao_carne", OPEntities.ABERRACAO_CARNE::get, 0x8E392F, 0xD01431);
	public static final RegistryObject<ForgeSpawnEggItem> TITA_SANGUE_OVO = HELPER.createSpawnEggItem(
			"tita_sangue", OPEntities.TITA_SANGUE::get, 0x59100D, 0xD33F2E);
	public static final RegistryObject<ForgeSpawnEggItem> TRANSTORNADO_OVO = HELPER.createSpawnEggItem(
			"transtornado", OPEntities.TRANSTORNADO::get, 0x78818f, 0x6e3944);
	public static final RegistryObject<ForgeSpawnEggItem> PADRE_DIABO_OVO = HELPER.createSpawnEggItem(
			"padre_diabo", OPEntities.PADRE_DIABO::get, 0x78818f, 0xb31515);
	public static final RegistryObject<ForgeSpawnEggItem> SADICO_OVO = HELPER.createSpawnEggItem(
			"sadico", OPEntities.SADICO::get, 0x78818f, 0x5c1313);

	public static class Helper extends ItemSubRegistryHelper {
		// Ajudante para registrar itens
		@Override
		public RegistryObject<ForgeSpawnEggItem> createSpawnEggItem(String entityName,
				Supplier<EntityType<? extends Mob>> supplier, int primaryColor, int secondaryColor) {
			return this.deferredRegister.register(entityName + "_spawn_egg", () -> new ForgeSpawnEggItem(supplier,
					primaryColor, secondaryColor, new Item.Properties().tab(OPCreativeTabs.MOBS_TAB)));
		}

		public Helper(RegistryHelper parent) {
			super(parent, parent.getItemSubHelper().getDeferredRegister());
		}

		// Itens
		private RegistryObject<Item> createOrgao() {
			return this.deferredRegister
					.register("orgao",
							() -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON)
									.tab(OPCreativeTabs.OP_TAB)
									.food(new FoodProperties.Builder().nutrition(1).saturationMod(0.2F)
											.effect(() -> new MobEffectInstance(MobEffects.HUNGER, 800), 1F).build())));
		}

		private RegistryObject<Item> createCinzas() {
			return this.deferredRegister
					.register("cinzas",
							() -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON)
									.tab(OPCreativeTabs.OP_TAB).fireResistant()));
		}

		private RegistryObject<Item> createPergaminhoAntigo() {
			return this.deferredRegister
					.register("pergaminho_antigo",
							PergaminhoAntigo::new);
		}

		private RegistryObject<Item> createGiz() {
			return this.deferredRegister
					.register("giz",
							Giz::new);
		}

		private RegistryObject<RitualItem> createRItem(String id, AbstractRitual ritual) {
			return this.deferredRegister.register(id, () -> new RitualItem(ritual));
		}

		private RegistryObject<RitualComponent> createComponenteRitualistico(ParanormalElement elemento) {
			return this.deferredRegister.register("componente_" + elemento.getSerializedName(),
					() -> new RitualComponent(elemento));
		}

		private RegistryObject<Bidente> createBidente() {
			return this.deferredRegister
					.register("bidente",
							() -> new Bidente(new Item.Properties().durability(250)
									.tab(OPCreativeTabs.OP_TAB).rarity(Rarity.RARE)));
		}

		private RegistryObject<PerolaSangue> createPerolaSangue(){
			return this.deferredRegister
					.register("perola_sangue", () -> new PerolaSangue(new Item.Properties()
							.tab(OPCreativeTabs.OP_TAB).stacksTo(8).rarity(Rarity.RARE)));
		}
	}
}
