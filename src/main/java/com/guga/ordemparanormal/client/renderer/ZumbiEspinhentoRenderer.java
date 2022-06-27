package com.guga.ordemparanormal.client.renderer;

import com.guga.ordemparanormal.client.model.ZumbiEspinhentoModel;
import com.guga.ordemparanormal.common.entity.zumbissangue.ZumbiEspinhento;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class ZumbiEspinhentoRenderer extends MobRenderer<ZumbiEspinhento, ZumbiEspinhentoModel<ZumbiEspinhento>> {
	private static final ResourceLocation TEXTURE = OrdemParanormal.REGISTRY_HELPER.prefix("textures/entity/zumbi_espinhento.png");

	// Renderizar modelo
	public ZumbiEspinhentoRenderer(EntityRendererProvider.Context context) {
		super(context, new ZumbiEspinhentoModel<>(), 0.6F);
	}

	// Textura
	@Override
	public ResourceLocation getTextureLocation(ZumbiEspinhento entity) {
		return TEXTURE;
	}
}
