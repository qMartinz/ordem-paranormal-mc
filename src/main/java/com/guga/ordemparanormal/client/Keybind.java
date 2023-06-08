package com.guga.ordemparanormal.client;

import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.network.Messages;
import com.guga.ordemparanormal.core.network.Packets;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.LinkedList;
import java.util.List;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Keybind {
    private static final String MOD_CATEGORY = "ordemparanormal.key_category";
    public final List<KeyMapping> usePowerList = new LinkedList<>();
    public static final KeyMapping POWER_1 = new KeyMapping("ordemparanormal.key.power_1", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_1, MOD_CATEGORY);
    public static final KeyMapping POWER_2 = new KeyMapping("ordemparanormal.key.power_2", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_2, MOD_CATEGORY);
    public static final KeyMapping POWER_3 = new KeyMapping("ordemparanormal.key.power_3", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_3, MOD_CATEGORY);
    public static final KeyMapping POWER_4 = new KeyMapping("ordemparanormal.key.power_4", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_4, MOD_CATEGORY);
    public static final KeyMapping POWER_5 = new KeyMapping("ordemparanormal.key.power_5", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_5, MOD_CATEGORY);
    public Keybind(){
        usePowerList.add(POWER_1);
        usePowerList.add(POWER_2);
        usePowerList.add(POWER_3);
        usePowerList.add(POWER_4);
        usePowerList.add(POWER_5);
    }
    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(POWER_1);
        event.register(POWER_2);
        event.register(POWER_3);
        event.register(POWER_4);
        event.register(POWER_5);
    }
    @SubscribeEvent
    public void onKeyInput(InputEvent.Key event){
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
