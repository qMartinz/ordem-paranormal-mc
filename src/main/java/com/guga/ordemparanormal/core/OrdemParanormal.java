package com.guga.ordemparanormal.core;

import com.guga.ordemparanormal.client.Keybind;
import com.guga.ordemparanormal.client.Overlay;
import com.guga.ordemparanormal.client.renderer.*;
import com.guga.ordemparanormal.common.OPItemProperties;
import com.guga.ordemparanormal.core.network.ClientProxy;
import com.guga.ordemparanormal.core.network.IProxy;
import com.guga.ordemparanormal.core.network.Messages;
import com.guga.ordemparanormal.core.network.ServerProxy;
import com.guga.ordemparanormal.core.registry.*;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
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
	public static IProxy proxy = DistExecutor.runForDist(()-> ClientProxy::new, () -> ServerProxy::new);
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper ->
		helper.putSubHelper(ForgeRegistries.ITEMS, new OPItems.Helper(helper)));
	public static final CreativeModeTab OP_TAB = new CreativeModeTab(MOD_ID) {
		@Override
		public ItemStack makeIcon() {
			return OPBlocks.ALTAR_TRANSCENDER.get().asItem().getDefaultInstance();
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

		bus.addListener(this::apiSetup);
		REGISTRY_HELPER.register(bus);

		OPParticles.PARTICLE_TYPES.register(bus);
		OPStructures.STRUCTURE_FEATURES.register(bus);
		OPEffects.register(bus);
		OPPois.register(bus);
		OPProfessions.register(bus);
		OPProcessors.STRUCTURE_PROCESSORS.register(bus);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(this::rendererSetup));

		bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		MinecraftForge.EVENT_BUS.register(this);
	}
	public void apiSetup(final FMLCommonSetupEvent event){
		OPCurses.setup();
		OPRituals.setup();
		OPPowers.setup();
	}
	@OnlyIn(Dist.CLIENT)
	public void rendererSetup(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(OPEntities.BESTIAL.get(), BestialRenderer::new);
		event.registerEntityRenderer(OPEntities.ZUMBI_SANGUE.get(), ZumbiSangueRenderer::new);
		event.registerEntityRenderer(OPEntities.ZUMBI_SECO.get(), ZumbiSecoRenderer::new);
		event.registerEntityRenderer(OPEntities.ZUMBI_ESPINHENTO.get(), ZumbiEspinhentoRenderer::new);
		event.registerEntityRenderer(OPEntities.NEVOA.get(), NevoaRenderer::new);
		event.registerEntityRenderer(OPEntities.VILLAGER_CORPO.get(), VillagerCorpoRenderer::new);
	}
	public void setup(final FMLCommonSetupEvent event){
		Messages.register();

		event.enqueueWork(() -> {
			OPPois.registerPOIs();
		});
	}
	public void clientSetup(final FMLClientSetupEvent event){
		MinecraftForge.EVENT_BUS.register(new Overlay());
		MinecraftForge.EVENT_BUS.register(new Keybind());

		Overlay.registerOverlays();

		ItemBlockRenderTypes.setRenderLayer(OPBlocks.LUZ_BLOCK.get(), RenderType.translucent());

		event.enqueueWork(OPItemProperties::register);
	}
}