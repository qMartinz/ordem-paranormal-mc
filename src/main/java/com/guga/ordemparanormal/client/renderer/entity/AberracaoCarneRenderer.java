package com.guga.ordemparanormal.client.renderer.entity;

import com.guga.ordemparanormal.client.model.AberracaoCarneModel;
import com.guga.ordemparanormal.common.entity.AberracaoCarne;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class AberracaoCarneRenderer extends MobRenderer<AberracaoCarne, AberracaoCarneModel<AberracaoCarne>> {
	private static final ResourceLocation TEXTURE = OrdemParanormal.REGISTRY_HELPER.prefix("textures/entity/aberracao_carne.png");
	// Renderizar modelo
	public AberracaoCarneRenderer(EntityRendererProvider.Context context) {
		super(context, new AberracaoCarneModel<>(), 1F);
	}
	// Textura
	@Override
	public ResourceLocation getTextureLocation(AberracaoCarne entity) {
		return TEXTURE;
	}
}
