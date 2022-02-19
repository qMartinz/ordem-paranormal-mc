package com.guga.ordemparanormal.client.renderer;

import com.guga.ordemparanormal.client.model.AberradoModel;
import com.guga.ordemparanormal.common.entity.Aberrado;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class AberradoRenderer extends MobRenderer<Aberrado, AberradoModel<Aberrado>> {
	private static final ResourceLocation TEXTURE = OrdemParanormal.REGISTRY_HELPER.prefix("textures/entity/aberrado.png");
	
	// Renderizar modelo
	public AberradoRenderer(EntityRendererProvider.Context context) {
		super(context, new AberradoModel<>(context.bakeLayer(AberradoModel.LAYER_LOCATION)), 1.0F);
	}
	
	// Textura
	@Override
	public ResourceLocation getTextureLocation(Aberrado entity) {
		return TEXTURE;
	}
}
