package com.guga.ordemparanormal.client.screen.buttons;

import com.guga.ordemparanormal.client.screen.PowerScreen;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class PowerSlotButton extends AbstractButton {
    private final int slot;
    public PowerSlotButton(int x, int y, int width, int height, int slot) {
        super(x, y, width, height, Component.literal("slot_" + slot));
        this.slot = slot;
    }

    @Override
    public void onPress() {
        if (Minecraft.getInstance().screen instanceof PowerScreen pScreen){
            if (pScreen.selectedSlot != slot) {
                pScreen.selectedSlot = slot;
            } else pScreen.selectedSlot = 6;
        }
    }
    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        if (isMouseOver(pMouseX, pMouseY)){
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.depthMask(false);
            RenderSystem.disableDepthTest();

            RenderSystem.setShaderColor(1f, 1f, 1f, 0.5f);
            RenderSystem.setShaderTexture(0, PowerScreen.TEXTURE);
            blit(pPoseStack, x - 2, y - 4, 0, 134, 26, 30);
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
