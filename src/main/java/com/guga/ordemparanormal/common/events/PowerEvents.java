package com.guga.ordemparanormal.common.events;

import com.guga.ordemparanormal.api.ElementDamage;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class PowerEvents {
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player && event.getEntity() instanceof LivingEntity living &&
                player.hasEffect(OPEffects.ENRAGED_FIST.get()) && !(player.getMainHandItem().getItem() instanceof TieredItem)) {
            living.hurt(ElementDamage.DANO_SANGUE, 4);
        }
    }
    @SubscribeEvent
    public static void onLivingUseItemFinish(LivingEntityUseItemEvent.Finish event){
        if (event.getEntity() instanceof Player player){
            player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(abi -> {
                if (abi.hasPower(OPPowers.DIETA_ADAPTADA) &&
                    event.getItem().getFoodProperties(player).getEffects().stream().anyMatch(pair -> pair.getFirst().getEffect() == MobEffects.HUNGER)){
                    player.removeEffect(MobEffects.HUNGER);
                    if (abi.hasPower(OPPowers.DIETA_ADAPTADA_2)) player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600));
                }
                if (abi.hasPower(OPPowers.DIETA_ADAPTADA) &&
                        event.getItem().getFoodProperties(player).getEffects().stream().anyMatch(pair -> pair.getFirst().getEffect() == MobEffects.POISON)){
                    player.removeEffect(MobEffects.POISON);
                    if (abi.hasPower(OPPowers.DIETA_ADAPTADA_2)) player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600));
                }
                if (abi.hasPower(OPPowers.DIETA_ADAPTADA) &&
                        event.getItem().getFoodProperties(player).getEffects().stream().anyMatch(pair -> pair.getFirst().getEffect() == MobEffects.CONFUSION)){
                    player.removeEffect(MobEffects.CONFUSION);
                    if (abi.hasPower(OPPowers.DIETA_ADAPTADA_2)) player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 600));
                }

            });

        }
    }
}
