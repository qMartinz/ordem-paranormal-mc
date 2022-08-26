package com.guga.ordemparanormal.client.screen;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerPowersProvider;
import com.guga.ordemparanormal.api.capabilities.network.UpdatePowers;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.api.powers.power.PowerType;
import com.guga.ordemparanormal.api.util.PowerUtils;
import com.guga.ordemparanormal.client.PowerIcon;
import com.guga.ordemparanormal.client.screen.buttons.InvisibleButton;
import com.guga.ordemparanormal.client.screen.buttons.PowerButton;
import com.guga.ordemparanormal.client.screen.buttons.SlotButton;
import com.guga.ordemparanormal.common.CommonComponents;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.network.Messages;
import com.guga.ordemparanormal.core.registry.OPPowers;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PowerScreen extends Screen {
    private static final ResourceLocation TEXTURE = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/powerscreen.png");
    private final int screenWidth = 230;
    private final int screenHeight = 153;
    private boolean transcending;
    private int selectedSlot = 0;
    protected PowerScreen(boolean transcending) {
        super(TextComponent.EMPTY);
        this.transcending = transcending;
    }
    @Override
    protected void init() {
        int screenX = (this.width/2) - (this.screenWidth /2);
        int screenY = (this.height/2) - (this.screenHeight/2);

        addWidget(new Button(screenX - 15, screenY + 19, 18, 20, TextComponent.EMPTY, b -> {
            minecraft.setScreen(new NexScreen(transcending));
        }));

        for (int i = 0; i < 5; i++){
            int finalI = i;
            minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerPowers -> {
                PlayerPower power = playerPowers.getActivePower(finalI);
                addRenderableWidget(new SlotButton(screenX + 61 + (22*finalI), screenY + 6, 20, 20, new TextComponent("slot_" + finalI), b -> {
                    if (selectedSlot != finalI + 1){
                        selectedSlot = finalI + 1;
                    } else {
                        selectedSlot = 0;
                    }
                }, power));
            });
        }

        minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerPowers -> {
            for (int i = 0; i < 10; i++){
                int finalI = i;
                addRenderableWidget(new InvisibleButton(screenX + 8 + (22*i), screenY + 34, 16, 16, new TextComponent("power_" + finalI), b -> {
                    if (selectedSlot != 0 && finalI < playerPowers.getPowers().size()){
                        if (playerPowers.getPowers().stream().toList().get(finalI).isActivePower())
                            playerPowers.setActivePower(playerPowers.getPowers().stream().toList().get(finalI), selectedSlot-1);
                        for (Widget widget : renderables){
                            if (widget instanceof SlotButton button && button.getMessage().getString().equals("slot_" + (selectedSlot-1)))
                                button.setPower(playerPowers.getPowers().stream().toList().get(finalI));
                            if (widget instanceof SlotButton button && !button.getMessage().getString().equals("slot_" + (selectedSlot-1))
                            && button.getPower() == playerPowers.getPowers().stream().toList().get(finalI)){
                                button.setPower(PlayerPower.EMPTY);
                            }
                            Messages.sendToServer(new UpdatePowers(playerPowers.serializeNBT()));
                        }
                    }
                }));
            }
        });

        Collection<PlayerPower> bloodPowerTree = createPowerTree(OPPowers.TEST_POWER);
        Collection<PlayerPower> energyPowerTree = createPowerTree(OPPowers.TEST_POWER_2);
        Collection<PlayerPower> deathPowerTree = createPowerTree(OPPowers.TEST_POWER_3);
        Collection<PlayerPower> knowledgePowerTree = createPowerTree(OPPowers.TEST_POWER_4);

        for (int i = 0; i < bloodPowerTree.size(); i++){
            addRenderableWidget(new PowerButton(screenX+30 + (22*i), screenY+67, bloodPowerTree.stream().toList().get(i), transcending));
        }

        for (int i = 0; i < energyPowerTree.size(); i++){
            addRenderableWidget(new PowerButton(screenX+30 + (22*i), screenY+88, energyPowerTree.stream().toList().get(i), transcending));
        }

        for (int i = 0; i < deathPowerTree.size(); i++){
            addRenderableWidget(new PowerButton(screenX+30 + (22*i), screenY+109, deathPowerTree.stream().toList().get(i), transcending));
        }

        for (int i = 0; i < knowledgePowerTree.size(); i++){
            addRenderableWidget(new PowerButton(screenX+30 + (22*i), screenY+130, knowledgePowerTree.stream().toList().get(i), transcending));
        }

    }
    @Override
    public void render(PoseStack stack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(stack);

        int screenX = (this.width/2) - (this.screenWidth /2);
        int screenY = (this.height/2) - (this.screenHeight/2);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        RenderSystem.disableDepthTest();
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(stack, screenX, screenY, 0, 0, screenWidth, screenHeight);

        blit(stack, screenX - 15, screenY + 19, 22, 219, 18, 20);

        if (selectedSlot > 0){
            blit(stack, screenX + 60 + 22*(selectedSlot-1), screenY + 5, 0, 218, 22, 22);
        }

        int color = 0x38494f;

        minecraft.player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex ->
                minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerPowers -> {
                    String value = playerNex.getNexPercent() + "%";
                    String label = CommonComponents.POWER_POINTS.getString();

                    font.draw(stack, value, width/2f - font.width(value)/2f, screenY - 2 - font.lineHeight, FastColor.ARGB32.color(255, 255, 255, 255));

                    value = String.valueOf(playerNex.getPowerPoints());
                    font.drawShadow(stack, label + ": " + value, screenX + 28 + 174/2f - font.width(label + ": " + value)/2f, screenY + 53 + 13/2f - font.lineHeight/2f, color);

                    for (int i = 0; i < playerPowers.getPowers().size(); i++){
                        PlayerPower power = playerPowers.getPowers().stream().toList().get(i);

                        if (power != PlayerPower.EMPTY) {
                            RenderSystem.setShaderColor(1f, 1f, 1f, power.isActivePower() ? 1f : 0.5f);

                            RenderSystem.setShaderTexture(0, PowerType.TEXTURE);
                            blit(stack, screenX + 8 + (22 * i), screenY + 34, power.getType().x, power.getType().y, 16, 16);

                            PowerIcon icon = PowerUtils.getIcon(power);
                            if (icon != null) {
                                RenderSystem.setShaderTexture(0, icon.getTextureLoc());
                                blit(stack, screenX + 10 + (22 * i), screenY + 36, icon.getX(), icon.getY(), 12, 12);
                                RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
                            }
                        }
                    }

                    for (int i = 0; i < 5; i++){
                        PlayerPower power = playerPowers.getActivePower(i);

                        if (power != PlayerPower.EMPTY) {
                            RenderSystem.setShaderTexture(0, PowerType.TEXTURE);
                            blit(stack, screenX + 63 + (22 * i), screenY + 8, power.getType().x, power.getType().y, 16, 16);

                            PowerIcon icon = PowerUtils.getIcon(power);
                            if (icon != null) {
                                RenderSystem.setShaderTexture(0, icon.getTextureLoc());
                                blit(stack, screenX + 65 + (22 * i), screenY + 10, icon.getX(), icon.getY(), 12, 12);
                            }
                        }
                    }
                }));

        super.render(stack, pMouseX, pMouseY, pPartialTick);

        for (Widget widget : renderables){
            if (widget instanceof PowerButton powerButton && powerButton.visible){
                minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerPowers -> {
                    List<Component> description = powerButton.getPower().getDescription();
                    if (powerButton.getPower().isActivePower()) {
                        description.add(CommonComponents.ACTIVE_POWER.plainCopy().withStyle(ChatFormatting.GRAY));
                    } else {
                        description.add(CommonComponents.PASSIVE_POWER.plainCopy().withStyle(ChatFormatting.GRAY));
                    }
                    if (playerPowers.hasPower(powerButton.getPower())){
                        description.add(CommonComponents.OWNED_POWER.plainCopy().withStyle(ChatFormatting.WHITE));
                    }

                    if (powerButton.isMouseOver(pMouseX, pMouseY))
                        renderComponentTooltip(stack,
                                description,
                                pMouseX, pMouseY);
                });
            }
            if (widget instanceof Button button && button.getMessage().getString().contains("power")) {
                minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerPowers -> {
                    int index = Character.getNumericValue(button.getMessage().getString().charAt(button.getMessage().getString().length() - 1));
                    if (index >= 0 && index < playerPowers.getPowers().size()){
                        PlayerPower power = playerPowers.getPower(index);
                        List<Component> description = power.getDescription();
                        if (power.isActivePower()) {
                            description.add(CommonComponents.ACTIVE_POWER.plainCopy().withStyle(ChatFormatting.GRAY));
                        } else {
                            description.add(CommonComponents.PASSIVE_POWER.plainCopy().withStyle(ChatFormatting.GRAY));
                        }

                        if (button.isMouseOver(pMouseX, pMouseY) && power != PlayerPower.EMPTY)
                            renderComponentTooltip(stack,
                                    description,
                                    pMouseX, pMouseY);
                    }
                });
            }
            if (widget instanceof SlotButton button && button.getMessage().getString().contains("slot")){
                minecraft.player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerPowers -> {
                    PlayerPower power = button.getPower();
                    List<Component> description = power.getDescription();

                    if (button.isMouseOver(pMouseX, pMouseY) && power != PlayerPower.EMPTY)
                        renderComponentTooltip(stack,
                                description,
                                pMouseX, pMouseY);
                });
            }
        }

        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);
        RenderSystem.disableBlend();
    }
    private Collection<PlayerPower> createPowerTree(PlayerPower firstPower){
        List<PlayerPower> powers = new ArrayList<>();
        powers.add(firstPower);

        int i = 0;
        OrdemParanormalAPI api = OrdemParanormalAPI.getInstance();
        for (PlayerPower power : api.getPowerMap().values()){
            if (power.getPowerRequirement() == powers.get(i) && power.getPowerRequirement() != PlayerPower.EMPTY) {
                powers.add(i+1, power);
                i++;
            }
        }
        return powers;
    }
    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
}
