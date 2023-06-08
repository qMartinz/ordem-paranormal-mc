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
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class GuiEffortHUD extends GuiComponent {
    public static final ResourceLocation TEXTURES = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/overlay.png");
    public static final IGuiOverlay OVERLAY = GuiEffortHUD::renderOverlay;
    private static final Minecraft minecraft = Minecraft.getInstance();
    public static void renderOverlay(ForgeGui gui, PoseStack ms, float pt, int width,
                                     int height) {
        Player player = minecraft.player;
        if (!minecraft.options.hideGui && gui.shouldDrawSurvivalElements()) {
            player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(cap -> {
                int amount = (int) cap.getCurrentEffort();
                int max = (int) cap.getMaxEffort();

                int x = width / 2 + 91;
                int y = height - gui.rightHeight - (minecraft.player.getAirSupply() < 300 ? 9 : 0);
                int i3 = Mth.ceil(max / 10.0F);
                int j2 = Math.max(10 - (i3 - 2), 3);

                for (int i = 0; i < max; i++){
                    int j1 = i / 10;
                    int k1 = i % 10;
                    int l1 = x - k1 * 8 - 10;
                    int i2 = y - j1 * j2;

                    RenderSystem.setShaderTexture(0, TEXTURES);
                    gui.blit(ms, l1, i2, 125, 0, 9, 9);
                }

                for (int i = amount - 1; i >= 0; --i) {
                    int j1 = i / 10;
                    int k1 = i % 10;
                    int l1 = x - k1 * 8 - 10;
                    int i2 = y - j1 * j2;

                    RenderSystem.setShaderTexture(0, TEXTURES);
                    gui.blit(ms, l1, i2, 116, 0, 9, 9);
                }
            });
        }
    }
}
