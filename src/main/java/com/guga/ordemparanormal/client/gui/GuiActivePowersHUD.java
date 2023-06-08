package com.guga.ordemparanormal.client.gui;

import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.*;
import com.guga.ordemparanormal.api.util.ResourceUtil;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class GuiActivePowersHUD extends GuiComponent {
    public static final ResourceLocation TEXTURES = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/overlay.png");
    public static final IGuiOverlay OVERLAY = GuiActivePowersHUD::renderOverlay;
    private static final Minecraft minecraft = Minecraft.getInstance();
    public static void renderOverlay(ForgeGui gui, PoseStack ms, float pt, int width,
                                     int height) {
        Player player = minecraft.player;

        IAbilitiesCap abilities = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        INexCap nex = player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
        if (abilities != null && nex != null && !minecraft.options.hideGui){
            int x = 4;
            int y = height - 34;
            for (int i = 0; i < 5; i++){
                PlayerPower power = abilities.getActivePower(i);

                if (power != PlayerPower.EMPTY) {
                    RenderSystem.enableBlend();
                    RenderSystem.disableDepthTest();
                    RenderSystem.depthMask(false);
                    RenderSystem.defaultBlendFunc();

                    RenderSystem.setShaderTexture(0, TEXTURES);
                    if (nex.getPowerCooldown() > 0) RenderSystem.setShaderColor(0.9f, 0.9f, 0.9f, 0.5f);

                    gui.blit(ms, x, y, 0, 74, 30, 30);
                    gui.blit(ms, x + 5, y + 5, 20 * power.getElement().index, power.isActivePower() ? 54 : 34, 20, 20);

                    ResourceLocation icon = new ResourceLocation(power.getId().getNamespace(), "textures/paranormal_power/" + power.getId().getPath() + ".png");
                    if (ResourceUtil.hasResource(minecraft, icon)) {
                        RenderSystem.setShaderTexture(0, icon);
                        gui.blit(ms, x + 7, y + 7, 0, 0, 16, 16, 16, 16);
                    }

                    gui.getFont().draw(ms, String.valueOf(i+1), x + 5, y + 27 - minecraft.font.lineHeight, 0xFFFFFF);

                    x += 32;

                    RenderSystem.depthMask(true);
                    RenderSystem.enableDepthTest();
                    RenderSystem.disableBlend();
                }
            }
        }
    }
}
