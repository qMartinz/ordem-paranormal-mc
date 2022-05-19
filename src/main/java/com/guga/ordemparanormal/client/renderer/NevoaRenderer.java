package com.guga.ordemparanormal.client.renderer;

import com.guga.ordemparanormal.client.model.NevoaModel;
import com.guga.ordemparanormal.common.entity.Nevoa;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public final class NevoaRenderer extends EntityRenderer<Nevoa>{
	private final NevoaModel<Nevoa> model;
	private static final ResourceLocation TEXTURE = OrdemParanormal.REGISTRY_HELPER.prefix("textures/entity/nevoa.png");
	public NevoaRenderer(EntityRendererProvider.Context context) {
	      super(context);
	      this.model = new NevoaModel<>(NevoaModel.createBodyLayer().bakeRoot());
	   }
	public void render(Nevoa pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
		super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
	}
	@Override
	public ResourceLocation getTextureLocation(Nevoa entity) {
		return TEXTURE;
	}
}
