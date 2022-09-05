package com.guga.ordemparanormal.client.screen.powerscreen;

import com.google.common.collect.Lists;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.client.screen.buttons.PowerButton;
import com.guga.ordemparanormal.common.CommonComponents;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.ArrayList;
import java.util.List;

public class PowerScreen extends Screen {
    public static final ResourceLocation TEXTURE = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/powerscreen.png");
    public final List<PowerButton> powerIcons = new ArrayList<>();
    public final ParanormalElement element;
    public double xOffset;
    public double yOffset;
    protected PowerScreen(ParanormalElement element) {
        super(TextComponent.EMPTY);
        this.element = element;
    }
    @Override
    protected void init() {
        int tabWidth = this.width - 41;

        this.initContents();
        powerIcons.forEach(this::addWidget);
        this.addWidget(new Button(37, (int) (this.height/2f - 7/2f), 7, 7, TextComponent.EMPTY, button -> {
            if (xOffset < -350) xOffset = 0;
        }));
        this.addWidget(new Button(this.width - 13, (int) (this.height/2f - 7/2f), 7, 7, TextComponent.EMPTY, button -> {
            if (xOffset > 350) xOffset = 0;
        }));
        this.addWidget(new Button((int) (tabWidth/2f - 7/2f) + 37, 6, 7, 7, TextComponent.EMPTY, button -> {
            if (yOffset < -200) yOffset = 0;
        }));
        this.addWidget(new Button((int) (tabWidth/2f - 7/2f) + 37, this.height - 13, 7, 7, TextComponent.EMPTY, button -> {
            if (yOffset > 200) yOffset = 0;
        }));

        this.addWidget(new Button(2, 9, 32, 32, TextComponent.EMPTY, button -> {
            minecraft.setScreen(new BloodPowerScreen());
        }));
        this.addWidget(new Button(2, 43, 32, 32, TextComponent.EMPTY, button -> {
            minecraft.setScreen(new DeathPowerScreen());
        }));
        this.addWidget(new Button(2, 77, 32, 32, TextComponent.EMPTY, button -> {
            minecraft.setScreen(new EnergyPowerScreen());
        }));
        this.addWidget(new Button(2, 111, 32, 32, TextComponent.EMPTY, button -> {
            minecraft.setScreen(new KnowledgePowerScreen());
        }));
    }
    @Override
    public void render(PoseStack stack, int pMouseX, int pMouseY, float pPartialTick) {
        int tabWidth = this.width - 41;
        fill(stack,0,0,this.width,this.height,0xFF341c27);

        this.renderContents(stack, pMouseX, pMouseY, pPartialTick);
        super.render(stack, pMouseX, pMouseY, pPartialTick);

        drawBorder(stack);
        RenderSystem.setShaderTexture(0, TEXTURE);
        if (xOffset < -350) blit(stack, 37, (int) (this.height/2f - 7/2f), 0, 0, 7, 7);
        if (xOffset > 350) blit(stack, this.width - 13, (int) (this.height/2f - 7/2f), 7, 7, 7, 7);
        if (yOffset < -200) blit(stack, (int) (tabWidth/2f - 7/2f) + 37, 6, 0, 7, 7, 7);
        if (yOffset > 200) blit(stack, (int) (tabWidth/2f - 7/2f) + 37, this.height - 13, 7, 0, 7, 7);

        if (this.element == ParanormalElement.BLOOD) {
            blit(stack, 2, 9, 14, 32, 32, 32);
        } else { blit(stack, 2, 9, 14, 0, 32, 32); }
        if (this.element == ParanormalElement.DEATH) {
            blit(stack, 2, 43, 46, 32, 32, 32);
        } else { blit(stack, 2, 43, 46, 0, 32, 32); }
        if (this.element == ParanormalElement.ENERGY) {
            blit(stack, 2, 77, 78, 32, 32, 32);
        } else { blit(stack, 2, 77, 78, 0, 32, 32); }
        if (this.element == ParanormalElement.KNOWLEDGE) {
            blit(stack, 2, 111, 110, 32, 32, 32);
        } else { blit(stack, 2, 111, 110, 0, 32, 32); }

        minecraft.player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(cap -> {
            Component label = CommonComponents.POWER_POINTS;
            String value = String.valueOf(cap.getPowerPoints());

            font.draw(stack, label, tabWidth/2f - font.width(label)/2f + 37, 15, 0xFFFFFF);
            font.draw(stack, value, tabWidth/2f - font.width(value)/2f + 37, 16 + font.lineHeight, 0xFFFFFF);
        });

        for (PowerButton icon : powerIcons){
            if (icon.isMouseOver(pMouseX, pMouseY) &&
                    pMouseX > 36 && pMouseX < this.width - 5 &&
                    pMouseY > 5 && pMouseY < this.height - 5) renderComponentTooltip(stack, icon.getPower().getDescription(), pMouseX, pMouseY);
        }
    }
    private void drawBorder(PoseStack stack){
        fill(stack, 0,0,36,this.height,0xFF602c2c);
        fill(stack, this.width - 5,0,this.width,this.height,0xFF602c2c);
        fill(stack, 0,0,this.width,5,0xFF602c2c);
        fill(stack, 0,this.height - 5,this.width,this.height,0xFF602c2c);

        fill(stack, 36,0,37,this.height,0xFFde9e41);
        fill(stack, this.width - 5,0,this.width - 6,this.height,0xFFde9e41);
        fill(stack, 0,5,this.width,6,0xFFde9e41);
        fill(stack, 0,this.height - 5,this.width,this.height - 6,0xFFde9e41);
    }
    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        this.xOffset += pDragX;
        this.yOffset += pDragY;
        this.xOffset = Mth.clamp(xOffset, -850, 850);
        this.yOffset = Mth.clamp(yOffset, -850, 850);

        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }
    public PowerButton addPowerIcon(PowerButton icon){
        this.powerIcons.add(icon);
        return icon;
    }
    public void initContents(){}
    public void renderContents(PoseStack stack, int pMouseX, int pMouseY, float pPartialTick){
        for (PowerButton icon : powerIcons){
            if (powerIcons.stream().anyMatch(button -> button.getPower() == icon.getPower())) {
                for (PowerButton requisite : powerIcons){
                    if (icon.y > requisite.y && requisite.x == icon.x) {
                        this.vLine(stack, (int) (icon.x + icon.getWidth() / 2f),
                                icon.y + icon.getHeight(), requisite.y - 1,
                                0xbf782c);
                    } if (icon.y < requisite.y && requisite.x == icon.x) {
                        this.vLine(stack, (int) (icon.x + icon.getWidth() / 2f),
                                icon.y - 1, requisite.y + requisite.getHeight(),
                                0xbf782c);
                    } else if (icon.y == requisite.y && requisite.x > icon.x) {
                        this.hLine(stack, icon.x - 1, requisite.x + requisite.getWidth(),
                                icon.y + icon.getHeight() / 2,
                                0xbf782c);
                    } else if (icon.y == requisite.y && requisite.x < icon.x) {
                        this.hLine(stack, icon.x + icon.getWidth(), requisite.x - 1,
                                icon.y + icon.getHeight() / 2,
                                0xbf782c);
                    }
                }
            }
        }
        powerIcons.forEach(icon -> icon.render(stack, pMouseX, pMouseY, pPartialTick, xOffset, yOffset));

    }
    @Override
    public boolean isPauseScreen()
    {
        return true;
    }
}
