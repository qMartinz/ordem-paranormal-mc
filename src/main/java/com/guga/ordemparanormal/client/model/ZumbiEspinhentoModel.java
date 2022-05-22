package com.guga.ordemparanormal.client.model;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.guga.ordemparanormal.common.entity.zumbissangue.ZumbiEspinhento;
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

public class ZumbiEspinhentoModel<T extends ZumbiEspinhento> extends EndimatorEntityModel<T> {
	private static final Endimation walking = OPEndimations.ZUMBI_ESPINHENTO_WALKING.asEndimation();
	private final EndimatorModelPart root;

	public ZumbiEspinhentoModel() {
		this.endimator = Endimator.compile(this.root = createBodyLayer().bakeRoot());
	}

	public static EndimatorLayerDefinition createBodyLayer() {
		EndimatorPartDefinition root = EndimatorPartDefinition.root();

		EndimatorPartDefinition torso = root.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(34, 12).addBox(-2.5F, -2.0F, -1.5F, 5.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.0494F, 0.5262F, 0.3927F, 0.0873F, 0.0F));

		EndimatorPartDefinition lowerbody = torso.addOrReplaceChild("lowerbody", CubeListBuilder.create().texOffs(16, 15).addBox(-3.5F, -0.517F, -2.1294F, 7.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0304F, 0.0607F, -0.088F, -0.0898F, 0.0168F));

		EndimatorPartDefinition leftleg = lowerbody.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(36, 34).addBox(-1.5F, -1.1418F, -1.4726F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.05F, 1.308F, -0.1056F, -0.9457F, -0.4149F, 0.1393F));

		EndimatorPartDefinition leftlowerleg = leftleg.addOrReplaceChild("leftlowerleg", CubeListBuilder.create().texOffs(24, 34).addBox(-1.5F, -0.8271F, -1.3761F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.8457F, 0.0595F, 1.0036F, 0.0F, 0.0F));

		EndimatorPartDefinition leftfoot = leftlowerleg.addOrReplaceChild("leftfoot", CubeListBuilder.create().texOffs(14, 21).addBox(-2.0F, -0.0331F, -4.3274F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.6744F, 0.1173F, -0.3927F, 0.0F, 0.0F));

		EndimatorPartDefinition rightleg = lowerbody.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(12, 34).addBox(-1.5F, -1.1418F, -1.4726F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.05F, 1.308F, -0.1056F, -1.1868F, 0.538F, -0.1893F));

		EndimatorPartDefinition rightlowerleg = rightleg.addOrReplaceChild("rightlowerleg", CubeListBuilder.create().texOffs(0, 34).addBox(-1.5F, -0.8271F, -1.3761F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.8457F, 0.0595F, 1.2217F, 0.0F, 0.0F));

		EndimatorPartDefinition rightfoot = rightlowerleg.addOrReplaceChild("rightfoot", CubeListBuilder.create().texOffs(18, 5).addBox(-2.0F, -0.0331F, -4.3274F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.7244F, 0.1173F, -0.4051F, -0.2415F, 0.1022F));

		EndimatorPartDefinition upperbody = torso.addOrReplaceChild("upperbody", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -5.6373F, -2.8961F, 7.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -5.25F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -3.5F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -1.75F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.9339F, 0.0358F, 0.1734F, 0.084F, -0.0226F));

		EndimatorPartDefinition neck = upperbody.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(40, 27).addBox(-1.5F, -3.5206F, -1.4857F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -1.0F, 1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(50, 42).addBox(2.0F, -3.5443F, -4.3007F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(50, 42).addBox(-3.0F, -3.5443F, -4.3007F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -2.5F, 1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.5863F, 0.0209F, 0.3927F, 0.0F, 0.0F));

		EndimatorPartDefinition jaw = neck.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(50, 42).addBox(1.5F, -3.0F, -4.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(50, 42).addBox(-3.5F, -3.0F, -4.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(50, 42).addBox(0.25F, -4.5F, -4.75F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(50, 42).addBox(-2.25F, -4.5F, -4.75F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -3.0443F, 0.1993F, -0.2356F, 0.0F, 0.0F));

		jaw.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 6).addBox(-2.55F, -0.25F, -3.25F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5566F, -0.6714F, 0.2618F, 0.0F, 0.0F));

		jaw.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 19).addBox(-2.0F, -2.25F, -3.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.8748F, -0.4419F, 0.3054F, 0.0F, 0.0F));

		EndimatorPartDefinition head = jaw.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 11).addBox(-3.0F, -2.9966F, -4.5138F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(43, 7).addBox(-2.55F, -0.3466F, -4.4138F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5553F, 0.6444F, -0.0524F, 0.0F, 0.0F));

		EndimatorPartDefinition leftarm = upperbody.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(32, 0).addBox(-0.1833F, -1.9447F, -1.4941F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.3696F, -3.6486F, 0.0235F, 0.1039F, -0.1682F, 1.2678F));

		EndimatorPartDefinition leftforearm = leftarm.addOrReplaceChild("leftforearm", CubeListBuilder.create().texOffs(28, 21).addBox(-0.1453F, -1.5F, -1.4073F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7901F, -0.4447F, -0.0579F, 0.0F, 0.6981F, 0.0F));

		EndimatorPartDefinition rightarm = upperbody.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(20, 28).addBox(-6.8167F, -1.9447F, -1.4941F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3696F, -3.6486F, 0.0235F, 0.5062F, -0.1329F, -1.1685F));

		EndimatorPartDefinition rightforearm = rightarm.addOrReplaceChild("rightforearm", CubeListBuilder.create().texOffs(0, 28).addBox(-6.8547F, -1.5F, -1.4073F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7901F, -0.4447F, -0.0579F, 0.0F, -0.5236F, 0.0F));

		return new EndimatorLayerDefinition(root, 57, 45);
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