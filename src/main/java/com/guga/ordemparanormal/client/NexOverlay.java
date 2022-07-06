package com.guga.ordemparanormal.client;

import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.util.MathUtils;
import com.guga.ordemparanormal.client.screen.NexScreen;
import com.guga.ordemparanormal.core.registry.OPSounds;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.FastColor;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NexOverlay extends GuiComponent {
    private static int showLvlUpTicks = 0;
    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event){
        Minecraft minecraft = Minecraft.getInstance();
        PoseStack poseStack = event.getMatrixStack();

        int width = event.getWindow().getGuiScaledWidth();
        int height = event.getWindow().getGuiScaledHeight();

        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            minecraft.player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
                if (!(minecraft.screen instanceof NexScreen) && !minecraft.player.isCreative()) {
                    String s = playerNex.getNexPercent() + "%";

                    minecraft.font.drawShadow(poseStack, s, width - (minecraft.font.width(s) + 96), height - (minecraft.font.lineHeight + 2), FastColor.ARGB32.color(255, 255, 255, 255));

                    int curEffort = (int) playerNex.getCurrentEffort();
                    int maxEffort = (int) playerNex.getMaxEffort();
                    String effort = curEffort + " / " + maxEffort;
                    minecraft.font.drawShadow(poseStack, effort, 5, height - (minecraft.font.lineHeight + 4), FastColor.ARGB32.color(255, 245, 245, 255));

                    RenderSystem.enableBlend();
                    RenderSystem.disableDepthTest();
                    RenderSystem.depthMask(false);
                    RenderSystem.defaultBlendFunc();

                    RenderSystem.setShaderTexture(0, NexScreen.RESOURCES);
                    blit(poseStack, width - 95, height - 7, 58, 24, 92, 5);

                    int nextLvlXP = (playerNex.getNexPercent() + 1) * 10;
                    int barsFilled = (playerNex.getNexPercent() % 5) * 18;
                    int total = (int) (barsFilled + (playerNex.getNexXp() / nextLvlXP)*18);
                    blit(poseStack, width - 95, height - 7, 58, 29, total, 5);

                    RenderSystem.depthMask(true);
                    RenderSystem.enableDepthTest();
                    RenderSystem.disableBlend();
                }

                if (showLvlUpTicks > 0) {
                    RenderSystem.enableBlend();
                    RenderSystem.disableDepthTest();
                    RenderSystem.depthMask(false);
                    RenderSystem.defaultBlendFunc();
                    RenderSystem.setShaderColor(1f, 1f, 1f, MathUtils.Oscillate(showLvlUpTicks, 1, 100)/100f);
                    RenderSystem.setShaderTexture(0, NexScreen.RESOURCES);
                    blit(poseStack, width - 98, height - 24, 55, 0, 98, 24);
                    RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
                    RenderSystem.depthMask(true);
                    RenderSystem.enableDepthTest();
                    RenderSystem.disableBlend();
                }
            });
        }
    }
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event){
        if (showLvlUpTicks > 0) showLvlUpTicks--;
    }

    public static void showLvLUpNotification(){
        Minecraft minecraft = Minecraft.getInstance();

        showLvlUpTicks = 200;
        minecraft.player.level.playLocalSound(minecraft.player.getX(), minecraft.player.getY(), minecraft.player.getZ(), OPSounds.RITUAL_LEARNED.get(), SoundSource.AMBIENT, 1f, 1f, false);
    }
}
