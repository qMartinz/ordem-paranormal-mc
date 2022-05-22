package com.guga.ordemparanormal.client.ui;

import com.guga.ordemparanormal.common.capabilities.nexplayer.NexCapability;
import com.guga.ordemparanormal.common.capabilities.nexplayer.NexModel;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NexOverlay extends GuiComponent {
    private static final ResourceLocation TEXTURE = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/opgui.png");
    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event){
        Minecraft minecraft = Minecraft.getInstance();
        PoseStack poseStack = event.getMatrixStack();
        if (minecraft.player.getCapability(NexCapability.INSTANCE).isPresent()) {
            String s;

            NexModel nexModel = NexModel.get(minecraft.player);
            s = new TranslatableComponent("nex.abbreviation").append(": " + nexModel.getNexLevel() + "%").getString();

            minecraft.font.drawShadow(poseStack, s, 5, 5, 0xB0B0FF);
        }
    }
}
