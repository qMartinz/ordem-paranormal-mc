package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.teamabnormals.blueprint.common.item.BlueprintSpawnEggItem;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class OPItems {
	private static final Helper HELPER = OrdemParanormal.REGISTRY_HELPER.getItemSubHelper();

	// Registrar itens
	public static final RegistryObject<Item> GRIMORIO_SANGUE = HELPER.createGSangue();
	public static final RegistryObject<Item> GRIMORIO_ENERGIA = HELPER.createGEnergia();
	public static final RegistryObject<Item> ORGAO = HELPER.createOrgao();
	public static final RegistryObject<Item> CINZAS = HELPER.createCinzas();
	
	// Ovos geradores
	public static final RegistryObject<BlueprintSpawnEggItem> ZUMBI_SANGUE_OVO = HELPER
			.createSpawnEggItem("zumbi_sangue", OPEntities.ZUMBI_SANGUE::get, 3670016, 7737617);
	public static final RegistryObject<BlueprintSpawnEggItem> BESTIAL_OVO = HELPER.createSpawnEggItem("zumbi_bestial",
			OPEntities.BESTIAL::get, 7999247, 13875596);
	public static final RegistryObject<BlueprintSpawnEggItem> ABERRADO_OVO = HELPER.createSpawnEggItem("aberrado",
			OPEntities.ABERRADO::get, 1126172, 8920604);

	public static class Helper extends ItemSubRegistryHelper {
		// Ajudante para registrar itens
		public Helper(RegistryHelper parent) {
			super(parent, parent.getItemSubHelper().getDeferredRegister());
		}

		// Itens
		private RegistryObject<Item> createGSangue() {
			return this.deferredRegister.register("grimorio_sangue", () -> new Item(
					new Item.Properties().stacksTo(1).rarity(Rarity.RARE).tab(OrdemParanormal.OP_ITENS)));
		}

		private RegistryObject<Item> createGEnergia() {
			return this.deferredRegister.register("grimorio_energia", () -> new Item(
					new Item.Properties().stacksTo(1).rarity(Rarity.RARE).tab(OrdemParanormal.OP_ITENS)));
		}

		private RegistryObject<Item> createOrgao() {
			return this.deferredRegister
					.register("orgao",
							() -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON)
									.tab(OrdemParanormal.OP_ITENS)
									.food(new FoodProperties.Builder().nutrition(1).saturationMod(0.2F)
											.effect(() -> new MobEffectInstance(MobEffects.HUNGER, 800), 1F).build())));
		}
		
		private RegistryObject<Item> createCinzas() {
			return this.deferredRegister
					.register("cinzas",
							() -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON)
									.tab(OrdemParanormal.OP_ITENS).fireResistant()));
		}
	}
}
