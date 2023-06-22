package com.guga.ordemparanormal.client.renderer;

import com.guga.ordemparanormal.common.entity.illagers.PadreDiabo;
import com.guga.ordemparanormal.common.entity.illagers.Sadico;
import com.guga.ordemparanormal.common.entity.illagers.Transtornado;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class IllagerOcultistaRenderers {
    public static class TranstornadoRenderer extends IllagerRenderer<Transtornado>{
        private static final ResourceLocation TEXTURE = OrdemParanormal.REGISTRY_HELPER.prefix("textures/entity/transtornado.png");
        public TranstornadoRenderer(EntityRendererProvider.Context context) {
            super(context, new IllagerModel<>(IllagerModel.createBodyLayer().bakeRoot()), 0.5F);
            this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()) {
                public void render(PoseStack p_116352_, MultiBufferSource p_116353_, int p_116354_, Transtornado p_116355_, float p_116356_, float p_116357_, float p_116358_, float p_116359_, float p_116360_, float p_116361_) {
                    if (p_116355_.isAggressive()) {
                        super.render(p_116352_, p_116353_, p_116354_, p_116355_, p_116356_, p_116357_, p_116358_, p_116359_, p_116360_, p_116361_);
                    }

                }
            });
        }
        @Override
        public ResourceLocation getTextureLocation(Transtornado pEntity) {
            return TEXTURE;
        }
    }
    public static class PadreDiaboRenderer extends IllagerRenderer<PadreDiabo>{
        private static final ResourceLocation TEXTURE = OrdemParanormal.REGISTRY_HELPER.prefix("textures/entity/padre_diabo.png");
        public PadreDiaboRenderer(EntityRendererProvider.Context context) {
            super(context, new IllagerModel<>(IllagerModel.createBodyLayer().bakeRoot()), 0.5F);
            this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()) {
                public void render(PoseStack p_116352_, MultiBufferSource p_116353_, int p_116354_, PadreDiabo p_116355_, float p_116356_, float p_116357_, float p_116358_, float p_116359_, float p_116360_, float p_116361_) {
                    if (p_116355_.isAggressive()) {
                        super.render(p_116352_, p_116353_, p_116354_, p_116355_, p_116356_, p_116357_, p_116358_, p_116359_, p_116360_, p_116361_);
                    }

                }
            });
        }
        @Override
        public ResourceLocation getTextureLocation(PadreDiabo pEntity) {
            return TEXTURE;
        }
    }
    public static class SadicoRenderer extends IllagerRenderer<Sadico>{
        private static final ResourceLocation TEXTURE = OrdemParanormal.REGISTRY_HELPER.prefix("textures/entity/sadico.png");
        public SadicoRenderer(EntityRendererProvider.Context context) {
            super(context, new IllagerModel<>(IllagerModel.createBodyLayer().bakeRoot()), 0.5F);
            this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
        }
        @Override
        public ResourceLocation getTextureLocation(Sadico pEntity) {
            return TEXTURE;
        }
    }
}
