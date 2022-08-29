package com.guga.ordemparanormal.client.screen;

import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerPowersProvider;
import com.guga.ordemparanormal.client.screen.buttons.AttributeButton;
import com.guga.ordemparanormal.client.screen.widgets.SelectedRitual;
import com.guga.ordemparanormal.common.CommonComponents;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;

import java.util.List;

public class NexScreen extends Screen {
    public static final ResourceLocation TEXTURES = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/nexscreen.png");
    private final int screenWidth = 256;
    private final int screenHeight = 112;
    public final boolean transcending;
    public NexScreen(boolean transcending) {
        super(TextComponent.EMPTY);
        this.transcending = transcending;
    }
    @Override
    protected void init(){
        int screenX = (this.width/2) - (this.screenWidth /2);
        int screenY = (this.height/2) - (this.screenHeight/2);
        addRenderableWidget(new AttributeButton(screenX + 22, screenY + 13, ParanormalAttribute.STRENGTH));
        addRenderableWidget(new AttributeButton(screenX + 76, screenY + 13, ParanormalAttribute.VIGOR));
        addRenderableWidget(new AttributeButton(screenX + 131, screenY + 13, ParanormalAttribute.PRESENCE));
        SelectedRitual ritualWidget = new SelectedRitual(screenX + 177, screenY + 15);
        addRenderableWidget(ritualWidget);
        addWidget(new Button(screenX + 175, screenY + 81, 8, 8, TextComponent.EMPTY, b -> {
            minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerAbilities -> {
                ritualWidget.setRitualIndex(ritualWidget.getRitualIndex() - 1 >= 0 ? ritualWidget.getRitualIndex() - 1 : playerAbilities.getKnownRituals().size() - 1);
            });
        }));
        addWidget(new Button(screenX + 235, screenY + 81, 8, 8, TextComponent.EMPTY, b -> {
            minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerAbilities -> {
                ritualWidget.setRitualIndex(playerAbilities.getKnownRituals().size() - 1 >= ritualWidget.getRitualIndex() + 1 ? ritualWidget.getRitualIndex() + 1 : 0);
            });
        }));
        if (this.transcending) addWidget(new Button(screenX + 119, screenY + 92, 20, 20, TextComponent.EMPTY, b -> {
            minecraft.setScreen(new PowerScreen());
        }));
    }
    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(stack);

        int screenX = (this.width/2) - (this.screenWidth /2);
        int screenY = (this.height/2) - (this.screenHeight/2);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        RenderSystem.disableDepthTest();
        RenderSystem.setShaderTexture(0, TEXTURES);

        RenderSystem.setShaderColor(1f, 1f, 1f, 0.6f);
        if (transcending) blit(stack, screenX + 101, screenY + 72, 0, 112, 55, 63);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

        blit(stack, screenX, screenY, 0, 0, this.screenWidth, this.screenHeight);

        if (transcending) blit(stack, screenX + 123, screenY + 94, 54, 112, 10, 15);

        blit(stack, screenX + 175, screenY + 81, 96, 175, 8, 7);
        blit(stack, screenX + 235, screenY + 81, 96, 182, 8, 7);

        minecraft.player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerAbilities -> {
                String value = playerNex.getNexPercent() + "%";
                String label = CommonComponents.ATTRIBUTE_POINTS.getString();

                font.draw(stack, value, width + 45 - font.width(value)/2f, screenY + 81 - font.lineHeight, 0xFFFFFF);

                value = String.valueOf(playerNex.getAttributePoints());
                font.draw(stack, label, width/2f - font.width(label)/2f, screenY - 2 - font.lineHeight*2, 0xFFFFFF);
                font.draw(stack, value, width/2f - font.width(value)/2f, screenY - 1 - font.lineHeight, 0xFFFFFF);

                value = playerAbilities.getKnownRituals().size() + "/" + playerNex.getRitualSlots();
                font.drawShadow(stack, value, screenX + 183 + 26 - font.width(value)/2f, screenY + 80, 0xFFFFFF);
            });
        });
        super.render(stack, mouseX, mouseY, partialTicks);
        for (Widget widget : this.renderables){
            if (widget instanceof AttributeButton attButton){
                List<Component> lines = attButton.attribute.getDescription();
                if (attButton.isMouseOver(mouseX, mouseY)) renderComponentTooltip(stack,
                        lines,
                        mouseX, mouseY);
            }
            if (widget instanceof SelectedRitual selectedRitual){
                minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerAbilities -> {
                    if (selectedRitual.isMouseOver(mouseX, mouseY) && !playerAbilities.getKnownRituals().isEmpty())
                        renderComponentTooltip(stack,
                            playerAbilities.getKnownRituals().stream().toList().get(selectedRitual.getRitualIndex()).getDescription(),
                            mouseX, mouseY);
                });
            }
        }

        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);
        RenderSystem.disableBlend();
    }
    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
}