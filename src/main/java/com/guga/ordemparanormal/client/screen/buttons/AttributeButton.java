package com.guga.ordemparanormal.client.screen.buttons;

import com.guga.ordemparanormal.client.screen.NexScreen;
import com.guga.ordemparanormal.common.abilities.ParanormalAttribute;
import com.guga.ordemparanormal.common.capabilities.nexplayer.NexCapability;
import com.guga.ordemparanormal.common.capabilities.nexplayer.NexModel;
import com.guga.ordemparanormal.common.network.RequestAttrIncrease;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.util.MathUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.FastColor;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class AttributeButton extends AbstractButton {
    private final ParanormalAttribute attribute;
    private final Minecraft minecraft = Minecraft.getInstance();
    public AttributeButton(int pX, int pY, ParanormalAttribute attribute) {
        super(0, 0, 32, 32, TextComponent.EMPTY);
        this.x = pX - width/2;
        this.y = pY - height/2;

        this.attribute = attribute;
    }

    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks){
        RenderSystem.setShaderTexture(0, NexScreen.RESOURCES);

        int level = NexModel.get().getAttribute(attribute);

        int u = 32 * this.attribute.index;
        blit(stack, x, y, u, 63, width, height);

        int color = color = FastColor.ARGB32.color(255, 255, 255, 255);;
        color = switch (attribute.index) {
            case 0 -> FastColor.ARGB32.color(255, 255, 255, 0);
            case 1 -> FastColor.ARGB32.color(255, 255, 0, 0);
            case 2 -> FastColor.ARGB32.color(255, 0, 0, 255);
            default -> color;
        };

        minecraft.font.drawShadow(stack, Integer.toString(level), (x + width/2f) - minecraft.font.width(Integer.toString(level))/2f, (y - height/2) - 2, color);

        if (isMouseOver(mouseX, mouseY) && minecraft.player.getCapability(NexCapability.INSTANCE).isPresent()){
            String text = new TranslatableComponent(attribute.name).getString();
            minecraft.font.drawShadow(stack, text, (x + width/2f) - minecraft.font.width(text)/2f, (y + height) + 3, color);
        }
    }

    @Override
    public void onPress() {
        if (minecraft.player.getCapability(NexCapability.INSTANCE).isPresent()) {
            RequestAttrIncrease.send(attribute);
        }
    }
    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
