package com.guga.ordemparanormal.client.model;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class BidenteModel extends Model {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OrdemParanormal.MOD_ID, "bidente"), "main");
    private final ModelPart root;

    public BidenteModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.root = root;
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition tridente = partdefinition.addOrReplaceChild("tridente", CubeListBuilder.create().texOffs(4, 0).addBox(-2.4713F, 28.9665F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.9713F, 5.9665F, 0.0F, 1.0F, 24.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 3).addBox(-2.9713F, 4.6915F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 9).addBox(-3.9484F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 7).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4742F, -5.9665F, -0.5F));

        tridente.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(11, 11).addBox(-7.9545F, -12.8166F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4742F, 14.4832F, 0.5F, 0.0F, 0.0F, 0.3927F));

        tridente.addOrReplaceChild("body_r2", CubeListBuilder.create().texOffs(12, 0).addBox(6.9045F, -12.8166F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4742F, 14.4832F, 0.5F, 0.0F, 0.0F, -0.3927F));

        tridente.addOrReplaceChild("body_r3", CubeListBuilder.create().texOffs(11, 5).addBox(-5.3025F, -6.3779F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 6).addBox(-6.9137F, -11.0387F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4742F, 14.4832F, 0.5F, 0.0F, 0.0F, 0.7854F));

        tridente.addOrReplaceChild("body_r4", CubeListBuilder.create().texOffs(4, 12).addBox(4.2565F, -6.3738F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 6).addBox(5.8636F, -11.0387F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4742F, 14.4832F, 0.5F, 0.0F, 0.0F, -0.7854F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
