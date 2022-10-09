package com.guga.ordemparanormal.api.attributes;

import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class AttributeEvents {
    @SubscribeEvent
    public static void breakSpeedBonus(PlayerEvent.BreakSpeed event){
        // Aumenta velocidade de mineração
        int speedBonus = event.getEntity().getCapability(PlayerNexProvider.PLAYER_NEX).resolve().get().getAttribute(ParanormalAttribute.STRENGTH);
        event.setNewSpeed(event.getOriginalSpeed() + speedBonus);
    }
    @SubscribeEvent
    public static void eatSaturationBonus(LivingEntityUseItemEvent.Finish event){
        if (event.getEntity() instanceof Player player && event.getItem().isEdible()){
            player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + player.getCapability(PlayerNexProvider.PLAYER_NEX).resolve().get().getAttribute(ParanormalAttribute.VIGOR));
        }
    }
}
