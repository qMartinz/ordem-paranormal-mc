package com.guga.ordemparanormal.client.events;

import com.guga.ordemparanormal.client.model.AberradoModel;
import com.guga.ordemparanormal.client.model.ZumbiSangueModel;
import com.guga.ordemparanormal.client.renderer.AberradoRenderer;
import com.guga.ordemparanormal.client.renderer.ZumbiSangueRenderer;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPEntities;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {
	public ClientModEvents() {}
	
	// Registrar modelo
	@SubscribeEvent
	public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ZumbiSangueModel.LAYER_LOCATION, ZumbiSangueModel::createBodyLayer);
		event.registerLayerDefinition(AberradoModel.LAYER_LOCATION, AberradoModel::createBodyLayer);
	}
	
	// Registrar renderizador
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(OPEntities.ZUMBI_SANGUE.get(), ZumbiSangueRenderer::new);
		event.registerEntityRenderer(OPEntities.ABERRADO.get(), AberradoRenderer::new);
	}
}
