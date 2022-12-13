package com.guga.ordemparanormal.client.model;

import com.guga.ordemparanormal.common.entity.zumbissangue.TitaSangue;
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

public class TitaSangueModel<T extends TitaSangue> extends EndimatorEntityModel<T> {
	private static final Endimation walking = OPEndimations.TITA_WALKING.asEndimation();
	private final EndimatorModelPart root;

	public TitaSangueModel() {
		this.endimator = Endimator.compile(this.root = createLayer().bakeRoot());
	}

	public static EndimatorLayerDefinition createLayer() {
		EndimatorPartDefinition partdefinition = EndimatorPartDefinition.root();

		EndimatorPartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		EndimatorPartDefinition barriguinha = root.addOrReplaceChild("barriguinha", CubeListBuilder.create(), PartPose.offsetAndRotation(1.5F, -28.5791F, -0.1315F, 0.1745F, 0.0F, 0.0F));

		barriguinha.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-11.5F, -6.2F, -5.1F, 23.0F, 13.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.4209F, -1.3685F, -0.0873F, 0.0F, 0.0F));

		EndimatorPartDefinition upperbody = barriguinha.addOrReplaceChild("upperbody", CubeListBuilder.create().texOffs(0, 26).addBox(-11.5F, -10.0922F, -4.982F, 23.0F, 10.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.4702F, 0.2523F, 0.1309F, 0.0F, 0.0F));

		upperbody.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 47).addBox(-11.5F, -3.1F, -4.4F, 23.0F, 9.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5955F, -1.8444F, 0.1745F, 0.0F, 0.0F));

		EndimatorPartDefinition neck = upperbody.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offsetAndRotation(0.4F, -10.0447F, -3.3306F, -0.0436F, 0.0F, 0.0F));

		neck.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(92, 62).addBox(-6.0F, 1.5F, -1.0F, 12.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.5439F, 1.8306F, -0.1309F, 0.0F, 0.0F));

		EndimatorPartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -3.7079F, -0.4401F));

		head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 127).addBox(-6.0F, 4.0F, -4.0F, 12.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(39, 82).addBox(-6.0F, -4.0F, -4.0F, 12.0F, 8.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -5.0F, 0.1745F, 0.0F, 0.0F));

		head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(37, 67).addBox(-2.6F, 1.2F, -4.4F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 8).addBox(-2.6F, 1.2F, -5.4F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(7, 0).addBox(-2.1F, 4.2F, -4.4F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9322F, -1.7479F, -4.7555F, -0.0895F, -0.1059F, -0.7686F));

		head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(4, 34).addBox(4.1F, 1.2F, -4.4F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 53).addBox(3.6F, -1.8F, -5.4F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(62, 101).addBox(3.6F, -1.8F, -4.4F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1322F, -1.7479F, -4.7555F, -0.0895F, 0.1059F, 0.7686F));

		EndimatorPartDefinition mandible = neck.addOrReplaceChild("mandible", CubeListBuilder.create(), PartPose.offsetAndRotation(0.1F, 2.9474F, -3.3486F, -0.2618F, 0.0F, 0.0F));

		mandible.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(96, 20).addBox(-4.0F, -1.4F, -0.8F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 1.8966F, -0.6916F, 1.0036F, 0.0F, 0.0F));

		mandible.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 118).addBox(-5.0F, -4.3F, -4.4F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(92, 52).addBox(-5.0F, -3.3F, -4.4F, 10.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 1.8966F, -0.6916F, 0.1745F, 0.0F, 0.0F));

		mandible.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(63, 0).addBox(0.8F, -5.0F, 0.8F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(57, 52).addBox(-0.2F, -2.0F, -0.2F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9965F, 2.1029F, -7.4984F, 0.279F, -0.5318F, 0.2829F));

		mandible.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 34).addBox(-2.0F, -2.8F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 101).addBox(-3.0F, -1.0F, -3.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9F, 1.3966F, -3.1916F, 0.2159F, -0.2192F, 0.1406F));

		mandible.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(59, 4).addBox(-2.0F, -3.4F, 0.6F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9F, 1.3966F, -3.1916F, 0.3031F, -0.2192F, 0.1406F));

		mandible.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 26).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2809F, -0.6839F, -0.4986F, -0.4386F, -0.2192F, 0.1406F));

		mandible.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(59, 0).addBox(1.0F, -3.4F, 0.6F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.9F, 1.3966F, -3.1916F, 0.3031F, 0.2192F, -0.1406F));

		mandible.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(7, 26).addBox(1.0F, -2.8F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(74, 82).addBox(1.0F, -1.0F, -3.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.9F, 1.3966F, -3.1916F, 0.2159F, 0.2192F, -0.1406F));

		mandible.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(8, 8).addBox(-1.8F, -5.0F, 0.8F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 47).addBox(-2.8F, -2.0F, -0.2F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.9965F, 2.1029F, -7.4984F, 0.279F, 0.5318F, -0.2829F));

		mandible.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.2809F, -0.6839F, -0.4986F, -0.4386F, 0.2192F, -0.1406F));

		EndimatorPartDefinition leftarm = upperbody.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 67).mirror().addBox(-3.0F, -3.0F, -6.5F, 12.0F, 12.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(12.5F, -11.9507F, -0.6208F, -0.1308F, -0.0057F, -0.0433F));

		leftarm.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(85, 82).mirror().addBox(-5.0F, -4.5F, -6.5F, 10.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 12.5F, 0.0F, -0.0873F, 0.0F, 0.0F));

		EndimatorPartDefinition leftforearm = leftarm.addOrReplaceChild("leftforearm", CubeListBuilder.create().texOffs(55, 54).mirror().addBox(-6.0F, -0.5F, -6.5F, 12.0F, 15.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 14.5F, -0.4F, -0.2182F, 0.0F, 0.0F));

		leftforearm.addOrReplaceChild("lefthand", CubeListBuilder.create().texOffs(59, 13).mirror().addBox(-6.0F, -2.0F, -6.5F, 12.0F, 8.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 16.5F, 0.0F));

		EndimatorPartDefinition rightarm = upperbody.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(0, 67).addBox(-10.0F, -3.0F, -6.5F, 12.0F, 12.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.5F, -11.9507F, -0.6208F, -0.1744F, 0.0076F, 0.043F));

		rightarm.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(85, 82).addBox(-5.0F, -4.5F, -6.5F, 10.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 12.5F, 0.0F, -0.0873F, 0.0F, 0.0F));

		EndimatorPartDefinition rightforearm = rightarm.addOrReplaceChild("rightforearm", CubeListBuilder.create().texOffs(55, 54).addBox(-6.0F, -0.5F, -6.5F, 12.0F, 15.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 14.5F, -0.4F, -0.1309F, 0.0F, 0.0F));

		rightforearm.addOrReplaceChild("righthand", CubeListBuilder.create().texOffs(59, 13).addBox(-6.0F, -2.0F, -6.5F, 12.0F, 8.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.5F, 0.0F));

		EndimatorPartDefinition lowerbody = root.addOrReplaceChild("lowerbody", CubeListBuilder.create().texOffs(57, 36).addBox(-11.5F, 6.0F, -3.5F, 23.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -33.0F, -1.5F));

		EndimatorPartDefinition rightleg = lowerbody.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 92).addBox(-5.0F, 0.5F, -5.5F, 10.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, 10.5F, 2.0F, -0.3491F, 0.1745F, 0.0F));

		EndimatorPartDefinition rightlowerleg = rightleg.addOrReplaceChild("rightlowerleg", CubeListBuilder.create().texOffs(96, 0).addBox(-4.0F, 0.5F, -4.5F, 8.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 0.0F, 0.7418F, 0.0F, 0.0F));

		EndimatorPartDefinition rightfoot = rightlowerleg.addOrReplaceChild("rightfoot", CubeListBuilder.create().texOffs(59, 0).addBox(-4.0F, 0.0F, -5.5F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.5F, 1.0F, -0.3927F, 0.0F, 0.0F));

		rightfoot.addOrReplaceChild("righfinger", CubeListBuilder.create().texOffs(73, 102).addBox(-4.0F, -1.0F, -3.5F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -5.0F));

		EndimatorPartDefinition leftleg = lowerbody.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 92).mirror().addBox(-5.0F, 0.5F, -5.5F, 10.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.5F, 10.5F, 2.0F, -0.3491F, -0.0873F, 0.0F));

		EndimatorPartDefinition leftlowerleg = leftleg.addOrReplaceChild("leftlowerleg", CubeListBuilder.create().texOffs(96, 0).mirror().addBox(-4.0F, 0.5F, -4.5F, 8.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 11.0F, 0.0F, 0.7418F, 0.0F, 0.0F));

		EndimatorPartDefinition leftfoot = leftlowerleg.addOrReplaceChild("leftfoot", CubeListBuilder.create().texOffs(59, 0).mirror().addBox(-4.0F, 0.0F, -5.5F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 11.5F, 1.0F, -0.3927F, 0.0F, 0.0F));

		leftfoot.addOrReplaceChild("righfinger2", CubeListBuilder.create().texOffs(73, 102).mirror().addBox(-4.0F, -1.0F, -3.5F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 1.0F, -5.0F));

		return new EndimatorLayerDefinition(root, 130, 140);
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
		float adjustedLimbSwingAmount = 6.0F * limbSwingAmount / length;
		if (adjustedLimbSwingAmount > 1.0F) {
			adjustedLimbSwingAmount = 1.0F;
		}
		this.endimator.apply(walking, computeWalkTime(limbSwing, length), adjustedLimbSwingAmount, Endimator.ResetMode.UNAPPLY);
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}