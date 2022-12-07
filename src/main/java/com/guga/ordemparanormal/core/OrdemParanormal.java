package com.guga.ordemparanormal.core;

import com.guga.ordemparanormal.client.ClientEvents;
import com.guga.ordemparanormal.client.Keybind;
import com.guga.ordemparanormal.client.Overlay;
import com.guga.ordemparanormal.client.renderer.*;
import com.guga.ordemparanormal.client.screen.BloodTableScreen;
import com.guga.ordemparanormal.client.screen.DeathTableScreen;
import com.guga.ordemparanormal.client.screen.EnergyTableScreen;
import com.guga.ordemparanormal.client.screen.KnowledgeTableScreen;
import com.guga.ordemparanormal.common.OPItemProperties;
import com.guga.ordemparanormal.core.network.ClientProxy;
import com.guga.ordemparanormal.core.network.IProxy;
import com.guga.ordemparanormal.core.network.Messages;
import com.guga.ordemparanormal.core.network.ServerProxy;
import com.guga.ordemparanormal.core.registry.*;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
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
	public OrdemParanormal() {
		// Event Bus para registrar coisas do mod
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		REGISTRY_HELPER.register(bus);

		bus.addListener(this::apiSetup);
		OPParticles.PARTICLE_TYPES.register(bus);
		OPStructures.STRUCTURE_FEATURES.register(bus);
		OPEffects.register(bus);
		OPPois.register(bus);
		OPProfessions.register(bus);
		OPProcessors.STRUCTURE_PROCESSORS.register(bus);
		OPLootFunctions.LOOT_FUNCTIONS.register(bus);
		OPLootItemConditions.LOOT_CONDITIONS.register(bus);
		OPLootModifiers.LOOT_MODIFIERS.register(bus);
		OPTriggers.init();
		OPCreativeTabs.init();
		OPMenuTypes.MENUS.register(bus);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(this::rendererSetup));
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(ClientEvents::onAddLayers));

		bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		MinecraftForge.EVENT_BUS.register(this);
	}
	@OnlyIn(Dist.CLIENT)
	public void rendererSetup(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(OPEntities.BESTIAL.get(), BestialRenderer::new);
		event.registerEntityRenderer(OPEntities.ZUMBI_SANGUE.get(), ZumbiSangueRenderer::new);
		event.registerEntityRenderer(OPEntities.ZUMBI_SECO.get(), ZumbiSecoRenderer::new);
		event.registerEntityRenderer(OPEntities.ZUMBI_ESPINHENTO.get(), ZumbiEspinhentoRenderer::new);
		event.registerEntityRenderer(OPEntities.ABERRACAO_CARNE.get(), AberracaoCarneRenderer::new);
		event.registerEntityRenderer(OPEntities.NEVOA.get(), NevoaRenderer::new);
		event.registerEntityRenderer(OPEntities.VILLAGER_CORPO.get(), VillagerCorpoRenderer::new);
		event.registerEntityRenderer(OPEntities.BIDENTE.get(), BidenteRenderer::new);
	}
	public void apiSetup(final FMLCommonSetupEvent event){
		OPCurses.setup();
		OPRituals.setup();
		OPPowers.setup();
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

		MenuScreens.register(OPMenuTypes.BLOOD_TABLE_MENU.get(), BloodTableScreen::new);
		MenuScreens.register(OPMenuTypes.ENERGY_TABLE_MENU.get(), EnergyTableScreen::new);
		MenuScreens.register(OPMenuTypes.DEATH_TABLE_MENU.get(), DeathTableScreen::new);
		MenuScreens.register(OPMenuTypes.KNOWLEDGE_TABLE_MENU.get(), KnowledgeTableScreen::new);

		event.enqueueWork(OPItemProperties::register);
	}
}