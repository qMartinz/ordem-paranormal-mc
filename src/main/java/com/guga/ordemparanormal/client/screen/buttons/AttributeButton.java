package com.guga.ordemparanormal.client.screen.buttons;

import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.capabilities.data.CapEvents;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.capabilities.network.SyncNexToServer;
import com.guga.ordemparanormal.client.screen.NexScreen;
import com.guga.ordemparanormal.core.network.Messages;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.player.Player;

public class AttributeButton extends AbstractButton {
    private final ParanormalAttribute attribute;
    private final Minecraft minecraft = Minecraft.getInstance();
    private final Player player = minecraft.player;
    public AttributeButton(int pX, int pY, ParanormalAttribute attribute) {
        super(0, 0, 32, 32, TextComponent.EMPTY);
        this.x = pX - width/2;
        this.y = pY - height/2;

        this.attribute = attribute;
    }

    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks){
        RenderSystem.setShaderTexture(0, NexScreen.RESOURCES);

        int level = player.getCapability(PlayerNexProvider.PLAYER_NEX).map(cap -> cap.getAttribute(this.attribute)).get();

        int u = 32 * this.attribute.index;
        blit(stack, x, y, u, 63, width, height);

        int color = FastColor.ARGB32.color(255, 255, 255, 255);
        color = switch (attribute.index) {
            case 0 -> FastColor.ARGB32.color(255, 255, 255, 0);
            case 1 -> FastColor.ARGB32.color(255, 255, 0, 0);
            case 2 -> FastColor.ARGB32.color(255, 0, 0, 255);
            default -> color;
        };

        minecraft.font.drawShadow(stack, Integer.toString(level), (x + width/2f) - minecraft.font.width(Integer.toString(level))/2f, (y - height/2f) - 2, color);

        String text = new TranslatableComponent("ordemparanormal." + attribute.name).getString();
        minecraft.font.drawShadow(stack, text, (x + width/2f) - minecraft.font.width(text)/2f, (y + height) + 3, color);
    }

    @Override
    public void onPress() {
        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            if (playerNex.getAbilityPoints() > 0){
                playerNex.setAttribute(attribute, playerNex.getAttribute(attribute) + 1);
                playerNex.setAbilityPoints(playerNex.getAbilityPoints() - 1);
                Messages.sendToServer(new SyncNexToServer(playerNex.getNexPercent(), playerNex.getNexXp(), playerNex.getAbilityPoints(), playerNex.getMaxEffort(), playerNex.getCurrentEffort(), playerNex.getAttributes()));
            }
        });
    }
    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
