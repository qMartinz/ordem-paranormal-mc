package com.guga.ordemparanormal.client.renderer.blockentity;

import com.guga.ordemparanormal.common.block.entities.MesaMaldicaoBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;

public class MesaMaldicaoRenderer implements BlockEntityRenderer<MesaMaldicaoBlockEntity> {
    private final EntityRenderDispatcher entityRenderer;

    public MesaMaldicaoRenderer(BlockEntityRendererProvider.Context pContext) {
        entityRenderer = pContext.getEntityRenderer();
    }

    @Override
    public void render(MesaMaldicaoBlockEntity blockEntity, float pPartialTick, PoseStack matrixStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        float yOffset = 1.3f;
        float xOffset = 0.5f;
        float zOffset = 0.5f;

        matrixStack.pushPose();
        matrixStack.translate(xOffset, yOffset, zOffset);
        matrixStack.scale(0.5f, 0.5f, 0.5f);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees((System.currentTimeMillis() / 10) % 360));
        Minecraft.getInstance().getItemRenderer().renderStatic(blockEntity.getStack(),
                ItemTransforms.TransformType.FIXED,
                pPackedLight,
                pPackedOverlay,
                matrixStack,
                pBufferSource,
                (int) blockEntity.getBlockPos().asLong());

        matrixStack.popPose();
    }
}
