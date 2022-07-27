package com.guga.ordemparanormal.client.screen.widgets;

import com.guga.ordemparanormal.api.capabilities.data.PlayerPowersProvider;
import com.guga.ordemparanormal.api.util.PowerUtils;
import com.guga.ordemparanormal.client.RitualSymbol;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.TextComponent;

public class SelectedRitual extends AbstractWidget {
    private int ritualIndex = 0;
    public SelectedRitual(int pX, int pY) {
        super(pX, pY, 64, 64, TextComponent.EMPTY);
    }
    public void setRitualIndex(int ritualIndex) {
        this.ritualIndex = ritualIndex;
    }
    public int getRitualIndex() {
        return ritualIndex;
    }
    @Override
    public void render(PoseStack stack, int pMouseX, int pMouseY, float pPartialTick) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerAbilities -> {
            if (!playerAbilities.getKnownRituals().isEmpty()){
                RitualSymbol currentRitual = PowerUtils.getSymbol(playerAbilities.getKnownRituals().stream().toList().get(ritualIndex));
                RenderSystem.enableBlend();
                RenderSystem.disableDepthTest();
                RenderSystem.depthMask(false);
                RenderSystem.defaultBlendFunc();
                RenderSystem.setShaderColor(1f, 1f, 1f, 0.7f);
                RenderSystem.setShaderTexture(0, currentRitual.getTextureLoc());
                blit(stack, x + width/2 - currentRitual.getWidth()/2, y + height/2 - currentRitual.getHeight()/2, currentRitual.getX(), currentRitual.getY(), currentRitual.getWidth(), currentRitual.getHeight());
                RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
                RenderSystem.depthMask(true);
                RenderSystem.enableDepthTest();
                RenderSystem.disableBlend();
            }
        });
    }
    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
