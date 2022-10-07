package com.guga.ordemparanormal.client.screen;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.client.screen.buttons.PowerButton;
import com.guga.ordemparanormal.client.screen.powerscreen.BloodPowerScreen;
import com.guga.ordemparanormal.client.screen.powerscreen.DeathPowerScreen;
import com.guga.ordemparanormal.client.screen.powerscreen.EnergyPowerScreen;
import com.guga.ordemparanormal.client.screen.powerscreen.KnowledgePowerScreen;
import com.guga.ordemparanormal.common.CommonComponents;
import com.guga.ordemparanormal.common.power.Afinidade;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.*;

public class PowerScreen extends Screen {
    public static final ResourceLocation TEXTURE = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/powerscreen.png");
    public final List<PowerButton> powerIcons = new ArrayList<>();
    private final Map<PowerButton, Integer> iconsX = new HashMap<>();
    private final Map<PowerButton, Integer> iconsY = new HashMap<>();
    public final ParanormalElement element;
    public double xOffset;
    public double yOffset;
    public int selectedSlot = 6;
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

        for (int i = 0; i < 5; i++){
            int finalI = i;
            this.addWidget(new Button(tabWidth / 2 - 36 + 26*i, height - 46, 22, 22, new TextComponent("slot_" + i), button -> {
                if (selectedSlot != finalI) {
                    selectedSlot = finalI;
                } else selectedSlot = 6;
            }));
        }

