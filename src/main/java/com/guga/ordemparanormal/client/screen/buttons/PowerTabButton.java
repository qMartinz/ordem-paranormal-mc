package com.guga.ordemparanormal.client.screen.buttons;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.client.screen.PowerScreen;
import com.guga.ordemparanormal.client.screen.powerscreen.BloodPowerScreen;
import com.guga.ordemparanormal.client.screen.powerscreen.DeathPowerScreen;
import com.guga.ordemparanormal.client.screen.powerscreen.EnergyPowerScreen;
import com.guga.ordemparanormal.client.screen.powerscreen.KnowledgePowerScreen;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;

public class PowerTabButton extends AbstractButton {
    public final ParanormalElement element;
    public final boolean selected;
    public PowerTabButton(int x, int y, int width, int height, ParanormalElement element, boolean selected) {
        super(x, y, width, height, CommonComponents.EMPTY);
        this.element = element;
        this.selected = selected;
    }

    @Override
    public void onPress() {
        PowerScreen screen = switch (element){
            case MORTE -> new DeathPowerScreen();
            case ENERGIA -> new EnergyPowerScreen();
            case CONHECIMENTO -> new KnowledgePowerScreen();
            default -> new BloodPowerScreen();
        };

        Minecraft.getInstance().setScreen(screen);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        RenderSystem.setShaderTexture(0, PowerScreen.TEXTURE);
        blit(pPoseStack, x, y, 14 + 32 * (element.index - 1), selected ? 32 : 0, 32, 32);

        if (isMouseOver(pMouseX, pMouseY) && !selected){
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.depthMask(false);
            RenderSystem.disableDepthTest();

            RenderSystem.setShaderColor(1f, 1f, 1f, 0.5f);
            blit(pPoseStack, x, y, 14 + 32 * (element.index - 1), 32, 32, 32);
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(true);
            RenderSystem.disableBlend();
        }
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
