package com.guga.ordemparanormal.core;

import com.guga.ordemparanormal.core.registry.OPItems;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("ordemparanormal")
public class OrdemParanormal {
	public static final String MOD_ID = "ordemparanormal";
	
	// Abas do modo criativo
	public static final CreativeModeTab OP_ITENS = new CreativeModeTab(MOD_ID) {
		
		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack makeIcon() {
			return new ItemStack(OPItems.GRIMORIO_SANGUE.get());
		}
	};
	
	// Event Bus para registrar coisas do mod
	public OrdemParanormal() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		// Registrar conteúdo do mod
		OPItems.ITENS.register(bus);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
}
