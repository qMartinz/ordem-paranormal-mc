package com.guga.ordemparanormal.client;

import com.guga.ordemparanormal.api.capabilities.data.ParanormalEffectsProvider;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.IIngameOverlay;

import java.util.Random;

public class RitualOverlay {
    public static final IIngameOverlay HUD_DEATH_HEARTS = (gui, poseStack, partialTicks, width, height) -> {
        Player player = Minecraft.getInstance().player;
        Random random = new Random();
        player.getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).ifPresent(cap -> {
            int amount = cap.getDeathHealthPoints();
            int max = (int) player.getMaxHealth();
            int hearts = Mth.ceil(amount/2D);
            int maxHearts = Mth.ceil(max/2D);

            int x = width / 2 - 91;
            int y = height - 39;
            int i3 = Mth.ceil(max / 2.0F / 10.0F);
            int j2 = Math.max(10 - (i3 - 2), 3);
            int j4 = -1;
            if (player.hasEffect(MobEffects.REGENERATION)) {
                j4 = gui.getGuiTicks() % Mth.ceil(max + 5.0F);
            }

            for(int i1 = hearts - 1; i1 >= 0; --i1){
                int j1 = i1 / 10;
                int k1 = i1 % 10;
                int l1 = x + k1 * 8;
                int i2 = y - j1 * j2;
                int j3 = i1 * 2;
                boolean half = j3 + 1 == amount;

                if (i1 < maxHearts && i1 == j4) {
                    i2 -= 2;
                }

                RenderSystem.setShaderTexture(0, NexOverlay.TEXTURES);
                gui.blit(poseStack, l1, i2, half ? 107 : 98, 0, 9, 9);
            }
        });
    };

}