        this.addWidget(new Button(2, 9, 32, 32, TextComponent.EMPTY, button -> minecraft.setScreen(new BloodPowerScreen())));
        this.addWidget(new Button(2, 43, 32, 32, TextComponent.EMPTY, button -> minecraft.setScreen(new DeathPowerScreen())));
        this.addWidget(new Button(2, 77, 32, 32, TextComponent.EMPTY, button -> minecraft.setScreen(new EnergyPowerScreen())));
        this.addWidget(new Button(2, 111, 32, 32, TextComponent.EMPTY, button -> minecraft.setScreen(new KnowledgePowerScreen())));
    }
    @Override
    public void render(PoseStack stack, int pMouseX, int pMouseY, float pPartialTick) {
        INexCap playerNex = minecraft.player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
        IAbilitiesCap playerAbilities = minecraft.player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (playerNex == null || playerAbilities == null) return;

        int tabWidth = this.width - 41;
        fill(stack,0,0,this.width,this.height,0xFF341c27);

        this.renderContents(stack, pMouseX, pMouseY, pPartialTick);
        this.drawPowerHierarchyLines(stack);
        super.render(stack, pMouseX, pMouseY, pPartialTick);

        drawBorder(stack);
        RenderSystem.setShaderTexture(0, TEXTURE);
        if (xOffset < -350) blit(stack, 37, (int) (this.height/2f - 7/2f), 0, 0, 7, 7);
        if (xOffset > 350) blit(stack, this.width - 13, (int) (this.height/2f - 7/2f), 7, 7, 7, 7);
        if (yOffset < -200) blit(stack, (int) (tabWidth/2f - 7/2f) + 37, 6, 0, 7, 7, 7);
        if (yOffset > 200) blit(stack, (int) (tabWidth/2f - 7/2f) + 37, this.height - 13, 7, 0, 7, 7);

        drawTabButtons(stack);

        Component label = CommonComponents.POWER_POINTS;
        String value = String.valueOf(playerNex.getPowerPoints());
        font.draw(stack, label, tabWidth/2f - font.width(label)/2f + 37, 15, 0xFFFFFF);
        font.draw(stack, value, tabWidth/2f - font.width(value)/2f + 37, 16 + font.lineHeight, 0xFFFFFF);

        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(stack, tabWidth/2 - 41, height - 55, 0, 164, 136, 40);
        drawActivePowerSlots(stack, playerAbilities);
        RenderSystem.setShaderTexture(0, TEXTURE);
        if (selectedSlot < 6) blit(stack, tabWidth/2 - 38 + 26*selectedSlot, height - 50, 0, 134, 26, 30);

        for (PowerButton powerButton : powerIcons){
            if (powerButton.isMouseOver(pMouseX, pMouseY)) drawPowerInfo(stack, powerButton.getPower());
        }
        for (GuiEventListener eventListener : children()){
            if (eventListener instanceof Button button){
                if (button.getMessage().getString().startsWith("slot_") && button.isMouseOver(pMouseX, pMouseY))
                    drawPowerInfo(stack, playerAbilities.getActivePower(Integer.parseInt(button.getMessage().getString().replaceAll("\\D+",""))));
            }
        }
    }
    private void drawPowerHierarchyLines(PoseStack stack){
        for (PowerButton icon : powerIcons) {
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
    private void drawTabButtons(PoseStack stack){
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
    }
    private void drawActivePowerSlots(PoseStack stack, IAbilitiesCap playerPowers){
        int tabWidth = this.width - 41;
        for (int i = 0; i < 5; i++){
            if (playerPowers.getActivePower(i) != PlayerPower.EMPTY){
                RenderSystem.setShaderTexture(0, TEXTURE);
                blit(stack, tabWidth / 2 - 35 + 26 * i, height - 45, 20 * playerPowers.getActivePower(i).getElement().index, 84, 20, 20);

                ResourceLocation icon = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/paranormal_power/" + playerPowers.getActivePower(i).getId() + ".png");
                if (minecraft.getResourceManager().hasResource(icon)) {
                    RenderSystem.setShaderTexture(0, icon);
                    blit(stack, tabWidth / 2 - 33 + 26 * i, height - 43, 0, 0, 16, 16, 16, 16);
                }
            }
        }
    }
    private void drawPowerInfo(PoseStack stack, PlayerPower power){
        if (power != PlayerPower.EMPTY) {
            int tooltipHeight = 4;
            List<FormattedText> splitLines = font.getSplitter().splitLines(power.getDescription(),
                    122, Style.EMPTY.applyFormat(ChatFormatting.WHITE));

            if (!power.getPowerRequirements().isEmpty() || !Arrays.equals(power.getAttributesRequired(), new int[]{0, 0, 0}) || power.getNexRequired() != 0) {
                List<MutableComponent> requisites = new ArrayList<>();

                if (!power.getPowerRequirements().isEmpty()) power.getPowerRequirements().forEach(req -> requisites.add(req.getDisplayName().plainCopy()));

                if (power.getNexRequired() != 0) {
                    int nex = power.getNexRequired() * 5 - (power.getNexRequired() == 20 ? 1 : 0);
                    requisites.add(CommonComponents.NEX_ABBREVIATION.plainCopy().append(" " + nex + "%"));
                }
                if (power.getAttributesRequired()[ParanormalAttribute.STRENGTH.index] != 0)
                    requisites.add(ParanormalAttribute.STRENGTH.getDisplayName().plainCopy().append(" " + power.getAttributesRequired()[ParanormalAttribute.STRENGTH.index]));

                if (power.getAttributesRequired()[ParanormalAttribute.VIGOR.index] != 0)
                    requisites.add(ParanormalAttribute.VIGOR.getDisplayName().plainCopy().append(" " + power.getAttributesRequired()[ParanormalAttribute.VIGOR.index]));

                if (power.getAttributesRequired()[ParanormalAttribute.PRESENCE.index] != 0)
                    requisites.add(ParanormalAttribute.PRESENCE.getDisplayName().plainCopy().append(" " + power.getAttributesRequired()[ParanormalAttribute.PRESENCE.index]));

                Iterator<MutableComponent> iterator = requisites.iterator();
                MutableComponent requisitesComponent = CommonComponents.REQUISITES.plainCopy().append(": ").append(iterator.next());
                iterator.forEachRemaining(req -> requisitesComponent.append(", " + req.getString()));

                splitLines.add(TextComponent.EMPTY);
                splitLines.addAll(font.getSplitter().splitLines(requisitesComponent, 122, Style.EMPTY.applyFormat(ChatFormatting.WHITE)));
            }

            tooltipHeight += font.lineHeight * splitLines.size();

            int width = 129;
            int height = tooltipHeight + 17;
            int x = this.width - width - 10;
            int y = 10;

            if (power.isActivePower() && power.getEffortCost() > 0) height += 12;

            fill(stack, x, y, x + width, y + height, 0xFF602c2c);
            fill(stack, x - 1, y - 3, x, y + height + 3, 0xFFde9e41);
            fill(stack, x + width + 1, y - 3, x + width, y + height + 3, 0xFFde9e41);
            fill(stack, x - 3, y - 1, x + width + 3, y, 0xFFde9e41);
            fill(stack, x - 3, y + height + 1, x + width + 3, y + height, 0xFFde9e41);

            fill(stack, x + 2, y + 13, x + width - 2, y + 15 + tooltipHeight, 0xFFde9e41);
            fill(stack, x + 3, y + 14, x + width - 3, y + 14 + tooltipHeight, 0xFF341c27);

            for (int i = 0; i < splitLines.size(); i++) {
                font.draw(stack, splitLines.get(i).getString(), x + 4, y + 17 + font.lineHeight * i, 0xFFFFFF);
            }

            if (power.isActivePower() && power.getEffortCost() > 0) {
                RenderSystem.setShaderTexture(0, TEXTURE);
                blit(stack, x + width / 2 - (font.width(String.valueOf(power.getEffortCost())) + 9) / 2, y + height - 10,
                        100, 64, 9, 9);
                font.draw(stack, Integer.toString(power.getEffortCost()),
                        x + width / 2f - (font.width(String.valueOf(power.getEffortCost())) + 9) / 2f + 9, y + height - 10,
                        0x9cdb43);
            }

            font.draw(stack, power.getDisplayName(), x + width / 2f - font.width(power.getDisplayName()) / 2f, y + 2, 0xFFFFFF);
        }
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
