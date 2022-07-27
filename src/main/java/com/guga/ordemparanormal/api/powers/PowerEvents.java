package com.guga.ordemparanormal.api.powers;

import com.guga.ordemparanormal.api.capabilities.data.PlayerPowersProvider;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class PowerEvents {
    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event){
        if (event.getEntityLiving() instanceof Player player){
            player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerAbilities -> {
                for (PlayerPower power : playerAbilities.getPowers()){
                    if (!power.isActivePower()) power.tick(player);
                }
            });
        }
    }
}
