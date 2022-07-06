package com.guga.ordemparanormal.client.screen;

import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.client.screen.buttons.AttributeButton;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;

public class NexScreen extends Screen {
    public static final ResourceLocation RESOURCES = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/nexgui.png");
    public NexScreen() {
        super(TextComponent.EMPTY);
    }

    @Override
    protected void init(){
        addRenderableWidget(new AttributeButton(width/4, height/2, ParanormalAttribute.STRENGTH));
        addRenderableWidget(new AttributeButton(width/2, height/2, ParanormalAttribute.VIGOR));
        addRenderableWidget(new AttributeButton(width - width/4, height/2, ParanormalAttribute.WILL));
    }
    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(stack);

        minecraft.player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            String nex = playerNex.getNexPercent() + "%";
            String points;
            String label;

            font.draw(stack, nex, width/2f - font.width(nex)/2f, 5, FastColor.ARGB32.color(255, 255, 255, 255));

            points = String.valueOf(playerNex.getAbilityPoints());
            label = new TranslatableComponent("ordemparanormal.nex.ability_points").getString();
            font.draw(stack, label, width/2f - font.width(label)/2f, height - (5 + font.lineHeight), FastColor.ARGB32.color(255, 200, 200, 255));
            font.draw(stack, points, width/2f - font.width(points)/2f, height - (8 + font.lineHeight * 2), FastColor.ARGB32.color(255, 200, 200, 255));
        });

        super.render(stack, mouseX, mouseY, partialTicks);
    }
    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
}