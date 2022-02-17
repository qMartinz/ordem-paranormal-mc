package com.guga.ordemparanormal.client.renderer;

import com.guga.ordemparanormal.client.model.ZumbiSangueModel;
import com.guga.ordemparanormal.common.entity.ZumbiSangue;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class ZumbiSangueRenderer extends MobRenderer<ZumbiSangue, ZumbiSangueModel<ZumbiSangue>> {
	private static final ResourceLocation TEXTURE = OrdemParanormal.REGISTRY_HELPER.prefix("textures/entity/zumbi_sangue.png");
	
	// Renderizar modelo
	public ZumbiSangueRenderer(EntityRendererProvider.Context context) {
		super(context, new ZumbiSangueModel<>(context.bakeLayer(ZumbiSangueModel.LAYER_LOCATION)), 0.3F);
	}
	
	// Textura
	@Override
	public ResourceLocation getTextureLocation(ZumbiSangue entity) {
		return TEXTURE;
	}
}
