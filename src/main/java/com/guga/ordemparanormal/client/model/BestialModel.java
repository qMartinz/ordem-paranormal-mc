package com.guga.ordemparanormal.client.model;

import com.guga.ordemparanormal.common.entity.zumbissangue.Bestial;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.blueprint.core.Blueprint;
import com.teamabnormals.blueprint.core.endimator.Endimation;
import com.teamabnormals.blueprint.core.endimator.Endimator;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class BestialModel<T extends Bestial> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation(OrdemParanormal.MOD_ID, "zumbi_bestial"), "main");
	
	// Animação de caminhada
	private static final Supplier<Endimation> WALKING = () -> Blueprint.ENDIMATION_LOADER.getEndimation(
			new ResourceLocation(OrdemParanormal.MOD_ID, "bestial/walking"));

	private final ModelPart torso;
	private final Endimator endimator;

	// Criar modelo
	public BestialModel() {
		this.endimator = Endimator.compile(this.torso = createBodyLayer().bakeRoot());
	}

	// Definição de todas as partes do modelo
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition torso = partdefinition.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 32)
				.addBox(-3.5F, -2.5F, -3.5F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-0.3617F, 8.25F, 6.2774F));

		PartDefinition upperbody = torso.addOrReplaceChild("upperbody",
				CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -1.9786F, -11.6161F, 11.0F, 5.0F, 12.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.5794F, -2.9724F, 0.3054F, 0.0F, 0.0F));

		PartDefinition upperbody2 = upperbody.addOrReplaceChild("upperbody2", CubeListBuilder.create(),
				PartPose.offset(0.3617F, 12.0214F, -3.3935F));

		upperbody2.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(0, 17).addBox(-4.5F, -2.2254F, -5.5644F, 10.0F, 4.0F, 11.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.8617F, -8.7801F, -2.161F, -0.2182F, 0.0F, 0.0F));

		PartDefinition rightarm = upperbody.addOrReplaceChild("rightarm",
				CubeListBuilder.create().texOffs(48, 44).addBox(-3.0853F, -1.337F, -1.7564F, 4.0F, 8.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.3765F, -0.3039F, -8.8597F, 0.2565F, -0.5322F, 0.1905F));

		rightarm.addOrReplaceChild("rightforearm",
				CubeListBuilder.create().texOffs(16, 44).addBox(-2.0853F, 0.0881F, -2.0792F, 4.0F, 9.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 6.5497F, 0.3557F, -0.8801F, -0.1382F, 0.1069F));

		PartDefinition leftarm = upperbody.addOrReplaceChild("leftarm",
				CubeListBuilder.create().texOffs(48, 44).mirror()
						.addBox(-0.4147F, -1.337F, -1.7564F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(5.5999F, -0.3039F, -8.8597F, 0.2565F, 0.5322F, -0.1905F));

		leftarm.addOrReplaceChild("leftforearm",
				CubeListBuilder.create().texOffs(16, 44).mirror()
						.addBox(-1.9147F, 0.0881F, -2.0792F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(1.5F, 6.5497F, 0.3557F, -0.8801F, 0.1382F, -0.1069F));

		PartDefinition head = upperbody.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(31, 17).addBox(-3.0F, -2.25F, -6.0F, 6.0F, 3.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 1.7714F, -11.6161F, 0.0436F, 0.0F, 0.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0.0F, 1.1262F, -0.2888F, -0.3927F, 0.0F, 0.0F));

		jaw.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(34, 0).addBox(-3.0F, 0.25F, -2.75F, 6.0F, 2.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 1.2019F, -2.6167F, 0.6981F, 0.0F, 0.0F));

		jaw.addOrReplaceChild("teeth2",
				CubeListBuilder.create().texOffs(56, 37).addBox(-2.0F, -0.6686F, -0.8999F, 5.0F, 1.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 2.5F, -3.5F, 0.6981F, 0.0F, 0.0F));

		head.addOrReplaceChild("teeth", CubeListBuilder.create().texOffs(52, 0).addBox(-2.3617F,
				1.2183F, -5.6812F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-0.1383F, -0.75F, 0.2226F));

		PartDefinition lowerbody = torso.addOrReplaceChild("lowerbody",
				CubeListBuilder.create().texOffs(28, 32).addBox(-4.0F, -2.5397F, -0.4982F, 8.0F, 6.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.2135F, 3.1268F, -0.2618F, 0.0F, 0.0F));

		PartDefinition leftleg = lowerbody.addOrReplaceChild("leftleg", CubeListBuilder.create(),
				PartPose.offsetAndRotation(4.0234F, 0.2905F, 3.051F, 0.0257F, 0.4205F, 0.1201F));

		leftleg.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(28, 56).addBox(-2.5F, -4.4995F, -2.4782F, 4.0F, 8.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.75F, 2.5765F, 1.3772F, 0.3054F, 0.0F, 0.0F));

		PartDefinition leftlowerleg = leftleg.addOrReplaceChild("leftlowerleg", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0.25F, 5.4685F, 1.657F, -0.8735F, -0.0596F, 0.0021F));

		leftlowerleg.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(0, 57).addBox(-2.4F, -0.6082F, 0.8881F, 4.0F, 9.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 2.5417F, 1.3382F, 1.6144F, 0.0F, 0.0F));

		PartDefinition rightleg = lowerbody.addOrReplaceChild("rightleg", CubeListBuilder.create(),
				PartPose.offsetAndRotation(-4.05F, 0.2905F, 3.051F, 0.0257F, -0.4205F, -0.1201F));

		rightleg.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(28, 56).mirror()
						.addBox(-1.5F, -4.4995F, -2.4782F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-0.75F, 2.5765F, 1.3772F, 0.3054F, 0.0F, 0.0F));

		PartDefinition rightlowerleg = rightleg.addOrReplaceChild("rightlowerleg", CubeListBuilder.create(),
				PartPose.offsetAndRotation(-0.25F, 5.4685F, 1.657F, -0.8735F, 0.0596F, -0.0021F));

		rightlowerleg.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(0, 57).mirror()
						.addBox(-1.6F, -0.6082F, 0.8881F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-0.5F, 2.5417F, 1.3382F, 1.6144F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 80, 80);
	}
	
	// Calcular tempo de caminhada
	private static float computeWalkTime(float limbSwing, float length) {
		float period = length * 5.0F;
		return (((limbSwing + period) % period) / period) * length;
	}

	// Animar
	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		assert WALKING.get() != null;
		float length = WALKING.get().getLength();
		float adjustedLimbSwingAmount = 4.0F * limbSwingAmount / length;
		if (adjustedLimbSwingAmount > 1.0F) {
			adjustedLimbSwingAmount = 1.0F;
		}
		this.endimator.apply(WALKING.get(), computeWalkTime(limbSwing, length), adjustedLimbSwingAmount, Endimator.ResetMode.ALL);
	}

	// Renderizar
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		torso.render(poseStack, buffer, packedLight, packedOverlay);
	}
}