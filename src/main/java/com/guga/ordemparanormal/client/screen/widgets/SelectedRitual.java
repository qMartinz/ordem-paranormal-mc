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
                ResourceLocation symbol = new ResourceLocation(OrdemParanormal.MOD_ID,
                        "textures/ritual_symbol/" + abilities.getKnownRituals().stream().toList().get(ritualIndex).getId() + ".png");

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

                    fill(stack, x + width/2 - 9/2, y + height - 9, x + width/2 + 9/2, y + height, 0xFF602c2c);
                    fill(stack, x + width/2 - 9/2 - 1, y + height - 11, x + width/2 - 9/2, y + height + 2, 0xFFde9e41);
                    fill(stack, x + width/2 + 9/2 + 1, y + height - 11, x + width/2 + 9/2, y + height + 2, 0xFFde9e41);
                    fill(stack, x + width/2 - 9/2 - 2, y + height - 10, x + width/2 + 9/2 + 2, y + height - 9, 0xFFde9e41);
                    fill(stack, x + width/2 - 9/2 - 2, y + height + 1, x + width/2 + 9/2 + 2, y + height, 0xFFde9e41);

                    minecraft.font.draw(stack, String.valueOf(getRitualIndex() + 1),
                            x + width/2f - minecraft.font.width(String.valueOf(getRitualIndex() + 1))/2f + 1, y + height - minecraft.font.lineHeight + 1,
                            0xFFde9e41);
                }
            }
        });
    }
    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
