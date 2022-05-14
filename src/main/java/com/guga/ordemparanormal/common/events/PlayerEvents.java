package com.guga.ordemparanormal.common.events;

import com.guga.ordemparanormal.common.capabilities.nexplayer.NexCapability;
import com.guga.ordemparanormal.common.capabilities.nexplayer.NexModel;
import com.guga.ordemparanormal.common.capabilities.nexplayer.NexProvider;
import com.guga.ordemparanormal.common.commands.NexCommands;
import com.guga.ordemparanormal.common.network.SyncNex;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class PlayerEvents {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event){
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        NexCommands.register(dispatcher);
    }
    @SubscribeEvent
     public static void onRegisterCapabilites(RegisterCapabilitiesEvent event){
        event.register(NexModel.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof Player){
            NexModel nexModel = new NexModel();
            NexProvider provider = new NexProvider(nexModel);

            event.addCapability(new ResourceLocation(OrdemParanormal.MOD_ID, "cap_nex"), provider);
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event){
        NexModel.get(event.getPlayer()).nexLevel = NexModel.get(event.getOriginal()).nexLevel;
        NexModel.get(event.getPlayer()).nexXP = NexModel.get(event.getOriginal()).nexXP;
        event.getOriginal().getCapability(NexCapability.INSTANCE).invalidate();
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
        SyncNex.send(event.getPlayer());
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event){
        SyncNex.send(event.getPlayer());
    }

    @SubscribeEvent
    public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event){
        SyncNex.send(event.getPlayer());
    }
}
