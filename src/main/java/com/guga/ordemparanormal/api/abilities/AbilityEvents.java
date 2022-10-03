package com.guga.ordemparanormal.api.powers;

import com.guga.ordemparanormal.api.ElementDamage;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.capabilities.data.PlayerPowersProvider;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ForgeItemTagsProvider;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class PowerEvents {
    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event){
        if (event.getEntityLiving() instanceof Player player){
            player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerAbilities ->
                    playerAbilities.getPowers().forEach(power -> power.tick(player)));
        }
    }
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player && event.getSource().getEntity() instanceof LivingEntity living)
            player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerAbilities ->
                    playerAbilities.getPowers().forEach(power -> power.hurt(player, living, event.getAmount())));

        if (event.getSource().getEntity() instanceof Player player && event.getEntity() instanceof LivingEntity living)
            player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerAbilities ->
                    playerAbilities.getPowers().forEach(power -> power.attack(player, living)));

        if (event.getSource().getEntity() instanceof Player player && event.getEntity() instanceof LivingEntity living &&
            player.hasEffect(OPEffects.ENRAGED_FIST.get()) && !(player.getMainHandItem().getItem() instanceof TieredItem)) {
            living.hurt(ElementDamage.DANO_SANGUE, 4);
        }
    }
}
