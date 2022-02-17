package com.guga.ordemparanormal.core;

import com.guga.ordemparanormal.client.renderer.ZumbiSangueRenderer;
import com.guga.ordemparanormal.core.registry.OPEntities;
import com.guga.ordemparanormal.core.registry.OPItems;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod("ordemparanormal")
@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OrdemParanormal {
	public static final String MOD_ID = "ordemparanormal";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> {
		helper.putSubHelper(ForgeRegistries.ITEMS, new OPItems.Helper(helper));
	});
	
	// Abas do modo criativo
	public static final CreativeModeTab OP_ITENS = new CreativeModeTab(MOD_ID) {		
		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack makeIcon() {
			return new ItemStack(OPItems.GRIMORIO_SANGUE.get());
		}
	};
		
	public OrdemParanormal() {
		// Event Bus para registrar coisas do mod
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		REGISTRY_HELPER.register(bus);
		
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			bus.addListener(this::rendererSetup);
		});
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@OnlyIn(Dist.CLIENT)
	private void rendererSetup(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(OPEntities.ZUMBI_SANGUE.get(), ZumbiSangueRenderer::new);
	}
}
