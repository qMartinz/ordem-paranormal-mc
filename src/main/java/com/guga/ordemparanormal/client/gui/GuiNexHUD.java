package com.guga.ordemparanormal.client.gui;

import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.util.MathUtils;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class GuiNexHUD extends GuiComponent {
    public static final ResourceLocation TEXTURES = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/overlay.png");
    public static final IGuiOverlay OVERLAY = GuiNexHUD::renderOverlay;
    private static final Minecraft minecraft = Minecraft.getInstance();
    public static void renderOverlay(ForgeGui gui, PoseStack ms, float pt, int width,
                                     int height) {
        Player player = minecraft.player;
        if (!minecraft.options.hideGui) {
            player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
                RenderSystem.setShaderTexture(0, TEXTURES);

                gui.blit(ms, width - 95, height - 9, 3, 24, 92, 5);

                int nextLvlXP = (playerNex.getNex() + 1) * 50;
                int barFilled = (int) ((playerNex.getNexXp() / nextLvlXP) * 90);
                gui.blit(ms, width - 95, height - 9, 3, 29, barFilled, 5);

                if (playerNex.getAttributePoints() > 0 || playerNex.getPowerPoints() > 0) {
                    RenderSystem.enableBlend();
                    RenderSystem.disableDepthTest();
                    RenderSystem.depthMask(false);
                    RenderSystem.defaultBlendFunc();

                    RenderSystem.setShaderColor(1f, 1f, 1f, MathUtils.Oscillate((int) ((System.currentTimeMillis() / 10) % 200), 1, 100) / 100f);

                    gui.blit(ms, width - 98, height - 26, 0, 0, 98, 24);

                    RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

                    RenderSystem.depthMask(true);
                    RenderSystem.enableDepthTest();
                    RenderSystem.disableBlend();
                }

                String s = playerNex.getNexPercent() + "%";
                gui.getFont().drawShadow(ms, s, width - (gui.getFont().width(s) + 96), height - (gui.getFont().lineHeight + 2), FastColor.ARGB32.color(255, 255, 255, 255));
            });
        }
    }
}
