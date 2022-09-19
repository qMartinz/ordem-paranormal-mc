package com.guga.ordemparanormal.client;

import com.guga.ordemparanormal.api.capabilities.data.ParanormalEffectsProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.util.MathUtils;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Overlay extends GuiComponent {
    public static final ResourceLocation TEXTURES = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/overlay.png");
    private static int showLvlUpTicks = 0;
    public static final IIngameOverlay HUD_NEX = (gui, poseStack, partialTicks, width, height) -> {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (!minecraft.options.hideGui && gui.shouldDrawSurvivalElements()) {
            player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
                RenderSystem.setShaderTexture(0, TEXTURES);

                gui.blit(poseStack, width - 95, height - 9, 3, 24, 92, 5);

                int nextLvlXP = (playerNex.getNex() + 1) * 50;
                int barFilled = (int) ((playerNex.getNexXp() / nextLvlXP) * 90);
                gui.blit(poseStack, width - 95, height - 9, 3, 29, barFilled, 5);

                String s = playerNex.getNexPercent() + "%";
                gui.getFont().drawShadow(poseStack, s, width - (gui.getFont().width(s) + 96), height - (gui.getFont().lineHeight + 2), FastColor.ARGB32.color(255, 255, 255, 255));
            });
        }
    };
    public static final IIngameOverlay HUD_EFFORT = (gui, poseStack, partialTicks, width, height) -> {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (!minecraft.options.hideGui && gui.shouldDrawSurvivalElements()) {
            player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(cap -> {
                int amount = (int) cap.getCurrentEffort();
                int max = (int) cap.getMaxEffort();

                int x = width / 2 + 91;
                int y = height - gui.right_height;
                int i3 = Mth.ceil(max / 10.0F);
                int j2 = Math.max(10 - (i3 - 2), 3);

                for (int i = 0; i < max; i++){
                    int j1 = i / 10;
                    int k1 = i % 10;
                    int l1 = x - k1 * 8 - 10;
                    int i2 = y - j1 * j2;

                    RenderSystem.setShaderTexture(0, TEXTURES);
                    gui.blit(poseStack, l1, i2, 125, 0, 9, 9);
                }

                for (int i = amount - 1; i >= 0; --i) {
                    int j1 = i / 10;
                    int k1 = i % 10;
                    int l1 = x - k1 * 8 - 10;
                    int i2 = y - j1 * j2;

                    RenderSystem.setShaderTexture(0, TEXTURES);
                    gui.blit(poseStack, l1, i2, 116, 0, 9, 9);
                }
            });
        }
    };
    public static final IIngameOverlay HUD_DEATH_HEARTS = (gui, poseStack, partialTicks, width, height) -> {
        Minecraft minecraft = Minecraft.getInstance();
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
                    gui.blit(poseStack, l1, i2, half ? 107 : 98, 0, 9, 9);
                }
            });
        }
    };
    public static final IIngameOverlay HUD_BLOOD_ARMOR = (gui, poseStack, partialTick, width, height) -> {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (!minecraft.options.hideGui && gui.shouldDrawSurvivalElements()) {
            player.getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).ifPresent(effects -> {
                int left = width / 2 - 91;
                int top = height - gui.left_height + 10;

                int level = effects.getBloodArmorPoints();
                for (int i = 1; level > 0 && i < 20; i += 2)
                {
                    RenderSystem.setShaderTexture(0, TEXTURES);
                    if (i < level) {
                        gui.blit(poseStack, left, top, 98, 9, 9, 9);
                    } else if (i == level) {
                        gui.blit(poseStack, left, top, 107, 9, 9, 9);
                    }
                    left += 8;
                }
            });
        }
    };
    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event){
        if (showLvlUpTicks > 0) {
            RenderSystem.enableBlend();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.defaultBlendFunc();

            RenderSystem.setShaderTexture(0, TEXTURES);
            RenderSystem.setShaderColor(1f, 1f, 1f, MathUtils.Oscillate(showLvlUpTicks, 1, 100) / 100f);

            blit(event.getMatrixStack(), event.getWindow().getGuiScaledWidth() - 98, event.getWindow().getGuiScaledHeight() - 26, 0, 0, 98, 24);

            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.disableBlend();
        }
    }
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event){
        if (showLvlUpTicks > 0) showLvlUpTicks--;
    }
    public static void showLvLUpNotification(){
        showLvlUpTicks = 200;
    }
    public static void registerOverlays(){
        OverlayRegistry.registerOverlayTop("NEX", Overlay.HUD_NEX);
        OverlayRegistry.registerOverlayAbove(ForgeIngameGui.FOOD_LEVEL_ELEMENT, "Effort Points", Overlay.HUD_EFFORT);
        OverlayRegistry.registerOverlayAbove(ForgeIngameGui.PLAYER_HEALTH_ELEMENT, "Death Health", Overlay.HUD_DEATH_HEARTS);
        OverlayRegistry.registerOverlayAbove(ForgeIngameGui.ARMOR_LEVEL_ELEMENT, "Blood Armor", Overlay.HUD_BLOOD_ARMOR);
    }
}
