package com.guga.ordemparanormal.client.screen.powerscreen;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.client.screen.buttons.PowerButton;
import com.guga.ordemparanormal.common.CommonComponents;
import com.guga.ordemparanormal.common.power.Afinidade;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PowerScreen extends Screen {
    public static final ResourceLocation TEXTURE = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/powerscreen.png");
    public final List<PowerButton> powerIcons = new ArrayList<>();
    private final Map<PowerButton, Integer> iconsX = new HashMap<>();
    private final Map<PowerButton, Integer> iconsY = new HashMap<>();
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

        powerIcons.clear();
        this.initContents();

        powerIcons.forEach(button -> iconsX.put(button, button.x));
        powerIcons.forEach(button -> iconsY.put(button, button.y));
        powerIcons.forEach(this::addRenderableWidget);

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

        this.addWidget(new Button(2, 9, 32, 32, TextComponent.EMPTY, button -> minecraft.setScreen(new BloodPowerScreen())));
        this.addWidget(new Button(2, 43, 32, 32, TextComponent.EMPTY, button -> minecraft.setScreen(new DeathPowerScreen())));
        this.addWidget(new Button(2, 77, 32, 32, TextComponent.EMPTY, button -> minecraft.setScreen(new EnergyPowerScreen())));
        this.addWidget(new Button(2, 111, 32, 32, TextComponent.EMPTY, button -> minecraft.setScreen(new KnowledgePowerScreen())));
    }

    @Override
    public void resize(Minecraft pMinecraft, int pWidth, int pHeight) {
        super.resize(pMinecraft, pWidth, pHeight);
    }

    @Override
    public void render(PoseStack stack, int pMouseX, int pMouseY, float pPartialTick) {
        int tabWidth = this.width - 41;
        fill(stack,0,0,this.width,this.height,0xFF341c27);

        this.renderContents(stack, pMouseX, pMouseY, pPartialTick);
        for (PowerButton icon : powerIcons) {
            int reqSize = icon.getPower().getPowerRequirements().size();

            for (PowerButton icon2 : powerIcons){
                if (icon.getPower().getPowerRequirements().contains(icon2.getPower()) && !(icon2.getPower() instanceof Afinidade)){
                    int xd = icon.x - icon2.x;
                    int yd = icon.y - icon2.y;
                    boolean diagonal = Math.abs(xd) > 0 && Math.abs(yd) > 0;
                    boolean horizontal = Math.abs(xd) > 0 & Math.abs(yd) == 0;
                    boolean vertical = Math.abs(xd) == 0 & Math.abs(yd) > 0;

                    if (diagonal){
                        int minX = icon.x + icon.getWidth()/2;
                        int maxX = icon2.x + icon2.getWidth()/2;
                        int minY = icon.y;
                        int midY = icon.y - yd/2;
                        int maxY = icon2.y;

                        if (yd < 0) {
                            minY += icon.getHeight();
                            midY -= icon2.getHeight()/2;
                        }
                        if (yd > 0) {
                            maxY += icon2.getHeight();
                            midY += icon2.getHeight()/2;
                        }

                        fill(stack, minX - 1, minY, minX + 1, midY, 0xFFbf782c);
                        fill(stack, minX - 1, midY - 1, maxX + 1, midY + 1, 0xFFbf782c);
                        fill(stack, maxX - 1, midY, maxX + 1, maxY, 0xFFbf782c);
                    } else {
                        int minX = vertical ? (icon.x + icon.getWidth()/2) - 1 : icon.x;
                        int minY = horizontal ? (icon.y + icon.getHeight()/2) - 1 : icon.y;
                        int maxX = vertical ? minX + 2 : icon2.x;
                        int maxY = horizontal ? minY + 2 : icon2.y;

                        if (xd > 0 && horizontal) maxX += icon.getWidth();
                        if (yd > 0 && vertical) maxY += icon.getHeight();
                        if (xd < 0 && horizontal) minX += icon2.getWidth();
                        if (yd < 0 && vertical) minY += icon2.getHeight();

                        fill(stack, minX, minY, maxX, maxY, 0xFFbf782c);
                    }
                }
            }
        }
        super.render(stack, pMouseX, pMouseY, pPartialTick);

        drawBorder(stack);
        RenderSystem.setShaderTexture(0, TEXTURE);
        if (xOffset < -350) blit(stack, 37, (int) (this.height/2f - 7/2f), 0, 0, 7, 7);
        if (xOffset > 350) blit(stack, this.width - 13, (int) (this.height/2f - 7/2f), 7, 7, 7, 7);
        if (yOffset < -200) blit(stack, (int) (tabWidth/2f - 7/2f) + 37, 6, 0, 7, 7, 7);
        if (yOffset > 200) blit(stack, (int) (tabWidth/2f - 7/2f) + 37, this.height - 13, 7, 0, 7, 7);

        if (this.element == ParanormalElement.SANGUE) {
            blit(stack, 2, 9, 14, 32, 32, 32);
        } else { blit(stack, 2, 9, 14, 0, 32, 32); }
        if (this.element == ParanormalElement.MORTE) {
            blit(stack, 2, 43, 46, 32, 32, 32);
        } else { blit(stack, 2, 43, 46, 0, 32, 32); }
        if (this.element == ParanormalElement.ENERGIA) {
            blit(stack, 2, 77, 78, 32, 32, 32);
        } else { blit(stack, 2, 77, 78, 0, 32, 32); }
        if (this.element == ParanormalElement.CONHECIMENTO) {
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
        if (pMouseX > 36 && pMouseX < this.width - 5 &&
            pMouseY > 5 && pMouseY < this.height - 5){
            this.xOffset += pDragX;
            this.yOffset += pDragY;
            this.xOffset = Mth.clamp(xOffset, -850, 850);
            this.yOffset = Mth.clamp(yOffset, -850, 850);
        }

        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }
    @Override
    public void tick() {
        iconsX.forEach((button, x) -> button.x = (int) (x + xOffset));
        iconsY.forEach((button, y) -> button.y = (int) (y + yOffset));
    }
    public PowerButton addPowerIcon(PowerButton icon){
        this.powerIcons.add(icon);
        return icon;
    }
    public void initContents(){}
    public void renderContents(PoseStack stack, int pMouseX, int pMouseY, float pPartialTick){}
    @Override
    public boolean isPauseScreen()
    {
        return true;
    }
}
