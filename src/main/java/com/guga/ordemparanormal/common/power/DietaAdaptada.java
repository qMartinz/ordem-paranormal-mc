package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;

import static com.guga.ordemparanormal.api.ParanormalElement.SANGUE;

public class DietaAdaptada extends PlayerPower {
    public DietaAdaptada() {
        super("dieta_adaptada", false, SANGUE, 0, 7, new int[]{0, 3, 0}, OPPowers.SANGUE_VIVO);
    }

    @Override
    public ItemStack onFinishUseItem(Player player, ItemStack item, ItemStack result, int duration) {
        if (item.getFoodProperties(player).getEffects().stream().anyMatch(pair -> pair.getFirst().getEffect() == MobEffects.HUNGER)){
            player.removeEffect(MobEffects.HUNGER);
        }
        if (item.getFoodProperties(player).getEffects().stream().anyMatch(pair -> pair.getFirst().getEffect() == MobEffects.POISON)){
            player.removeEffect(MobEffects.POISON);
        }
        if (item.getFoodProperties(player).getEffects().stream().anyMatch(pair -> pair.getFirst().getEffect() == MobEffects.CONFUSION)){
            player.removeEffect(MobEffects.CONFUSION);
        }
        return result;
    }
}
