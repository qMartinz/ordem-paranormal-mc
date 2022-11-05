package com.guga.ordemparanormal.client.screen.buttons;

import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.util.MathUtils;
import com.guga.ordemparanormal.client.screen.PowerScreen;
import com.guga.ordemparanormal.common.power.Afinidade;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.network.Messages;
import com.guga.ordemparanormal.core.network.Packets;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;

public class PowerButton extends AbstractButton {
    private final PlayerPower power;
    private final Minecraft minecraft = Minecraft.getInstance();
    public PowerButton(int x, int y, PlayerPower power) {
        super(x, y, 20, 20, TextComponent.EMPTY);
        this.power = power;
    }
    public void render(PoseStack stack, int pMouseX, int pMouseY, float pPartialTick) {

        INexCap playerNex = minecraft.player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
        IAbilitiesCap playerAbilities = minecraft.player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (playerNex == null || playerAbilities == null) return;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        RenderSystem.disableDepthTest();

        float alpha = 1f;
        boolean powerRequirement = power.getPowerRequirements().stream().allMatch(playerAbilities::hasPower) || power.getPowerRequirements().isEmpty();
        boolean requirements = playerNex.getNex() >= power.getNexRequired() &&
                playerNex.getAttributes()[0] >= power.getAttributesRequired()[0] &&
                playerNex.getAttributes()[1] >= power.getAttributesRequired()[1] &&
                playerNex.getAttributes()[2] >= power.getAttributesRequired()[2] &&
                powerRequirement;
        if (power instanceof Afinidade && playerAbilities.getPowers().stream().anyMatch(p -> p instanceof Afinidade && p != power)) requirements = false;
        if (!requirements) alpha -= 0.5f;

        RenderSystem.setShaderTexture(0, PowerScreen.TEXTURE);
        if (playerAbilities.hasPower(power)) {
            blit(stack, x - 5, y - 5, 0, 104, 30, 30);

            if (!playerAbilities.getActivePowers().containsValue(this.power)){
                if (this.power.isActivePower() && this.power.canEquip(minecraft.player) && minecraft.screen instanceof PowerScreen pScreen){
                    if (pScreen.selectedSlot < 6) {
                        RenderSystem.setShaderColor(1f, 1f, 1f, 0.5f);
                        blit(stack, x - 4, y - 4, 76, 104, 28, 28);
                    }
                }
            } else {
                blit(stack, x - 4, y - 4, 76, 104, 28, 28);
            }

            if (power instanceof Afinidade) blit(stack, (x + width/2) - 23, (y + height/2) - 24, 30, 104, 46, 44);
        }

        RenderSystem.setShaderColor(1f, 1f, 1f, alpha);

        if (playerAbilities.hasPower(power) && this.power.isActivePower() && this.power.canEquip(minecraft.player) && minecraft.screen instanceof PowerScreen pScreen){
            if (pScreen.selectedSlot < 6) {
                RenderSystem.setShaderColor(1.15f, 1.15f, 1.15f, 1f);
            }
        }

        blit(stack, x, y, 20 * power.getElement().index, power.isActivePower() ? 84 : 64, 20, 20);

        ResourceLocation icon = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/paranormal_power/" + this.power.getId() + ".png");
        if (minecraft.getResourceManager().hasResource(icon)) {
            RenderSystem.setShaderTexture(0, icon);
            blit(stack, x + 2, y + 2, 0, 0, 16, 16, 16, 16);
        }

        if (!playerAbilities.hasPower(power) && power instanceof Afinidade && playerNex.getNex() >= 10 && playerAbilities.getPowers().stream().noneMatch(p -> p instanceof Afinidade && p != power)){
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
        IAbilitiesCap playerAbilities = minecraft.player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (playerNex == null || playerAbilities == null) return;

        boolean powerRequirement = power.getPowerRequirements().stream().allMatch(playerAbilities::hasPower) || power.getPowerRequirements().isEmpty();
        boolean requirements = playerNex.getNex() >= power.getNexRequired() &&
                playerNex.getAttributes()[0] >= power.getAttributesRequired()[0] &&
                playerNex.getAttributes()[1] >= power.getAttributesRequired()[1] &&
                playerNex.getAttributes()[2] >= power.getAttributesRequired()[2] &&
                powerRequirement;
        if (power instanceof Afinidade && playerAbilities.getPowers().stream().anyMatch(p -> p instanceof Afinidade)) requirements = false;
        if (requirements && playerNex.getPowerPoints() > 0 && !playerAbilities.hasPower(power)){
            playerNex.setPowerPoints(playerNex.getPowerPoints() - 1);
            playerAbilities.addPower(power);
            power.onAdded(minecraft.player);
            Messages.sendToServer(new Packets.ReceivePowerTrigger());
            playerAbilities.syncAttributeMods(minecraft.player);
        }

        if (playerAbilities.hasPower(this.power) && this.power.isActivePower() && this.power.canEquip(minecraft.player) && minecraft.screen instanceof PowerScreen pScreen){
            if (pScreen.selectedSlot < 6) {
                playerAbilities.setActivePower(this.power, pScreen.selectedSlot);
                pScreen.selectedSlot = 6;
            }
        }

        Messages.sendToServer(new Packets.UpdatePowers(playerAbilities.serializeNBT()));
        Messages.sendToServer(new Packets.SyncNexToServer(playerNex.serializeNBT()));
    }
    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
