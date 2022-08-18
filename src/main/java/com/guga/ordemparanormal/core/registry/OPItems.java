package com.guga.ordemparanormal.core.registry;

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
	private static final Helper HELPER = OrdemParanormal.REGISTRY_HELPER.getItemSubHelper();

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
		public RegistryObject<ForgeSpawnEggItem> createSpawnEggItem(String entityName, Supplier<EntityType<? extends Mob>> supplier, int primaryColor, int secondaryColor){
			return this.deferredRegister.register(entityName + "_spawn_egg", () -> new ForgeSpawnEggItem(supplier, primaryColor, secondaryColor, new Item.Properties().tab(OrdemParanormal.MOBS_TAB)));
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
		private RegistryObject<RitualItem> createRDescarnar(){
			return this.deferredRegister.register("ritual_descarnar", () -> new RitualItem(OPAPI.DESCARNAR));
		}
		private RegistryObject<RitualItem> createRDecadencia(){
			return this.deferredRegister.register("ritual_decadencia", () -> new RitualItem(OPAPI.DECADENCIA));
		}
		private RegistryObject<RitualItem> createRCicatrizacao(){
			return this.deferredRegister.register("ritual_cicatrizacao", () -> new RitualItem(OPAPI.CICATRIZACAO));
		}
		private RegistryObject<RitualItem> createRConsumirManancial(){
			return this.deferredRegister.register("ritual_consumir_manancial", () -> new RitualItem(OPAPI.CONSUMIR_MANANCIAL));
		}
		private RegistryObject<RitualItem> createRArmaduraSangue(){
			return this.deferredRegister.register("ritual_armadura_sangue", () -> new RitualItem(OPAPI.ARMADURA_SANGUE));
		}
	}	
}
