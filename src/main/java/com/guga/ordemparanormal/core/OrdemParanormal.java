package com.guga.ordemparanormal.core;

import com.guga.ordemparanormal.client.ClientHandler;
import com.guga.ordemparanormal.client.renderer.blockentity.MesaMaldicaoRenderer;
import com.guga.ordemparanormal.client.renderer.entity.*;
import com.guga.ordemparanormal.common.OPItemProperties;
import com.guga.ordemparanormal.core.network.ClientProxy;
import com.guga.ordemparanormal.core.network.IProxy;
import com.guga.ordemparanormal.core.network.Messages;
import com.guga.ordemparanormal.core.network.ServerProxy;
import com.guga.ordemparanormal.core.registry.*;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
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

		bus.addListener(this::setup);
		OPParticles.PARTICLE_TYPES.register(bus);
		OPStructures.STRUCTURE_TYPES.register(bus);
		OPEffects.register(bus);
		OPVillagers.register(bus);
		OPProcessors.STRUCTURE_PROCESSORS.register(bus);
		OPLootFunctions.LOOT_FUNCTIONS.register(bus);
		OPLootItemConditions.LOOT_CONDITIONS.register(bus);
		OPLootModifiers.register(bus);
		OPTriggers.init();
		OPCreativeTabs.init();

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(this::rendererSetup));
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(ClientHandler::onAddLayers));

		bus.addListener(this::clientSetup);
		MinecraftForge.EVENT_BUS.register(this);
	}
	@OnlyIn(Dist.CLIENT)
	public void rendererSetup(final EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(OPBlockEntities.BLOOD_CURSE_TABLE_BLOCK_ENTITY.get(), MesaMaldicaoRenderer::new);
		event.registerBlockEntityRenderer(OPBlockEntities.DEATH_CURSE_TABLE_BLOCK_ENTITY.get(), MesaMaldicaoRenderer::new);
		event.registerBlockEntityRenderer(OPBlockEntities.KNOWLEDGE_CURSE_TABLE_BLOCK_ENTITY.get(), MesaMaldicaoRenderer::new);
		event.registerBlockEntityRenderer(OPBlockEntities.ENERGY_CURSE_TABLE_BLOCK_ENTITY.get(), MesaMaldicaoRenderer::new);

		event.registerEntityRenderer(OPEntities.BESTIAL.get(), BestialRenderer::new);
		event.registerEntityRenderer(OPEntities.ZUMBI_SANGUE.get(), ZumbiSangueRenderer::new);
		event.registerEntityRenderer(OPEntities.ZUMBI_SECO.get(), ZumbiSecoRenderer::new);
		event.registerEntityRenderer(OPEntities.ZUMBI_ESPINHENTO.get(), ZumbiEspinhentoRenderer::new);
		event.registerEntityRenderer(OPEntities.ABERRACAO_CARNE.get(), AberracaoCarneRenderer::new);
		event.registerEntityRenderer(OPEntities.TITA_SANGUE.get(), TitaSangueRenderer::new);
		event.registerEntityRenderer(OPEntities.NEVOA.get(), NevoaRenderer::new);
		event.registerEntityRenderer(OPEntities.VILLAGER_CORPO.get(), VillagerCorpoRenderer::new);
		event.registerEntityRenderer(OPEntities.BIDENTE.get(), BidenteRenderer::new);
		event.registerEntityRenderer(OPEntities.RITUAL_PROJECTILE.get(), RitualProjectileRenderer::new);
		event.registerEntityRenderer(OPEntities.TRANSTORNADO.get(), IllagerOcultistaRenderers.TranstornadoRenderer::new);
		event.registerEntityRenderer(OPEntities.SADICO.get(), IllagerOcultistaRenderers.SadicoRenderer::new);
		event.registerEntityRenderer(OPEntities.PADRE_DIABO.get(), IllagerOcultistaRenderers.PadreDiaboRenderer::new);
	}
	public void setup(final FMLCommonSetupEvent event){
		Messages.register();
		OPCurses.setup();
		OPRituals.setup();
		OPPowers.setup();

		event.enqueueWork(() -> {
			OPVillagers.registerPois();
		});
	}
	public void clientSetup(final FMLClientSetupEvent event){
		event.enqueueWork(OPItemProperties::register);
	}
}