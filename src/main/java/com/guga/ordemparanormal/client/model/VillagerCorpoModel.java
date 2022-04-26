package com.guga.ordemparanormal.client.model;

import com.guga.ordemparanormal.common.entity.corpos.VillagerCorpo;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;


public class VillagerCorpoModel<T extends VillagerCorpo> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation(OrdemParanormal.MOD_ID, "villager_corpo"), "main");
	private final ModelPart body;

	// Criar modelo
	public VillagerCorpoModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	// Definir partes do modelo
	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 20).addBox(-11.0F, -15.5F, 3.25F, 23.0F, 26.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 20.5F, -2.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 48)
						.addBox(-3.6F, -9.9F, -3.6F, 7.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -9.5F, 0.0F, 0.0F, 0.0F, 0.5236F));

		return LayerDefinition.create(meshdefinition, 46, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	// Renderizar
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		body.render(poseStack, buffer, packedLight, packedOverlay);
	}
}