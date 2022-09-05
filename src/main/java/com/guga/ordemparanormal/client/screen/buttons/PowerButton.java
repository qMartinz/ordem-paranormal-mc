package com.guga.ordemparanormal.client.screen.buttons;

import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.IPowerCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerPowersProvider;
import com.guga.ordemparanormal.api.capabilities.network.SyncNexToServer;
import com.guga.ordemparanormal.api.capabilities.network.UpdatePowers;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.api.util.PowerUtils;
import com.guga.ordemparanormal.client.PowerIcon;
import com.guga.ordemparanormal.client.screen.powerscreen.PowerScreen;
import com.guga.ordemparanormal.core.network.Messages;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.TextComponent;

public class PowerButton extends AbstractButton {
    private final PlayerPower power;
    private final Minecraft minecraft = Minecraft.getInstance();
    private final int firstX;
    private final int firstY;
    public PowerButton(int x, int y, PlayerPower power) {
        super(x, y, 16, 16, TextComponent.EMPTY);
        this.power = power;

        this.firstX = x;
        this.firstY = y;
    }
    public void render(PoseStack stack, int pMouseX, int pMouseY, float pPartialTick, double xOffset, double yOffset) {
        this.x = (int) (firstX + xOffset);
        this.y = (int) (firstY + yOffset);

        INexCap playerNex = minecraft.player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
        IPowerCap playerPowers = minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).orElse(null);
        if (playerNex == null || playerPowers == null) return;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        RenderSystem.disableDepthTest();

        float alpha = 1f;
        visible = true;
        boolean powerRequirement = playerPowers.hasPower(power.getPowerRequirement()) || power.getPowerRequirement() == PlayerPower.EMPTY;
        boolean requirements = playerNex.getNex() >= power.getNexRequired() &&
                playerNex.getAttributes()[0] >= power.getAttributesRequired()[0] &&
                playerNex.getAttributes()[1] >= power.getAttributesRequired()[1] &&
                playerNex.getAttributes()[2] >= power.getAttributesRequired()[2] &&
                powerRequirement;
        if (!requirements) alpha -= 0.5f;
        if (!playerPowers.hasPower(power.getPowerRequirement().getPowerRequirement()) &&
                power.getPowerRequirement().getPowerRequirement() != PlayerPower.EMPTY) visible = false;

        if (this.visible) {
            RenderSystem.setShaderTexture(0, PowerScreen.TEXTURE);
            if (playerPowers.hasPower(power)) blit(stack, x - 5, y - 5, 0, 104, 30, 30);

            RenderSystem.setShaderColor(1f, 1f, 1f, alpha);

            blit(stack, x, y, 20 * power.getElement().index, power.isActivePower() ? 84 : 64, 20, 20);

            PowerIcon icon = PowerUtils.getIcon(power);
            if (icon != null) {
                RenderSystem.setShaderTexture(0, icon.getTextureLoc());
                blit(stack, x + 2, y + 2, icon.getX(), icon.getY(), 16, 16);
                RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
            }
        }

        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);
        RenderSystem.disableBlend();
    }
    public PlayerPower getPower() {
        return power;
    }

    @Override
    public void playDownSound(SoundManager pHandler) {
        if (this.visible) super.playDownSound(pHandler);
    }
    @Override
    public void onPress() {
        INexCap playerNex = minecraft.player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
        IPowerCap playerPowers = minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).orElse(null);
        if (playerNex == null || playerPowers == null) return;
        boolean powerRequirement = playerPowers.hasPower(power.getPowerRequirement()) || power.getPowerRequirement() == PlayerPower.EMPTY;
        boolean requirements = playerNex.getNex() >= power.getNexRequired() &&
                playerNex.getAttributes()[0] >= power.getAttributesRequired()[0] &&
                playerNex.getAttributes()[1] >= power.getAttributesRequired()[1] &&
                playerNex.getAttributes()[2] >= power.getAttributesRequired()[2] &&
                powerRequirement;
        if (requirements && playerNex.getPowerPoints() > 0 && !playerPowers.hasPower(power)){
            playerNex.setPowerPoints(playerNex.getPowerPoints() - 1);
            playerPowers.addPower(power);
            Messages.sendToServer(new UpdatePowers(playerPowers.serializeNBT()));
            Messages.sendToServer(new SyncNexToServer(playerNex.serializeNBT()));
        }
    }
    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
