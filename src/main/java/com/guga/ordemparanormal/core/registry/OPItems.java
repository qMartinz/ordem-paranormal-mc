package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.core.OrdemParanormal;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;

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
	
	public static class Helper extends ItemSubRegistryHelper {
		// Ajudante para registrar itens
		public Helper(RegistryHelper parent) {
			super(parent, parent.getItemSubHelper().getDeferredRegister());
		}
		
		// Itens
		private RegistryObject<Item> createGSangue() {
			return this.deferredRegister.register("grimorio_sangue", () -> new Item(new 
					Item.Properties().stacksTo(1).rarity(Rarity.RARE).tab(OrdemParanormal.OP_ITENS))); }
		private RegistryObject<Item> createGEnergia() {
			return this.deferredRegister.register("grimorio_energia", () -> new Item(new 
					Item.Properties().stacksTo(1).rarity(Rarity.RARE).tab(OrdemParanormal.OP_ITENS))); }
	}	
}
