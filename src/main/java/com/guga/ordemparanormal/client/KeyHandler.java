package com.guga.ordemparanormal.client;

import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.network.Messages;
import com.guga.ordemparanormal.core.network.Packets;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = OrdemParanormal.MOD_ID)
public class KeyHandler {
    private static final Minecraft MINECRAFT = Minecraft.getInstance();
    @SubscribeEvent
    public static void keyEvent(final InputEvent.Key event) {
        if (MINECRAFT.player == null || event.getAction() != 1)
            return;
        if(MINECRAFT.screen == null){
            for (int i = 0; i < Keybinds.QuickPower.KEYS.length; i++) {
                KeyMapping binding = Keybinds.QuickPower.KEYS[i].key();
                if (binding.consumeClick()) {
                    IAbilitiesCap cap = MINECRAFT.player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
                    if (cap == null) return;
                    Messages.sendToServer(new Packets.RequestPowerUse(i));
                }
            }
        }
    }
}
