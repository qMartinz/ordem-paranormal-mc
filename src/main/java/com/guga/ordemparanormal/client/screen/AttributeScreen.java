package com.guga.ordemparanormal.client.screen;

import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.client.screen.buttons.AttributeButton;
import com.guga.ordemparanormal.client.screen.powerscreen.BloodPowerScreen;
import com.guga.ordemparanormal.client.screen.widgets.SelectedRitual;
import com.guga.ordemparanormal.common.CommonComponents;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class AttributeScreen extends Screen {
    public static final ResourceLocation TEXTURES = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/nexscreen.png");
    private final int screenHeight = 217;
    public final boolean transcending;
    public AttributeScreen(boolean transcending) {
        super(TextComponent.EMPTY);
        this.transcending = transcending;
    }
    @Override
    protected void init(){
        int screenX = (this.width/2) - 266/2;
        int tabX = screenX + 110;
        int screenY = (this.height/2) - (this.screenHeight/2);

        addRenderableWidget(new AttributeButton(screenX + 33, screenY + 12, ParanormalAttribute.STRENGTH));
        addRenderableWidget(new AttributeButton(screenX + 33, screenY + 75, ParanormalAttribute.VIGOR));
        addRenderableWidget(new AttributeButton(screenX + 33, screenY + 138, ParanormalAttribute.PRESENCE));
        SelectedRitual ritualWidget = new SelectedRitual(tabX + 46, screenY + 7);
        addRenderableWidget(ritualWidget);

        addWidget(new Button(tabX + 44, screenY + 75, 8, 8, TextComponent.EMPTY, b -> minecraft.player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities ->
                ritualWidget.setRitualIndex(ritualWidget.getRitualIndex() - 1 >= 0 ? ritualWidget.getRitualIndex() - 1 : playerAbilities.getKnownRituals().size() - 1))));
        addWidget(new Button(tabX + 104, screenY + 75, 8, 8, TextComponent.EMPTY, b -> minecraft.player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities ->
                ritualWidget.setRitualIndex(playerAbilities.getKnownRituals().size() - 1 >= ritualWidget.getRitualIndex() + 1 ? ritualWidget.getRitualIndex() + 1 : 0))));

        if (this.transcending) addWidget(new Button(screenX + 39, screenY + 195, 20, 20, TextComponent.EMPTY, b -> minecraft.setScreen(new BloodPowerScreen())));
    }
    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        INexCap playerNex = minecraft.player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
        IAbilitiesCap playerAbilities = minecraft.player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (playerNex == null || playerAbilities == null) return;

        renderBackground(stack);

        int screenX = (this.width/2) - 266/2;
        int screenY = (this.height/2) - (this.screenHeight/2);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        RenderSystem.disableDepthTest();
        RenderSystem.setShaderTexture(0, TEXTURES);

        if (transcending) blit(stack, screenX + 98/2 - 27, screenY + 174, 174, 0, 54, 63);

        blit(stack, screenX, screenY, 0, 0, 98, this.screenHeight);

        if (transcending) blit(stack, screenX + 44, screenY + 197, 228, 0, 10, 16);

        String label = CommonComponents.ATTRIBUTE_POINTS.getString();
        String value = String.valueOf(playerNex.getAttributePoints());
        font.draw(stack, label, screenX + 98/2f - font.width(label)/2f, screenY - 2 - font.lineHeight*2, 0xFFFFFF);
        font.draw(stack, value, screenX + 98/2f - font.width(value)/2f, screenY - 1 - font.lineHeight, 0xFFFFFF);

        this.renderRitualTab(stack);

        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);
        RenderSystem.disableBlend();

        super.render(stack, mouseX, mouseY, partialTicks);
    }
    public void renderRitualTab(PoseStack stack){
        INexCap playerNex = minecraft.player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
        IAbilitiesCap playerAbilities = minecraft.player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (playerNex == null || playerAbilities == null) return;

        int tabX = (this.width/2) - 266/2 + 110;
        int tabY = (this.height/2) - (this.screenHeight/2);

        renderables.stream().filter(w -> w instanceof SelectedRitual).findFirst().ifPresent(widget -> {
            if (widget instanceof SelectedRitual ritualWidget && playerAbilities.getKnownRituals().size() > 0) {
                AbstractRitual ritual = playerAbilities.getKnownRituals().stream().toList().get(ritualWidget.getRitualIndex());
                FormattedText unsplitText = ritual.getDescription();
                int tabHeight = 112;

                List<FormattedText> splitLines = font.getSplitter().splitLines(
                        unsplitText,
                        148, Style.EMPTY.applyFormat(ChatFormatting.WHITE));
                tabHeight += 2 + font.lineHeight * splitLines.size();

                fill(stack, tabX, tabY, tabX + 156, tabY + tabHeight, 0xFF602c2c);
                fill(stack, tabX - 1, tabY - 3, tabX, tabY + tabHeight + 3, 0xFFde9e41);
                fill(stack, tabX + 157, tabY - 3, tabX + 156, tabY + tabHeight + 3, 0xFFde9e41);
                fill(stack, tabX - 3, tabY - 1, tabX + 159, tabY, 0xFFde9e41);
                fill(stack, tabX - 3, tabY + tabHeight + 1, tabX + 159, tabY + tabHeight, 0xFFde9e41);

                fill(stack, tabX + 1, tabY + 98, tabX + 155, tabY + 100 + font.lineHeight * splitLines.size(), 0xFFde9e41);
                fill(stack, tabX + 2, tabY + 99, tabX + 154, tabY + 99 + font.lineHeight * splitLines.size(), 0xFF341c27);

                RenderSystem.setShaderTexture(0, TEXTURES);
                blit(stack, tabX + 40, tabY + 1, 98, 0, 76, 86);
                blit(stack, tabX + 1, tabY + tabHeight - 11, 98, 86, 9, 9);

                String text = playerAbilities.getKnownRituals().size() + "/" + playerNex.getRitualSlots();
                font.draw(stack, text, tabX + 79 - font.width(text)/2f, tabY + 76, 0xFFFFFF);

                font.draw(stack, ritual.getDisplayName(), tabX + 79 - font.width(ritual.getDisplayName())/2f, tabY + 88, 0xFFFFFF);

                text = String.valueOf(ritual.getEffortCost());
                font.draw(stack, text, tabX + 11, tabY + tabHeight - 10, 0xFFFFFF);

                font.draw(stack, ritual.getElement().getDisplayName(), tabX + 156 - font.width(ritual.getElement().getDisplayName()) - 2, tabY + tabHeight - 10, 0xFFFFFF);

                for (int i = 0; i < splitLines.size(); i++) {
                    font.draw(stack, splitLines.get(i).getString(), tabX + 4, tabY + 100 + font.lineHeight * i, 0xFFFFFF);
                }
            }
        });
    }
    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
}