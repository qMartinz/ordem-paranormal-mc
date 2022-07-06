package com.guga.ordemparanormal.common.commands;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class CommandEvents {
    @SubscribeEvent
    public static void onRegisterCommand(RegisterCommandsEvent event){
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        NexCommands.register(dispatcher);
    }
}
