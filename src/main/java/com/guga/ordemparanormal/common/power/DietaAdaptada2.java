package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class DietaAdaptada2 extends PlayerPower {
    public DietaAdaptada2(ResourceLocation id, ParanormalElement element, int nex, int[] attributes, PlayerPower... powers) {
        super(id, false, element, 0, nex, attributes, powers);
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
