package com.guga.ordemparanormal.common.events;

import com.guga.ordemparanormal.common.abilities.ParanormalAttribute;
import com.guga.ordemparanormal.common.capabilities.nexplayer.NexModel;
import com.guga.ordemparanormal.common.capabilities.nexplayer.NexProvider;
import com.guga.ordemparanormal.common.commands.NexCommands;
import com.guga.ordemparanormal.common.network.SyncNex;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.datafixers.types.templates.Tag;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.Foods;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
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
            NexModel.get(event.getPlayer()).nexLevel = NexModel.get(event.getOriginal()).nexLevel;
            NexModel.get(event.getPlayer()).nexXP = NexModel.get(event.getOriginal()).nexXP;
            NexModel.get(event.getPlayer()).attributes = NexModel.get(event.getOriginal()).attributes;
            NexModel.get(event.getPlayer()).abilityPoints = NexModel.get(event.getOriginal()).abilityPoints;
            NexModel.get(event.getPlayer()).maxEffortPoints = NexModel.get(event.getOriginal()).maxEffortPoints;
            NexModel.get(event.getPlayer()).curEffortPoints = NexModel.get(event.getOriginal()).curEffortPoints;
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

    //Eventos para os Atributos
    @SubscribeEvent
    public static void breakSpeedBonus(PlayerEvent.BreakSpeed event){
        // Aumenta velocidade de mineração
        int speedBonus = NexModel.get(event.getPlayer()).getAttribute(ParanormalAttribute.STRENGTH);
        event.setNewSpeed(event.getOriginalSpeed() + speedBonus);
    }
    @SubscribeEvent
    public static void eatSaturationBonus(LivingEntityUseItemEvent.Finish event){
        if (event.getEntity() instanceof Player player && event.getItem().isEdible()){
            player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + NexModel.get(player).getAttribute(ParanormalAttribute.VIGOR));
        }
    }
}