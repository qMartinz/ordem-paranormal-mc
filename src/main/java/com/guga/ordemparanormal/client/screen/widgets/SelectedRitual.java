package com.guga.ordemparanormal.client.screen.widgets;

import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;

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
        minecraft.player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(abilities -> {
            if (!abilities.getKnownRituals().isEmpty()){
                ResourceLocation symbol = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/ritual_symbol/" + abilities.getKnownRituals().stream().toList().get(ritualIndex).getId() + ".png");

                if (minecraft.getResourceManager().hasResource(symbol)) {
                    RenderSystem.enableBlend();
                    RenderSystem.disableDepthTest();
                    RenderSystem.depthMask(false);
                    RenderSystem.defaultBlendFunc();
                    RenderSystem.setShaderTexture(0, symbol);
                    blit(stack, x + width/2 - 32, y + height/2 - 32, 0, 0, 64, 64, 64, 64);
                    RenderSystem.depthMask(true);
                    RenderSystem.enableDepthTest();
                    RenderSystem.disableBlend();
                }
            }
        });
    }
    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
