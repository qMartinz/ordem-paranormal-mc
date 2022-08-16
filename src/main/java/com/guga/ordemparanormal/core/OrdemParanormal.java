package com.guga.ordemparanormal.core;

import com.guga.ordemparanormal.client.Keybind;
import com.guga.ordemparanormal.client.Overlay;
import com.guga.ordemparanormal.client.renderer.*;
import com.guga.ordemparanormal.core.network.Messages;
import com.guga.ordemparanormal.core.registry.*;
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
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(OrdemParanormal.MOD_ID)
@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OrdemParanormal {
	public static final String MOD_ID = "ordemparanormal";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper ->
			helper.putSubHelper(ForgeRegistries.ITEMS, new OPItems.Helper(helper)));
	public static final CreativeModeTab OP_TAB = new CreativeModeTab(MOD_ID) {
		@Override
		public ItemStack makeIcon() {
			return OPItems.GRIMORIO_SANGUE.get().getDefaultInstance();
		}
	};
	public static final CreativeModeTab RITUALS_TAB = new CreativeModeTab(MOD_ID + ".rituals") {
		@Override
		public ItemStack makeIcon() {
			return OPItems.RITUAL_DESCARNAR.get().getDefaultInstance();
		}
	};
	public static final CreativeModeTab MOBS_TAB = new CreativeModeTab(MOD_ID + ".mobs") {
		@Override
		public ItemStack makeIcon() {
			return OPItems.ZUMBI_SANGUE_OVO.get().getDefaultInstance();
		}
	};
	public OrdemParanormal() {
		// Event Bus para registrar coisas do mod
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		Messages.register();
		REGISTRY_HELPER.register(bus);
		OPParticles.PARTICLE_TYPES.register(bus);
		OPStructures.register(bus);
		OPEffects.register(bus);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(this::rendererSetup));

		MinecraftForge.EVENT_BUS.register(this);

		bus.addListener(this::clientSetup);
		bus.addListener(this::commonSetup);
	}
	public void commonSetup(final FMLCommonSetupEvent event){
		OPAPI.setup();
	}
	@OnlyIn(Dist.CLIENT)
	private void rendererSetup(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(OPEntities.BESTIAL.get(), BestialRenderer::new);
		event.registerEntityRenderer(OPEntities.ZUMBI_SANGUE.get(), ZumbiSangueRenderer::new);
		event.registerEntityRenderer(OPEntities.ZUMBI_SECO.get(), ZumbiSecoRenderer::new);
		event.registerEntityRenderer(OPEntities.ZUMBI_ESPINHENTO.get(), ZumbiEspinhentoRenderer::new);
		event.registerEntityRenderer(OPEntities.NEVOA.get(), NevoaRenderer::new);
		event.registerEntityRenderer(OPEntities.VILLAGER_CORPO.get(), VillagerCorpoRenderer::new);
	}
	private void clientSetup(final FMLClientSetupEvent event){
		MinecraftForge.EVENT_BUS.register(new Overlay());
		MinecraftForge.EVENT_BUS.register(new Keybind());

		Overlay.registerOverlays();
	}
}