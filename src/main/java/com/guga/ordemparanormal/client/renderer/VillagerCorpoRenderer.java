package com.guga.ordemparanormal.client.renderer;

import com.guga.ordemparanormal.client.model.VillagerCorpoModel;
import com.guga.ordemparanormal.common.entity.Corpses.VillagerCorpo;
import com.guga.ordemparanormal.core.OrdemParanormal;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class VillagerCorpoRenderer extends MobRenderer<VillagerCorpo, VillagerCorpoModel<VillagerCorpo>> {
	private static final ResourceLocation TEXTURE = OrdemParanormal.REGISTRY_HELPER.prefix("textures/entity/villager_corpo.png");

	// Renderizar modelo
		public VillagerCorpoRenderer(EntityRendererProvider.Context context) {
			super(context, new VillagerCorpoModel<>(context.bakeLayer(VillagerCorpoModel.LAYER_LOCATION)), 0.3F);
		}
		
	// Textura
		@Override
		public ResourceLocation getTextureLocation(VillagerCorpo entity) {
			return TEXTURE;
		}
}
