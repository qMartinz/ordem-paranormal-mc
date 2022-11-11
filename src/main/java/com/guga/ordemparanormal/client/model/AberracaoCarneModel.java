package com.guga.ordemparanormal.client.model;

import com.guga.ordemparanormal.common.entity.AberracaoCarne;
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

public class AberracaoCarneModel<T extends AberracaoCarne> extends EndimatorEntityModel<T> {
	private static final Endimation walking = OPEndimations.ABERRACAO_WALKING.asEndimation();
	private final EndimatorModelPart torso;
	public AberracaoCarneModel() {
		this.endimator = Endimator.compile(this.torso = createLayer().bakeRoot());
	}
	public static EndimatorLayerDefinition createLayer() {
		EndimatorPartDefinition root = EndimatorPartDefinition.root();

		EndimatorPartDefinition torso = root.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 48).addBox(-10.0F, -11.875F, 1.25F, 20.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(-10.0F, -2.875F, -8.75F, 20.0F, 5.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(59, 15).addBox(-9.5F, -9.875F, -8.25F, 5.0F, 7.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(37, 49).addBox(4.5F, -9.875F, -8.25F, 5.0F, 7.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(55, 37).addBox(-4.5F, -8.875F, -8.0F, 9.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(55, 35).addBox(-4.5F, -4.875F, -8.0F, 9.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.875F, 0.8917F));

		EndimatorPartDefinition uppertorso = torso.addOrReplaceChild("uppertorso", CubeListBuilder.create().texOffs(0, 0).addBox(-10.5F, -11.6532F, -7.4133F, 21.0F, 13.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.1162F, -0.9659F, 0.2618F, 0.0F, 0.0F));

		uppertorso.addOrReplaceChild("righthead", CubeListBuilder.create().texOffs(28, 99).addBox(-4.25F, -7.2923F, -4.3068F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -11.6532F, -3.6633F, 0.2182F, 0.0F, 0.0F));

		uppertorso.addOrReplaceChild("lefthead", CubeListBuilder.create().texOffs(93, 96).addBox(-3.7847F, -7.4331F, -4.0913F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -11.6532F, -3.4133F, 0.0855F, -0.0283F, 0.1278F));

		EndimatorPartDefinition leftlowerarm = uppertorso.addOrReplaceChild("leftlowerarm", CubeListBuilder.create().texOffs(0, 76).addBox(-11.4925F, -3.2478F, -3.6045F, 13.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.2905F, -7.5781F, 0.9296F, 0.3927F, 0.0F, -0.8727F));

		leftlowerarm.addOrReplaceChild("leftlowerforearm", CubeListBuilder.create().texOffs(33, 69).addBox(-10.2F, -3.25F, -4.6715F, 13.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.3092F, 0.0599F, -0.398F, 0.0F, -1.0036F, 0.0F));

		EndimatorPartDefinition rightlowerarm = uppertorso.addOrReplaceChild("rightlowerarm", CubeListBuilder.create().texOffs(0, 76).mirror().addBox(-1.5075F, -3.2478F, -3.6045F, 13.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.2905F, -7.5781F, 0.9296F, 0.3927F, 0.0F, 0.8727F));

		rightlowerarm.addOrReplaceChild("rightlowerforearm", CubeListBuilder.create().texOffs(33, 69).mirror().addBox(-2.8F, -3.25F, -4.6715F, 13.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(11.3092F, 0.0599F, -0.398F, 0.0F, 1.0036F, 0.0F));

		EndimatorPartDefinition leftupperarm = uppertorso.addOrReplaceChild("leftupperarm", CubeListBuilder.create().texOffs(65, 90).addBox(-9.563F, -2.5274F, -3.2546F, 11.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.2905F, -10.0046F, -0.363F, 0.219F, -0.3286F, 0.0944F));

		leftupperarm.addOrReplaceChild("leftupperforearm", CubeListBuilder.create().texOffs(66, 76).addBox(-9.0896F, -3.1493F, -5.0254F, 12.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0747F, 0.6795F, -0.0351F, 0.0F, -1.1781F, 0.0F));

		EndimatorPartDefinition rightupperarm = uppertorso.addOrReplaceChild("rightupperarm", CubeListBuilder.create().texOffs(65, 90).mirror().addBox(-1.437F, -2.5274F, -3.2546F, 11.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.2905F, -10.0046F, -0.363F, 0.219F, 0.3286F, -0.0944F));

		rightupperarm.addOrReplaceChild("rightupperforearm", CubeListBuilder.create().texOffs(66, 76).mirror().addBox(-2.9104F, -3.1493F, -5.0254F, 12.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.0747F, 0.6795F, -0.0351F, 0.0F, 1.1781F, 0.0F));

		EndimatorPartDefinition frontleftleg = torso.addOrReplaceChild("frontleftleg", CubeListBuilder.create().texOffs(104, 69).addBox(-2.9048F, -0.1072F, -3.7145F, 7.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.4159F, 1.5F, -4.7833F, -0.4363F, 0.2182F, 0.0F));

		EndimatorPartDefinition frontlowerleftleg = frontleftleg.addOrReplaceChild("frontlowerleftleg", CubeListBuilder.create().texOffs(28, 115).addBox(-3.5F, -1.7099F, -4.1986F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5952F, 9.0806F, -0.0805F, 1.0472F, 0.0F, 0.0F));

		frontlowerleftleg.addOrReplaceChild("frontleftfeet", CubeListBuilder.create().texOffs(86, 3).addBox(-3.5F, -0.6973F, -8.5104F, 7.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, 7.4253F, -0.0245F, -0.6109F, 0.0F, 0.0F));

		EndimatorPartDefinition frontrightleg = torso.addOrReplaceChild("frontrightleg", CubeListBuilder.create().texOffs(104, 69).mirror().addBox(-4.0952F, -0.1072F, -3.7145F, 7.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.4159F, 1.5F, -4.7833F, -0.4363F, -0.2182F, 0.0F));

		EndimatorPartDefinition frontlowerrightleg = frontrightleg.addOrReplaceChild("frontlowerrightleg", CubeListBuilder.create().texOffs(28, 115).mirror().addBox(-3.5F, -1.7099F, -4.1986F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5952F, 9.0806F, -0.0805F, 1.0472F, 0.0F, 0.0F));

		frontlowerrightleg.addOrReplaceChild("frontrightfeet", CubeListBuilder.create().texOffs(86, 3).mirror().addBox(-3.5F, -0.6973F, -8.5104F, 7.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.025F, 7.4253F, -0.0245F, -0.6109F, 0.0F, 0.0F));

		EndimatorPartDefinition backleftleg = torso.addOrReplaceChild("backleftleg", CubeListBuilder.create().texOffs(0, 106).addBox(-2.8507F, -0.0041F, -3.0643F, 7.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.4159F, 1.5F, 2.5F, 0.4363F, -0.2182F, 0.0F));

		EndimatorPartDefinition backlowerleftleg = backleftleg.addOrReplaceChild("backlowerleftleg", CubeListBuilder.create().texOffs(116, 112).addBox(0.4392F, 0.0144F, -3.3417F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2899F, 8.7895F, 2.0652F, -1.0472F, 0.0F, 0.0F));

		backlowerleftleg.addOrReplaceChild("backleftfeet", CubeListBuilder.create().texOffs(0, 90).addBox(-3.5F, -0.3491F, -2.4282F, 7.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.9142F, 8.8996F, -0.7658F, 0.6109F, 0.0F, 0.0F));

		EndimatorPartDefinition backrightleg = torso.addOrReplaceChild("backrightleg", CubeListBuilder.create().texOffs(0, 106).mirror().addBox(-4.1493F, -0.0041F, -3.0643F, 7.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.4159F, 1.5F, 2.5F, 0.4363F, 0.2182F, 0.0F));

		EndimatorPartDefinition backlowerrightleg = backrightleg.addOrReplaceChild("backlowerrightleg", CubeListBuilder.create().texOffs(116, 112).mirror().addBox(-7.4392F, 0.0144F, -3.3417F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.2899F, 8.7895F, 2.0652F, -1.0472F, 0.0F, 0.0F));

		backlowerrightleg.addOrReplaceChild("backrightfeet", CubeListBuilder.create().texOffs(0, 90).mirror().addBox(-3.5F, -0.3491F, -2.4282F, 7.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.9142F, 8.8996F, -0.7658F, 0.6109F, 0.0F, 0.0F));

		return new EndimatorLayerDefinition(root, 146, 136);
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
		this.endimator.apply(walking, computeWalkTime(limbSwing, length), adjustedLimbSwingAmount, Endimator.ResetMode.NONE);
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		torso.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}