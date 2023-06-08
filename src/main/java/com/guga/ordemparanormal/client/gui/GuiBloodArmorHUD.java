package com.guga.ordemparanormal.client.gui;

import com.guga.ordemparanormal.api.capabilities.data.ParanormalEffectsProvider;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class GuiBloodArmorHUD extends GuiComponent {
    public static final ResourceLocation TEXTURES = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/overlay.png");
    public static final IGuiOverlay OVERLAY = GuiBloodArmorHUD::renderOverlay;
    private static final Minecraft minecraft = Minecraft.getInstance();
    public static void renderOverlay(ForgeGui gui, PoseStack ms, float pt, int width,
                                     int height) {
        Player player = minecraft.player;
        if (!minecraft.options.hideGui && gui.shouldDrawSurvivalElements()) {
            player.getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).ifPresent(effects -> {
                int left = width / 2 - 91;
                int top = height - gui.leftHeight + 10;

                int level = effects.getBloodArmorPoints();
                for (int i = 1; level > 0 && i < 20; i += 2)
                {
                    RenderSystem.setShaderTexture(0, TEXTURES);
                    if (i < level) {
                        gui.blit(ms, left, top, 98, 9, 9, 9);
                    } else if (i == level) {
                        gui.blit(ms, left, top, 107, 9, 9, 9);
                    }
                    left += 8;
                }
            });
        }
    }
}
