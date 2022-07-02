package com.guga.ordemparanormal.common.events;

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

import java.util.UUID;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class PlayerEvents {
    private static final UUID damageBonusModUUID = UUID.randomUUID();
    private static final UUID healthBonusModUUID = UUID.randomUUID();
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
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            NexModel nexModel = new NexModel((Player) event.getObject());
            NexProvider provider = new NexProvider(nexModel);

            event.addCapability(new ResourceLocation(OrdemParanormal.MOD_ID, "cap_nex"), provider);
        }
    }
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event){
        if (event.isWasDeath()){
            event.getOriginal().reviveCaps();
            NexModel.get(event.getPlayer()).setNexLevel(NexModel.get(event.getOriginal()).getNexLevel());
            NexModel.get(event.getPlayer()).setNexXp(NexModel.get(event.getOriginal()).getNexXp());
            NexModel.get(event.getPlayer()).attributes = NexModel.get(event.getOriginal()).attributes;
            NexModel.get(event.getPlayer()).setAbilityPoints(NexModel.get(event.getOriginal()).getAbilityPoints());
            NexModel.get(event.getPlayer()).setMaxEffortPoints(NexModel.get(event.getOriginal()).getMaxEffortPoints());
            NexModel.get(event.getPlayer()).setCurEffortPoints(NexModel.get(event.getOriginal()).getMaxEffortPoints());
            NexModel.get(event.getPlayer()).rituals = NexModel.get(event.getOriginal()).rituals;
            event.getOriginal().invalidateCaps();
        }
        NexModel.get(event.getPlayer()).syncAttributeMods();
    }
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
        SyncNex.send(event.getPlayer());
        NexModel.get(event.getPlayer()).syncAttributeMods();
    }
    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event){
        SyncNex.send(event.getPlayer());
        NexModel.get(event.getPlayer()).syncAttributeMods();
    }
    @SubscribeEvent
    public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event){
        SyncNex.send(event.getPlayer());
        NexModel.get(event.getPlayer()).syncAttributeMods();
    }
}