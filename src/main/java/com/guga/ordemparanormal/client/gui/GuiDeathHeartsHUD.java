package com.guga.ordemparanormal.client.gui;

import com.guga.ordemparanormal.api.capabilities.data.ParanormalEffectsProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
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

public class GuiDeathHeartsHUD extends GuiComponent {
    public static final ResourceLocation TEXTURES = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/overlay.png");
    public static final IGuiOverlay OVERLAY = GuiDeathHeartsHUD::renderOverlay;
    private static final Minecraft minecraft = Minecraft.getInstance();
    public static void renderOverlay(ForgeGui gui, PoseStack ms, float pt, int width,
                                     int height) {
        Player player = minecraft.player;
        if (!minecraft.options.hideGui && gui.shouldDrawSurvivalElements()) {
            player.getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).ifPresent(cap -> {
                int amount = cap.getDeathHealthPoints();
                int max = (int) player.getMaxHealth();
                int hearts = Mth.ceil(amount / 2D);
                int maxHearts = Mth.ceil(max / 2D);

                int x = width / 2 - 91;
                int y = height - 39;

                int i3 = Mth.ceil(max / 2.0F / 10.0F);
                int j2 = Math.max(10 - (i3 - 2), 3);
                int j4 = -1;
                if (player.hasEffect(MobEffects.REGENERATION)) {
                    j4 = gui.getGuiTicks() % Mth.ceil(max + 5.0F);
                }

                for (int i1 = hearts - 1; i1 >= 0; --i1) {
                    int j1 = i1 / 10;
                    int k1 = i1 % 10;
                    int l1 = x + k1 * 8;
                    int i2 = y - j1 * j2;
                    int j3 = i1 * 2;
                    boolean half = j3 + 1 == amount;

                    if (i1 < maxHearts && i1 == j4) {
                        i2 -= 2;
                    }

                    RenderSystem.setShaderTexture(0, TEXTURES);
                    gui.blit(ms, l1, i2, half ? 107 : 98, 0, 9, 9);
                }
            });
        }
    }
}
