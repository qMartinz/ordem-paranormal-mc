package com.guga.ordemparanormal.client.renderer;

import com.guga.ordemparanormal.client.model.BestialModel;
import com.guga.ordemparanormal.common.entity.zumbissangue.Bestial;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class BestialRenderer extends MobRenderer<Bestial, BestialModel<Bestial>> {
	private static final ResourceLocation TEXTURE = OrdemParanormal.REGISTRY_HELPER.prefix("textures/entity/zumbi_bestial.png");
	
	// Renderizar modelo
	public BestialRenderer(EntityRendererProvider.Context context) {
		super(context, new BestialModel<>(context.bakeLayer(BestialModel.LAYER_LOCATION)), 1.0F);
	}
	
	// Textura
	@Override
	public ResourceLocation getTextureLocation(Bestial entity) {
		return TEXTURE;
	}
}
