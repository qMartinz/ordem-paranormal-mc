package com.guga.ordemparanormal.client.renderer;

import com.guga.ordemparanormal.client.model.TitaSangueModel;
import com.guga.ordemparanormal.common.entity.zumbissangue.TitaSangue;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class TitaSangueRenderer extends MobRenderer<TitaSangue, TitaSangueModel<TitaSangue>> {
	private static final ResourceLocation TEXTURE = OrdemParanormal.REGISTRY_HELPER.prefix("textures/entity/tita_sangue.png");
	// Renderizar modelo
	public TitaSangueRenderer(EntityRendererProvider.Context context) {
		super(context, new TitaSangueModel<>(), 1F);
	}
	// Textura
	@Override
	public ResourceLocation getTextureLocation(TitaSangue entity) {
		return TEXTURE;
	}
}
