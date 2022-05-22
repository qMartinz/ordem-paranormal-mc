package com.guga.ordemparanormal.client.renderer;

import com.guga.ordemparanormal.client.model.ZumbiSangueModel;
import com.guga.ordemparanormal.client.model.ZumbiSecoModel;
import com.guga.ordemparanormal.common.entity.zumbissangue.ZumbiSangue;
import com.guga.ordemparanormal.common.entity.zumbissangue.ZumbiSeco;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class ZumbiSecoRenderer extends MobRenderer<ZumbiSeco, ZumbiSecoModel<ZumbiSeco>> {
	private static final ResourceLocation TEXTURE = OrdemParanormal.REGISTRY_HELPER.prefix("textures/entity/zumbi_seco.png");

	// Renderizar modelo
	public ZumbiSecoRenderer(EntityRendererProvider.Context context) {
		super(context, new ZumbiSecoModel<>(), 0.6F);
	}

	// Textura
	@Override
	public ResourceLocation getTextureLocation(ZumbiSeco entity) {
		return TEXTURE;
	}
}
