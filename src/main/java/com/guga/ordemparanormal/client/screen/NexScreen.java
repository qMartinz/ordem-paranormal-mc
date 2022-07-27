package com.guga.ordemparanormal.client.screen;

import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.capabilities.data.PlayerPowersProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.client.screen.buttons.AttributeButton;
import com.guga.ordemparanormal.client.screen.widgets.SelectedRitual;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;

import java.util.ArrayList;
import java.util.List;

public class NexScreen extends Screen {
    public static final ResourceLocation TEXTURES = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/nexscreen.png");
    private final int screenWidth = 171;
    private final int screenHeight = 145;
    public final boolean transcending;
    public NexScreen(boolean transcending) {
        super(TextComponent.EMPTY);
        this.transcending = transcending;
    }
    @Override
    protected void init(){
        int screenX = (this.width/2) - (this.screenWidth /2);
        int screenY = (this.height/2) - (this.screenHeight/2);
        addRenderableWidget(new AttributeButton(screenX + 5, screenY + 5, ParanormalAttribute.STRENGTH));
        addRenderableWidget(new AttributeButton(screenX + 5, screenY + 40, ParanormalAttribute.VIGOR));
        addRenderableWidget(new AttributeButton(screenX + 5, screenY + 75, ParanormalAttribute.PRESENCE));
        SelectedRitual ritualWidget = new SelectedRitual(screenX + 101, screenY + 75);
        addRenderableWidget(ritualWidget);
        addWidget(new Button(screenX + 92, screenY + 98, 8, 8, TextComponent.EMPTY, b -> {
            minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerAbilities -> {
                ritualWidget.setRitualIndex(ritualWidget.getRitualIndex() - 1 >= 0 ? ritualWidget.getRitualIndex() - 1 : playerAbilities.getKnownRituals().size() - 1);
            });
        }));
        addWidget(new Button(screenX + 92, screenY + 108, 8, 8, TextComponent.EMPTY, b -> {
            minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerAbilities -> {
                ritualWidget.setRitualIndex(playerAbilities.getKnownRituals().size() - 1 >= ritualWidget.getRitualIndex() + 1 ? ritualWidget.getRitualIndex() + 1 : 0);
            });
        }));
        addWidget(new Button(screenX - 15, screenY + 5, 18, 20, TextComponent.EMPTY, b -> {
            minecraft.setScreen(new PowerScreen(transcending));
        }));
        if (transcending) addWidget(new Button(screenX - 15, screenY + 28, 18, 20, TextComponent.EMPTY, b -> {

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
        if (transcending) blit(stack, screenX - 15/2 - 55/2, screenY + 10 - 63/2, 0, 177, 55, 63);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

        blit(stack, screenX, screenY, 0, 0, 171, 145);

        blit(stack, screenX - 15, screenY + 5, 96, 145, 18, 20);

        int color = 0x38494f;

        minecraft.player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerAbilities -> {
                String value = playerNex.getNexPercent() + "%";
                String label = new TranslatableComponent("ordemparanormal.nex.attribute_points").getString();

                font.draw(stack, value, width/2f - font.width(value)/2f, screenY - 2 - font.lineHeight, FastColor.ARGB32.color(255, 255, 255, 255));

                value = String.valueOf(playerNex.getAttributePoints());
                font.draw(stack, label, width/2f - font.width(label)/2f, screenY + screenHeight + 2, FastColor.ARGB32.color(255, 200, 200, 255));
                font.draw(stack, value, width/2f - font.width(value)/2f, screenY + screenHeight + 4 + font.lineHeight, FastColor.ARGB32.color(255, 200, 200, 255));

                label = new TranslatableComponent("ordemparanormal.rituals").append(":").getString();
                value = playerAbilities.getKnownRituals().size() + "/" + playerNex.getRitualSlots();
                font.drawShadow(stack, label, screenX + 101, screenY + 64, color);
                font.drawShadow(stack, value, screenX + 166 - font.width(value), screenY + 64, color);

                label = new TranslatableComponent("ordemparanormal.health_points").append(": ").getString();
                value = (int)minecraft.player.getHealth() + "/" + (int)minecraft.player.getMaxHealth();
                font.drawShadow(stack, label + value, screenX + 48 - font.width(label + value)/2f, screenY + 112, color);
                label = new TranslatableComponent("ordemparanormal.effort_points").append(": ").getString();
                value = (int)playerNex.getCurrentEffort() + "/" + (int)playerNex.getMaxEffort();
                font.drawShadow(stack, label + value, screenX + 48 - font.width(label + value)/2f, screenY + 137 - font.lineHeight, color);
            });
        });
        super.render(stack, mouseX, mouseY, partialTicks);
        for (Widget widget : this.renderables){
            if (widget instanceof AttributeButton attButton){
                List<Component> lines = new ArrayList<>();
                for (int i = 1; i < 4; i++){
                    lines.add(new TranslatableComponent(attButton.attribute.name + ".description.line_" + i));
                    if (i == 1) lines.add(TextComponent.EMPTY);
                }
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