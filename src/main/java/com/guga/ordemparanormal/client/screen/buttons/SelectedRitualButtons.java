package com.guga.ordemparanormal.client.screen.buttons;

import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.client.screen.AttributeScreen;
import com.guga.ordemparanormal.client.screen.widgets.SelectedRitual;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.TextComponent;

public class SelectedRitualButtons {
    public static class Forward extends AbstractButton{
        public Forward(int x, int y, int width, int height) {
            super(x, y, width, height, TextComponent.EMPTY);
        }
        @Override
        public void onPress() {
            Minecraft.getInstance().player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities -> {
                SelectedRitual ritualWidget = (SelectedRitual) Minecraft.getInstance().screen.renderables.stream().filter(w -> w instanceof SelectedRitual).findFirst().get();

                ritualWidget.setRitualIndex(playerAbilities.getKnownRituals().size() - 1 >= ritualWidget.getRitualIndex() + 1 ? ritualWidget.getRitualIndex() + 1 : 0);
            });
        }
        @Override
        public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
            Minecraft.getInstance().player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(cap -> {
                if (!cap.getKnownRituals().isEmpty()){
                    if (isMouseOver(pMouseX, pMouseY)) RenderSystem.setShaderColor(1.5f, 1.5f, 1.5f, 1f);
                    RenderSystem.setShaderTexture(0, AttributeScreen.TEXTURES);
                    blit(pPoseStack, x, y, 174, 63, 8, 7);
                    RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
                }
            });
        }
        @Override
        public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

        }
    }
    public static class Backward extends AbstractButton{
        public Backward(int x, int y, int width, int height) {
            super(x, y, width, height, TextComponent.EMPTY);
        }
        @Override
        public void onPress() {
            Minecraft.getInstance().player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities -> {
                SelectedRitual ritualWidget = (SelectedRitual) Minecraft.getInstance().screen.renderables.stream().filter(w -> w instanceof SelectedRitual).findFirst().get();

                ritualWidget.setRitualIndex(ritualWidget.getRitualIndex() - 1 >= 0 ? ritualWidget.getRitualIndex() - 1 : playerAbilities.getKnownRituals().size() - 1);
            });
        }
        @Override
        public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
            Minecraft.getInstance().player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(cap -> {
                if (!cap.getKnownRituals().isEmpty()){
                    if (isMouseOver(pMouseX, pMouseY)) RenderSystem.setShaderColor(1.5f, 1.5f, 1.5f, 1f);
                    RenderSystem.setShaderTexture(0, AttributeScreen.TEXTURES);
                    blit(pPoseStack, x, y, 174, 70, 8, 7);
                    RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
                }
            });
        }
        @Override
        public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

        }
    }
}
