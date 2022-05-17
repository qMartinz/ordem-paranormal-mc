package com.guga.ordemparanormal.core;

import com.guga.ordemparanormal.client.renderer.BestialRenderer;
import com.guga.ordemparanormal.client.renderer.NevoaRenderer;
import com.guga.ordemparanormal.client.renderer.VillagerCorpoRenderer;
import com.guga.ordemparanormal.client.renderer.ZumbiSangueRenderer;
import com.guga.ordemparanormal.common.network.SyncNex;
import com.guga.ordemparanormal.core.registry.OPEntities;
import com.guga.ordemparanormal.core.registry.OPItems;
import com.guga.ordemparanormal.core.registry.OPParticles;
import com.guga.ordemparanormal.datagen.client.ModItemModelProvider;
import com.guga.ordemparanormal.datagen.client.lang.ModEnUsProvider;
import com.guga.ordemparanormal.datagen.client.lang.ModPtBrProvider;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;

@Mod(OrdemParanormal.MOD_ID)
@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OrdemParanormal {
	public static final String MOD_ID = "ordemparanormal";

	public static SimpleChannel network;
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> helper.putSubHelper(ForgeRegistries.ITEMS, new OPItems.Helper(helper)));
	
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
		OPParticles.PARTICLE_TYPES.register(bus);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			bus.addListener(this::rendererSetup);
		});

		MinecraftForge.EVENT_BUS.register(this);

		bus.addListener(this::commonSetup);
		bus.addListener(this::dataSetup);
	}
	private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			network = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, "network"), () -> "1.0", s -> true, s -> true);
			network.registerMessage(0, SyncNex.class, SyncNex::encode, SyncNex::new, SyncNex::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
		});
	}
	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		// Geração de Data do Cliente
		if (event.includeClient()) {
			generator.addProvider(new ModItemModelProvider(generator, helper));
			generator.addProvider(new ModPtBrProvider(generator));
			generator.addProvider(new ModEnUsProvider(generator));
		}
	}
	@OnlyIn(Dist.CLIENT)
	private void rendererSetup(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(OPEntities.BESTIAL.get(), BestialRenderer::new);
		event.registerEntityRenderer(OPEntities.ZUMBI_SANGUE.get(), ZumbiSangueRenderer::new);
		event.registerEntityRenderer(OPEntities.NEVOA.get(), NevoaRenderer::new);
		event.registerEntityRenderer(OPEntities.VILLAGER_CORPO.get(), VillagerCorpoRenderer::new);
	}
}