package com.guga.ordemparanormal.client.model;

import java.util.function.Supplier;

import com.guga.ordemparanormal.common.entity.ZumbiSangue;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.blueprint.client.ClientInfo;
import com.teamabnormals.blueprint.core.Blueprint;
import com.teamabnormals.blueprint.core.endimator.Endimation;
import com.teamabnormals.blueprint.core.endimator.Endimator;
import com.teamabnormals.blueprint.core.endimator.interpolation.EndimationEasers;

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

public class ZumbiSangueModel <E extends ZumbiSangue> extends EntityModel<E> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation(OrdemParanormal.MOD_ID, "zumbi_sangue"), "main");
	private static final Supplier<Endimation> IDLE = () -> Blueprint.ENDIMATION_LOADER.getEndimation(new ResourceLocation(OrdemParanormal.MOD_ID, "zumbisangue/idle"));
	
	private final Endimator endimator;
	private final ModelPart root;

	public ZumbiSangueModel(ModelPart root) {
		this.root = root.getChild("torso");
		this.endimator = Endimator.shortCompile(root);
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition torso = partdefinition.addOrReplaceChild("torso",
				CubeListBuilder.create().texOffs(34, 12).addBox(-2.5F, -2.0F, -1.5F, 5.0F, 4.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 6.5994F, 0.5262F, 0.0436F, 0.0F, 0.0F));

		PartDefinition lowerbody = torso.addOrReplaceChild("lowerbody",
				CubeListBuilder.create().texOffs(16, 15).addBox(-3.5F, -0.517F, -2.1294F, 7.0F, 2.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.0304F, 0.0607F, 0.2618F, 0.0F, 0.0F));

		PartDefinition leftleg = lowerbody.addOrReplaceChild("leftleg",
				CubeListBuilder.create().texOffs(36, 34).addBox(-1.5F, -1.1418F, -1.4726F, 3.0F, 8.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.05F, 1.308F, -0.1056F, -0.3054F, 0.0F, 0.0F));

		PartDefinition leftlowerleg = leftleg.addOrReplaceChild("leftlowerleg",
				CubeListBuilder.create().texOffs(24, 34).addBox(-1.5F, -0.8271F, -1.3761F, 3.0F, 8.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 6.8457F, -0.0405F, 0.2182F, 0.0F, 0.0F));

		PartDefinition leftfoot = leftlowerleg.addOrReplaceChild("leftfoot",
				CubeListBuilder.create().texOffs(14, 21).addBox(-2.0F, -0.0331F, -4.3274F, 4.0F, 1.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 6.4744F, 0.1173F, -0.2182F, 0.0F, 0.0F));

		PartDefinition rightleg = lowerbody.addOrReplaceChild("rightleg",
				CubeListBuilder.create().texOffs(12, 34).addBox(-1.5F, -1.1418F, -1.4726F, 3.0F, 8.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.05F, 1.308F, -0.1056F, -0.3054F, 0.0F, 0.0F));

		PartDefinition rightlowerleg = rightleg.addOrReplaceChild("rightlowerleg",
				CubeListBuilder.create().texOffs(0, 34).addBox(-1.5F, -0.8271F, -1.3761F, 3.0F, 8.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 6.8457F, -0.0405F, 0.2182F, 0.0F, 0.0F));

		PartDefinition rightfoot = rightlowerleg.addOrReplaceChild("rightfoot",
				CubeListBuilder.create().texOffs(18, 5).addBox(-2.0F, -0.0331F, -4.3274F, 4.0F, 1.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 6.4744F, 0.1173F, -0.2182F, 0.0F, 0.0F));

		PartDefinition upperbody = torso.addOrReplaceChild("upperbody",
				CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -5.6373F, -2.8961F, 7.0F, 6.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.9339F, 0.0358F, -0.2618F, 0.0F, 0.0F));

		PartDefinition neck = upperbody.addOrReplaceChild("neck",
				CubeListBuilder.create().texOffs(40, 27).addBox(-1.5F, -3.5206F, -1.4857F, 3.0F, 4.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -5.5863F, 0.0209F, 0.1745F, 0.0F, 0.0F));

		PartDefinition jaw = neck.addOrReplaceChild("jaw", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0.5F, -3.0443F, 0.1993F, 0.2007F, 0.0F, 0.0F));

		jaw.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(0, 19).addBox(-2.0F, -2.25F, -3.0F, 5.0F, 2.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 0.8748F, -0.4419F, 0.3054F, 0.0F, 0.0F));

		PartDefinition head = jaw.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 11).addBox(-3.0F, -2.9966F, -4.5138F, 5.0F, 3.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.5553F, 0.6444F, -0.2705F, 0.0F, 0.0F));

		head.addOrReplaceChild("teeth", CubeListBuilder.create().texOffs(43, 7).addBox(-2.5563F, -0.5F, -0.6875F, 4.0F,
				1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0062F, 0.1534F, -3.7263F));

		jaw.addOrReplaceChild("teeth2",
				CubeListBuilder.create().texOffs(32, 6).addBox(-2.5563F, -0.5F, -0.6875F, 4.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0062F, -0.6519F, -3.0819F, 0.2618F, 0.0F, 0.0F));

		PartDefinition leftarm = upperbody.addOrReplaceChild("leftarm",
				CubeListBuilder.create().texOffs(32, 0).addBox(-0.1833F, -1.9447F, -1.4941F, 7.0F, 3.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.3696F, -3.6486F, 0.0235F, 0.2182F, 0.0F, 0.0F));

		PartDefinition leftforearm = leftarm.addOrReplaceChild("leftforearm",
				CubeListBuilder.create().texOffs(28, 21).addBox(-0.1453F, -1.5F, -1.4073F, 7.0F, 3.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.7901F, -0.4447F, -0.0579F, 0.0F, 0.0873F, 0.0F));

		PartDefinition rightarm = upperbody.addOrReplaceChild("rightarm",
				CubeListBuilder.create().texOffs(20, 28).addBox(-6.8167F, -1.9447F, -1.4941F, 7.0F, 3.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.3696F, -3.6486F, 0.0235F, 0.2182F, 0.0F, 0.0F));

		PartDefinition rightforearm = rightarm.addOrReplaceChild("rightforearm",
				CubeListBuilder.create().texOffs(0, 28).addBox(-6.8547F, -1.5F, -1.4073F, 7.0F, 3.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.7901F, -0.4447F, -0.0579F, 0.0F, -0.0873F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
	
	public void animateModel(E ZumbiSangue, float partialTicks) {
		assert IDLE != null;
		float time = ((ZumbiSangue.tickCount + partialTicks) * 0.05F) % IDLE.get().getLength();
		this.endimator.apply(IDLE.get(), time, Endimator.ResetMode.ALL);
		ZumbiSangue.idleEffectHandler.update(IDLE.get(), time);
		Endimator.PosedPart torso = this.endimator.getPosedPart("torso");
		assert torso != null;
		float scale = ZumbiSangue.hurt.getProgress(EndimationEasers.EASE_IN_OUT_SINE, partialTicks);
		torso.applyAdd(part -> part.addScale(0.25F * scale, 0.25F * scale, 0.25F * scale));
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		this.root.render(poseStack, buffer, packedLight, packedOverlay);
	}
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTick, float netHeadYaw,
			float headPitch) {
		this.animateModel(entity, ClientInfo.getPartialTicks());
	}
}