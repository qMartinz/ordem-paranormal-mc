package com.guga.ordemparanormal.client;

import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.util.MathUtils;
import com.guga.ordemparanormal.client.screen.NexScreen;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class NexOverlay extends GuiComponent {
    public static final ResourceLocation TEXTURES = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/overlay.png");
    protected final Random random = new Random();
    public final Minecraft minecraft = Minecraft.getInstance();
    private static int showLvlUpTicks = 0;

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        PoseStack poseStack = event.getMatrixStack();

        int width = event.getWindow().getGuiScaledWidth();
        int height = event.getWindow().getGuiScaledHeight();

        minecraft.player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            if (!(minecraft.screen instanceof NexScreen) && !minecraft.player.isCreative()) {
                String s;

                int curEffort = (int) playerNex.getCurrentEffort();
                int maxEffort = (int) playerNex.getMaxEffort();
                String effort = curEffort + " / " + maxEffort;
                minecraft.font.drawShadow(poseStack, effort, 5, height - (minecraft.font.lineHeight + 4), FastColor.ARGB32.color(255, 245, 245, 255));

                RenderSystem.enableBlend();
                RenderSystem.disableDepthTest();
                RenderSystem.depthMask(false);
                RenderSystem.defaultBlendFunc();
                RenderSystem.setShaderTexture(0, TEXTURES);

                blit(poseStack, width - 95, height - 9, 3, 24, 92, 5);

                int nextLvlXP = (playerNex.getNex() + 1) * 50;
                int barFilled = (int) ((playerNex.getNexXp() / nextLvlXP) * 90);
                blit(poseStack, width - 95, height - 9, 3, 29, barFilled, 5);

                s = playerNex.getNexPercent() + "%";
                minecraft.font.drawShadow(poseStack, s, width - (minecraft.font.width(s) + 96), height - (minecraft.font.lineHeight + 2), FastColor.ARGB32.color(255, 255, 255, 255));

                if (showLvlUpTicks > 0) {
                    RenderSystem.setShaderColor(1f, 1f, 1f, MathUtils.Oscillate(showLvlUpTicks, 1, 100) / 100f);
                    blit(poseStack, width - 98, height - 26, 0, 0, 98, 24);
                    RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
                }

                RenderSystem.depthMask(true);
                RenderSystem.enableDepthTest();
                RenderSystem.disableBlend();
            }
        });
    }
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event){
        if (showLvlUpTicks > 0) showLvlUpTicks--;
    }
    public static void showLvLUpNotification(){
        showLvlUpTicks = 200;
    }
}
