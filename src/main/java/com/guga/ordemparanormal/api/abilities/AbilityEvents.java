package com.guga.ordemparanormal.api.abilities;

import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class AbilityEvents {
    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event){
        if (event.getEntityLiving() instanceof Player player){
            player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities ->
                    playerAbilities.getPowers().forEach(power -> power.onTick(player)));
        }
    }
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player && event.getSource().getEntity() instanceof LivingEntity living)
            player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities ->
                    playerAbilities.getPowers().forEach(power -> power.onHurt(player, living, event.getAmount())));

        if (event.getSource().getEntity() instanceof Player player && event.getEntity() instanceof LivingEntity living)
            player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities ->
                    playerAbilities.getPowers().forEach(power -> power.onAttack(player, living)));
    }
}
