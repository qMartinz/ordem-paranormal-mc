package com.guga.ordemparanormal.client.screen.buttons;

import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.client.screen.AttributeScreen;
import com.guga.ordemparanormal.core.network.Messages;
import com.guga.ordemparanormal.core.network.Packets;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.world.entity.player.Player;

public class AttributeButton extends AbstractButton {
    public final ParanormalAttribute attribute;
    private final Minecraft minecraft = Minecraft.getInstance();
    private final Player player = minecraft.player;
    public AttributeButton(int pX, int pY, ParanormalAttribute attribute) {
        super(pX, pY, 32, 32, CommonComponents.EMPTY);

        this.attribute = attribute;
    }

    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks){
        RenderSystem.setShaderTexture(0, AttributeScreen.TEXTURES);

        int u = 32 * this.attribute.index;
        if (isMouseOver(mouseX, mouseY)) RenderSystem.setShaderColor(1.4f, 1.4f, 1.4f, 1f);
        blit(stack, x, y, u, 217, width, height);

        int color = 0xFFFFFF;

        int level = player.getCapability(PlayerNexProvider.PLAYER_NEX).map(cap -> cap.getAttribute(this.attribute)).orElseThrow();
        minecraft.font.drawShadow(stack, Integer.toString(level), x + 16 - minecraft.font.width(Integer.toString(level))/2f, y + height + 1, color);

        String text = attribute.getDisplayName().getString();
        minecraft.font.drawShadow(stack, text, x + 16 - minecraft.font.width(text)/2f, y + height + 2 + minecraft.font.lineHeight, color);
    }
    @Override
    public void onPress() {
        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            if (playerNex.getAttributePoints() > 0){
                playerNex.setAttribute(attribute, playerNex.getAttribute(attribute) + 1);
                playerNex.setAttributePoints(playerNex.getAttributePoints() - 1);
                playerNex.syncAttributeMods(player);
                Messages.sendToServer(new Packets.SyncNexToServer(playerNex.serializeNBT()));
            }
        });
    }
    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
