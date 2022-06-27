package com.guga.ordemparanormal.client.screen;

import com.guga.ordemparanormal.client.screen.buttons.AttributeButton;
import com.guga.ordemparanormal.common.abilities.ParanormalAttribute;
import com.guga.ordemparanormal.common.capabilities.nexplayer.NexCapability;
import com.guga.ordemparanormal.common.capabilities.nexplayer.NexModel;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;

public class NexScreen extends Screen {
    public static final ResourceLocation RESOURCES = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/nexgui.png");
    public NexScreen() {
        super(new TranslatableComponent("container.nex"));
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

        if(minecraft.player.getCapability(NexCapability.INSTANCE).isPresent()) {
            NexModel nexModel = NexModel.get(minecraft.player);
            String nex = nexModel.getNexLevel() + "%";
            String points;
            String label;

            font.draw(stack, nex, (width - font.width(nex)) / 2f, 5, FastColor.ARGB32.color(255, 255, 255, 255));

            points = String.valueOf(nexModel.getAbilityPoints());
            label = new TranslatableComponent("nex.ability_points").getString();
            font.draw(stack, label, (width - font.width(label)) / 2f, height - (5 + font.lineHeight), FastColor.ARGB32.color(255, 200, 200, 255));
            font.draw(stack, points, (width - font.width(points)) / 2f, height - (8 + font.lineHeight * 2), FastColor.ARGB32.color(255, 200, 200, 255));
        }
        super.render(stack, mouseX, mouseY, partialTicks);
    }
    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
}