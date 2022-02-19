package com.guga.ordemparanormal.client.model;

import com.guga.ordemparanormal.common.entity.Aberrado;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.blueprint.core.endimator.Endimator;

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

public class AberradoModel<E extends Aberrado> extends EntityModel<E> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation(OrdemParanormal.MOD_ID, "aberrado"), "main");
	
	private final ModelPart torso;
	private final Endimator endimator;

	// Criar modelo
	public AberradoModel(ModelPart root) {
		this.torso = root.getChild("torso");
		this.endimator = Endimator.shortCompile(root);
	}

	// Definição de todas as partes do modelo
	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition torso = partdefinition.addOrReplaceChild("torso",
				CubeListBuilder.create().texOffs(0, 22)
						.addBox(-9.0F, -8.5F, -4.5F, 18.0F, 17.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(45, 25)
						.addBox(6.0F, -1.0F, 3.25F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.25F, -13.5F, 0.5F, 0.1745F, 0.0F, 0.0F));

		PartDefinition upperbody = torso.addOrReplaceChild("upperbody", CubeListBuilder.create().texOffs(0, 0)
				.addBox(-10.2083F, -11.523F, -5.4819F, 19.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(54, 25)
				.addBox(-8.7083F, -13.773F, -7.2319F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(76, 25)
				.addBox(4.7917F, -10.523F, -7.2319F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(78, 55)
				.addBox(1.2917F, -14.773F, -3.4819F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(32, 51)
				.addBox(-0.4583F, -13.273F, 2.0181F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(92, 42)
				.addBox(-7.2083F, -5.773F, 3.0181F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7083F, -6.3549F, 0.1309F, 0.2182F, 0.0F, 0.0F));

		PartDefinition rightarm = upperbody.addOrReplaceChild("rightarm",
				CubeListBuilder.create().texOffs(80, 91)
						.addBox(-2.5015F, -0.2064F, -3.2475F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
						.texOffs(91, 21).addBox(-4.0F, 1.0F, -1.25F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
						.texOffs(48, 0).addBox(-4.0F, 5.25F, -4.75F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-10.2121F, -7.3602F, 0.3554F, -1.2018F, -1.1324F, 1.6332F));

		PartDefinition rightforearm = rightarm.addOrReplaceChild("rightforearm",
				CubeListBuilder.create().texOffs(0, 88)
						.addBox(-3.0F, -0.2346F, -3.2051F, 6.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(18, 88)
						.addBox(-4.0F, 6.0F, 0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.4985F, 11.8796F, 0.0121F, -0.3927F, 0.0F, 0.0F));

		PartDefinition leftarm = upperbody.addOrReplaceChild("leftarm",
				CubeListBuilder.create().texOffs(56, 91)
						.addBox(-3.4985F, -0.2064F, -3.2475F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
						.texOffs(64, 0).addBox(0.0F, 2.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
						.texOffs(74, 91).addBox(0.0F, 8.0F, -3.75F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.7955F, -7.3602F, 0.3554F, -0.9372F, 0.7905F, -1.5688F));

		PartDefinition leftforearm = leftarm.addOrReplaceChild("leftforearm",
				CubeListBuilder.create().texOffs(32, 78)
						.addBox(-3.0F, -0.2346F, -3.2051F, 6.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(56, 84)
						.addBox(-3.75F, 5.25F, 1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.4985F, 11.8796F, 0.0121F, -1.9721F, -0.3486F, -0.6443F));

		PartDefinition lowerbody = torso.addOrReplaceChild("lowerbody",
				CubeListBuilder.create().texOffs(44, 38)
						.addBox(-9.5F, -0.4896F, -5.0124F, 19.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
						.texOffs(50, 78).addBox(-7.75F, -1.0F, 3.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 7.9706F, 0.1734F, -0.0873F, 0.0F, 0.0F));

		PartDefinition rightupperleg = lowerbody.addOrReplaceChild("rightupperleg",
				CubeListBuilder.create().texOffs(77, 0)
						.addBox(-3.625F, -2.6924F, -3.8155F, 8.0F, 13.0F, 8.0F, new CubeDeformation(0.0F))
						.texOffs(0, 48).addBox(-4.375F, 4.1375F, -4.4755F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.375F, 4.199F, -0.0515F, -0.2189F, -0.1304F, 0.0115F));

		PartDefinition rightlowerleg = rightupperleg.addOrReplaceChild("rightlowerleg",
				CubeListBuilder.create().texOffs(0, 65)
						.addBox(-5.5F, -0.1555F, -4.1232F, 8.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 55)
						.addBox(-1.0F, 4.2137F, -5.6175F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.875F, 9.8489F, -0.1691F, 0.3491F, 0.0F, 0.0F));

		PartDefinition rightfoot = rightlowerleg.addOrReplaceChild("rightfoot",
				CubeListBuilder.create().texOffs(0, 48).addBox(-4.5F, -0.4914F, -9.9232F, 9.0F, 3.0F, 14.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.5F, 13.5901F, -0.0772F, -0.2233F, 0.2129F, -0.0479F));

		PartDefinition leftupperleg = lowerbody.addOrReplaceChild("leftupperleg",
				CubeListBuilder.create().texOffs(70, 70)
						.addBox(-4.375F, -2.6924F, -3.8155F, 8.0F, 13.0F, 8.0F, new CubeDeformation(0.0F))
						.texOffs(33, 70).addBox(1.125F, 6.6375F, -4.4755F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.375F, 4.199F, -0.0515F, -0.2212F, -0.2608F, 0.0233F));

		PartDefinition leftlowerleg = leftupperleg.addOrReplaceChild("leftlowerleg",
				CubeListBuilder.create().texOffs(46, 55)
						.addBox(-2.5F, -0.1555F, -4.1232F, 8.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(92, 36)
						.addBox(-1.75F, 0.9637F, -5.6175F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(24, 65)
						.addBox(-3.0F, 4.5F, 2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.875F, 9.8489F, -0.1691F, 0.3491F, 0.0F, 0.0F));

		PartDefinition leftfoot = leftlowerleg.addOrReplaceChild("leftfoot",
				CubeListBuilder.create().texOffs(45, 8).addBox(-4.5F, -0.4914F, -9.9232F, 9.0F, 3.0F, 14.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5F, 13.5901F, -0.0772F, -0.2214F, 0.1704F, -0.0381F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	// Renderizar
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		torso.render(poseStack, buffer, packedLight, packedOverlay);
	}
}