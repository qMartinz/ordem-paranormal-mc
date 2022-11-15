package com.guga.ordemparanormal.client;

import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.core.network.Messages;
import com.guga.ordemparanormal.core.network.Packets;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

import java.util.LinkedList;
import java.util.List;

public class Keybind {
    private static final String MOD_CATEGORY = "ordemparanormal.key_category";
    public final List<KeyMapping> usePowerList = new LinkedList<>();
    public Keybind(){
        KeyMapping power1 = new KeyMapping("ordemparanormal.key.power_1", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_1, MOD_CATEGORY);
        ClientRegistry.registerKeyBinding(power1);

        KeyMapping power2 = new KeyMapping("ordemparanormal.key.power_2", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_2, MOD_CATEGORY);
        ClientRegistry.registerKeyBinding(power2);

        KeyMapping power3 = new KeyMapping("ordemparanormal.key.power_3", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_3, MOD_CATEGORY);
        ClientRegistry.registerKeyBinding(power3);

        KeyMapping power4 = new KeyMapping("ordemparanormal.key.power_4", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_4, MOD_CATEGORY);
        ClientRegistry.registerKeyBinding(power4);

        KeyMapping power5 = new KeyMapping("ordemparanormal.key.power_5", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_5, MOD_CATEGORY);
        ClientRegistry.registerKeyBinding(power5);

        usePowerList.add(power1);
        usePowerList.add(power2);
        usePowerList.add(power3);
        usePowerList.add(power4);
        usePowerList.add(power5);
    }
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event){
        Minecraft minecraft = Minecraft.getInstance();

        for (int i = 0; i < usePowerList.size(); i++){
            KeyMapping binding = usePowerList.get(i);
            if (binding.consumeClick() && minecraft.screen == null){
                IAbilitiesCap cap = minecraft.player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
                if (cap == null) return;

                Messages.sendToServer(new Packets.RequestPowerUse(i));
            }
        }
    }
}
