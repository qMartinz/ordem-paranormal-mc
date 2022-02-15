package com.guga.ordemparanormal.core.registry;

import java.util.function.Supplier;

import com.guga.ordemparanormal.core.OrdemParanormal;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OPItems {
	// DeferredRegister para registrar os itens
	public static final DeferredRegister<Item> ITENS = DeferredRegister.create(ForgeRegistries.ITEMS,
			OrdemParanormal.MOD_ID);

	// Itens
	public static final RegistryObject<Item> GRIMORIO_SANGUE = register("grimorio_sangue",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).tab(OrdemParanormal.OP_ITENS)));
	public static final RegistryObject<Item> GRIMORIO_ENERGIA = register("grimorio_energia",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).tab(OrdemParanormal.OP_ITENS)));

	// Função utilizada para registrar os itens
	private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
		return ITENS.register(name, item);
	}
}
