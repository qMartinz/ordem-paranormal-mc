package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;

import static com.guga.ordemparanormal.api.ParanormalElement.SANGUE;

public class DietaAdaptada2 extends PlayerPower {
    public DietaAdaptada2() {
        super("dieta_adaptada_2", false, SANGUE, 0, 10, new int[]{0, 5, 0}, OPPowers.DIETA_ADAPTADA, OPPowers.AFINIDADE_SANGUE);
    }

    @Override
    public ItemStack onFinishUseItem(Player player, ItemStack item, ItemStack result, int duration) {
        if (item.getFoodProperties(player).getEffects().stream().anyMatch(pair -> pair.getFirst().getEffect() == MobEffects.HUNGER)){
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600));
        }
        if (item.getFoodProperties(player).getEffects().stream().anyMatch(pair -> pair.getFirst().getEffect() == MobEffects.POISON)){
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600));
        }
        if (item.getFoodProperties(player).getEffects().stream().anyMatch(pair -> pair.getFirst().getEffect() == MobEffects.CONFUSION)){
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 600));
        }
        return result;
    }
}
