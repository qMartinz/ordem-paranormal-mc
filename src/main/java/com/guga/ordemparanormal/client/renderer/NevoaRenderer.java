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

public class NevoaRenderer extends EntityRenderer<Nevoa>{
	private final NevoaModel<Nevoa> model;
	private static final ResourceLocation TEXTURE = OrdemParanormal.REGISTRY_HELPER.prefix("textures/entity/nevoa.png");
	
	public NevoaRenderer(EntityRendererProvider.Context context) {
	      super(context);
	      this.model = new NevoaModel<Nevoa>(NevoaModel.createBodyLayer().bakeRoot());
	   }
	
	public void render(Nevoa entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource source, int packedLightIn) {
		poseStack.pushPose();
		poseStack.translate(0.0D, (double)0.1F, 0.0D);
		poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
		VertexConsumer vertexConsumer = source.getBuffer(this.model.renderType(this.getTextureLocation(entity)));
		this.model.renderToBuffer(poseStack, vertexConsumer, packedLightIn, packedLightIn, packedLightIn, entityYaw, partialTicks, packedLightIn);
		poseStack.popPose();
		super.render(entity, entityYaw, partialTicks, poseStack, source, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(Nevoa entity) {
		return TEXTURE;
	}
	
	
}
