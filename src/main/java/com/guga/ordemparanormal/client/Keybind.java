package com.guga.ordemparanormal.client;

import com.guga.ordemparanormal.client.screen.NexScreen;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Keybind {
    private final KeyMapping nexScreenKey = new KeyMapping("key.nex_screen", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, 82, "OP Mod");

    public Keybind(){
        ClientRegistry.registerKeyBinding(nexScreenKey);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event){
        Minecraft minecraft = Minecraft.getInstance();

        if (nexScreenKey.consumeClick() && minecraft.screen == null){
            minecraft.setScreen(new NexScreen());
        }
    }
}
