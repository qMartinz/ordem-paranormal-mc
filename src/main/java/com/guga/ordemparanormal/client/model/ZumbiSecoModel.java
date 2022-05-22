package com.guga.ordemparanormal.client.model;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.guga.ordemparanormal.common.entity.zumbissangue.ZumbiSeco;
import com.guga.ordemparanormal.core.registry.OPEndimations;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.blueprint.core.endimator.Endimation;
import com.teamabnormals.blueprint.core.endimator.Endimator;
import com.teamabnormals.blueprint.core.endimator.EndimatorModelPart;
import com.teamabnormals.blueprint.core.endimator.entity.EndimatorEntityModel;
import com.teamabnormals.blueprint.core.endimator.model.EndimatorLayerDefinition;
import com.teamabnormals.blueprint.core.endimator.model.EndimatorPartDefinition;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;

public class ZumbiSecoModel<T extends ZumbiSeco> extends EndimatorEntityModel<T> {
	private static final Endimation walking = OPEndimations.ZUMBI_SECO_WALKING.asEndimation();
	private final EndimatorModelPart root;

	public ZumbiSecoModel() {
		this.endimator = Endimator.compile(this.root = createBodyLayer().bakeRoot());
	}

	public static EndimatorLayerDefinition createBodyLayer() {
		EndimatorPartDefinition root = EndimatorPartDefinition.root();

		EndimatorPartDefinition torso = root.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(32, 12).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.5F, 0.5F, -0.1745F, 0.0F, 0.0F));

		EndimatorPartDefinition lowerbody = torso.addOrReplaceChild("lowerbody", CubeListBuilder.create().texOffs(16, 14).addBox(-3.0F, 0.0F, -2.5F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.5F, 0.0F, 0.1309F, 0.0F, 0.0F));

		EndimatorPartDefinition rightleg = lowerbody.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(16, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.25F, 1.25F, -0.5F, -0.3009F, 0.4359F, 0.0203F));

		EndimatorPartDefinition rightlowerleg = rightleg.addOrReplaceChild("rightlowerleg", CubeListBuilder.create().texOffs(8, 26).addBox(-1.0F, -0.508F, -0.8799F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.6109F, 0.0F, 0.0F));

		EndimatorPartDefinition rightfoot = rightlowerleg.addOrReplaceChild("rightfoot", CubeListBuilder.create().texOffs(11, 20).addBox(-1.5F, -0.2415F, -4.0647F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.492F, 0.1201F, -0.2618F, 0.0F, 0.0F));

		EndimatorPartDefinition leftleg = lowerbody.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(25, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.25F, 0.9F, -0.5F, -0.1664F, -0.5666F, -0.0278F));

		EndimatorPartDefinition leftlowerleg = leftleg.addOrReplaceChild("leftlowerleg", CubeListBuilder.create().texOffs(0, 24).addBox(-1.0F, -0.508F, -0.8799F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.48F, 0.0F, 0.0F));

		EndimatorPartDefinition leftfoot = leftlowerleg.addOrReplaceChild("leftfoot", CubeListBuilder.create().texOffs(0, 18).addBox(-1.5F, -0.2415F, -4.0647F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.492F, 0.1201F, -0.2618F, 0.0F, 0.0F));

		EndimatorPartDefinition upperbody = torso.addOrReplaceChild("upperbody", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -2.5F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, -0.3927F, 0.0F, 0.0F));

		EndimatorPartDefinition neck = upperbody.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(30, 5).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		EndimatorPartDefinition jaw = neck.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(15, 5).addBox(-2.5F, -1.134F, -4.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(20, 0).addBox(-2.0F, -1.634F, -3.5F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		EndimatorPartDefinition head = jaw.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 10).addBox(-2.5F, -2.7835F, -4.375F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(22, 20).addBox(-2.0F, -0.0335F, -3.875F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.134F, 0.5F, -1.309F, 0.0F, 0.0F));

		EndimatorPartDefinition rightarm = upperbody.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(34, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -5.0F, -0.5F, 0.5672F, 0.0F, 0.2618F));

		EndimatorPartDefinition rightfobackm = rightarm.addOrReplaceChild("rightfobackm", CubeListBuilder.create().texOffs(22, 34).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		EndimatorPartDefinition leftarm = upperbody.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -5.0F, -0.5F, 0.5672F, 0.0F, -0.3054F));

		EndimatorPartDefinition leftforearm = leftarm.addOrReplaceChild("leftforearm", CubeListBuilder.create().texOffs(31, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		return new EndimatorLayerDefinition(root, 42, 43);
	}
	private static float computeWalkTime(float limbSwing, float length) {
		float period = length * 5.0F;
		return (((limbSwing + period) % period) / period) * length;
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float p_102621_, float p_102622_, float p_102623_) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, p_102621_, p_102622_, p_102623_);
		assert walking != null;
		float length = walking.getLength();
		float adjustedLimbSwingAmount = 4.0F * limbSwingAmount / length;
		if (adjustedLimbSwingAmount > 1.0F) {
			adjustedLimbSwingAmount = 1.0F;
		}
		this.endimator.apply(walking, computeWalkTime(limbSwing, length), adjustedLimbSwingAmount, Endimator.ResetMode.ALL);
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}