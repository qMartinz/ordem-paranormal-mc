package com.guga.ordemparanormal.client;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.LinkedList;
import java.util.List;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Keybinds {
    private static final String MOD_CATEGORY = "ordemparanormal.key_category";
    public static final KeyMapping POWER_1 = new KeyMapping("ordemparanormal.key.power_1",
            KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_1,
            MOD_CATEGORY);
    public static final KeyMapping POWER_2 = new KeyMapping("ordemparanormal.key.power_2",
            KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_2,
            MOD_CATEGORY);
    public static final KeyMapping POWER_3 = new KeyMapping("ordemparanormal.key.power_3",
            KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_3,
            MOD_CATEGORY);
    public static final KeyMapping POWER_4 = new KeyMapping("ordemparanormal.key.power_4",
            KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_4,
            MOD_CATEGORY);
    public static final KeyMapping POWER_5 = new KeyMapping("ordemparanormal.key.power_5",
            KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_5,
            MOD_CATEGORY);
    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(POWER_1);
        event.register(POWER_2);
        event.register(POWER_3);
        event.register(POWER_4);
        event.register(POWER_5);
    }
    public record QuickPower(int slot, KeyMapping key){
        public static final QuickPower[] KEYS = new QuickPower[]{
                new QuickPower(0, POWER_1),
                new QuickPower(1, POWER_2),
                new QuickPower(2, POWER_3),
                new QuickPower(3, POWER_4),
                new QuickPower(4, POWER_5),
        };
    }
}
