package com.guga.ordemparanormal.client.screen.buttons;

import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.IPowerCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerPowersProvider;
import com.guga.ordemparanormal.api.capabilities.network.SyncNexToServer;
import com.guga.ordemparanormal.api.capabilities.network.UpdatePowers;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.api.util.MathUtils;
import com.guga.ordemparanormal.client.screen.PowerScreen;
import com.guga.ordemparanormal.common.power.Afinidade;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.network.Messages;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class PowerButton extends AbstractButton {
    private final PlayerPower power;
    private final Minecraft minecraft = Minecraft.getInstance();
    public PowerButton(int x, int y, PlayerPower power) {
        super(x, y, 20, 20, TextComponent.EMPTY);
        this.power = power;
    }
    public void render(PoseStack stack, int pMouseX, int pMouseY, float pPartialTick) {

        INexCap playerNex = minecraft.player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
        IPowerCap playerPowers = minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).orElse(null);
        if (playerNex == null || playerPowers == null) return;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        RenderSystem.disableDepthTest();

        float alpha = 1f;
        boolean powerRequirement = power.getPowerRequirements().stream().allMatch(playerPowers::hasPower) || power.getPowerRequirements().isEmpty();
        boolean requirements = playerNex.getNex() >= power.getNexRequired() &&
                playerNex.getAttributes()[0] >= power.getAttributesRequired()[0] &&
                playerNex.getAttributes()[1] >= power.getAttributesRequired()[1] &&
                playerNex.getAttributes()[2] >= power.getAttributesRequired()[2] &&
                powerRequirement;
        if (power instanceof Afinidade && playerPowers.getPowers().stream().anyMatch(p -> p instanceof Afinidade && p != power)) requirements = false;
        if (!requirements) alpha -= 0.5f;

        RenderSystem.setShaderTexture(0, PowerScreen.TEXTURE);
        if (playerPowers.hasPower(power)) {
            blit(stack, x - 5, y - 5, 0, 104, 30, 30);
            if (power instanceof Afinidade) blit(stack, (x + width/2) - 23, (y + height/2) - 24, 30, 104, 46, 44);
        }

        RenderSystem.setShaderColor(1f, 1f, 1f, alpha);

        blit(stack, x, y, 20 * power.getElement().index, power.isActivePower() ? 84 : 64, 20, 20);

        ResourceLocation icon = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/paranormal_power/" + this.power.getId() + ".png");
        if (minecraft.getResourceManager().hasResource(icon)) {
            RenderSystem.setShaderTexture(0, icon);
            blit(stack, x + 2, y + 2, 0, 0, 16, 16, 16, 16);
        }

        if (!playerPowers.hasPower(power) && power instanceof Afinidade && playerNex.getNex() >= 10 && playerPowers.getPowers().stream().noneMatch(p -> p instanceof Afinidade && p != power)){
            RenderSystem.setShaderColor(1f, 1f, 1f, MathUtils.Oscillate((int) ((System.currentTimeMillis()/10)%200), 1, 100)/100f);
            RenderSystem.setShaderTexture(0, PowerScreen.TEXTURE);
            blit(stack, (x + width/2) - 23, (y + height/2) - 24, 30, 104, 46, 44);
        }

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);
        RenderSystem.disableBlend();
    }
    public PlayerPower getPower() {
        return power;
    }
    @Override
    public void onPress() {
        INexCap playerNex = minecraft.player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
        IPowerCap playerPowers = minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).orElse(null);
        if (playerNex == null || playerPowers == null) return;

        boolean powerRequirement = power.getPowerRequirements().stream().allMatch(playerPowers::hasPower) || power.getPowerRequirements().isEmpty();
        boolean requirements = playerNex.getNex() >= power.getNexRequired() &&
                playerNex.getAttributes()[0] >= power.getAttributesRequired()[0] &&
                playerNex.getAttributes()[1] >= power.getAttributesRequired()[1] &&
                playerNex.getAttributes()[2] >= power.getAttributesRequired()[2] &&
                powerRequirement;
        if (power instanceof Afinidade && playerPowers.getPowers().stream().anyMatch(p -> p instanceof Afinidade)) requirements = false;
        if (requirements && playerNex.getPowerPoints() > 0 && !playerPowers.hasPower(power)){
            playerNex.setPowerPoints(playerNex.getPowerPoints() - 1);
            playerPowers.addPower(power);
        }

        if (playerPowers.hasPower(this.power) && this.power.isActivePower() && this.power.canEquip(minecraft.player) && minecraft.screen instanceof PowerScreen pScreen){
            if (pScreen.selectedSlot < 6) {
                playerPowers.setActivePower(this.power, pScreen.selectedSlot);
                pScreen.selectedSlot = 6;
            }
        }

        Messages.sendToServer(new UpdatePowers(playerPowers.serializeNBT()));
        Messages.sendToServer(new SyncNexToServer(playerNex.serializeNBT()));
    }
    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
