package com.guga.ordemparanormal.client.screen.buttons;

import com.guga.ordemparanormal.client.screen.AttributeScreen;
import com.guga.ordemparanormal.client.screen.powerscreen.BloodPowerScreen;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;

public class PowerScreenButton extends AbstractButton {
    public PowerScreenButton(int x, int y, int width, int height) {
        super(x, y, width, height, CommonComponents.EMPTY);
    }
    @Override
    public void onPress() {
        Minecraft.getInstance().setScreen(new BloodPowerScreen());
    }
    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        if (isMouseOver(pMouseX, pMouseY)){
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.depthMask(false);
            RenderSystem.disableDepthTest();

            RenderSystem.setShaderTexture(0, AttributeScreen.TEXTURES);
            blit(pPoseStack, x, y, 228, 0, 20, 20);

            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(true);
            RenderSystem.disableBlend();
        }
    }
    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
