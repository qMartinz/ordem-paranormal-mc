package com.guga.ordemparanormal.client.screen.powerscreen;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.client.screen.buttons.PowerButton;
import com.guga.ordemparanormal.core.registry.OPPowers;
import com.mojang.blaze3d.vertex.PoseStack;

public class BloodPowerScreen extends PowerScreen{
    public BloodPowerScreen() {
        super(ParanormalElement.SANGUE);
    }
    @Override
    public void initContents() {
        int tabWidth = this.width - 41;
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 - 10, OPPowers.TEST_POWER));
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 + 30, OPPowers.TEST_POWER_2));
    }

    @Override
    public void renderContents(PoseStack stack, int pMouseX, int pMouseY, float pPartialTick) {
        int tabWidth = this.width - 41;

        fill(stack,
                (int) (tabWidth/2 + 36 + xOffset), (int) (height/2 + 10 + yOffset),
                (int) (tabWidth/2 + 38 + xOffset), (int) (height/2 + 30 + yOffset),
                0xFFbf782c);
    }
}
