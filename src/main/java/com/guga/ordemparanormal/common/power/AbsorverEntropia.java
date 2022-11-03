package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.ParanormalEffectsProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class AbsorverEntropia extends PlayerPower {
    public AbsorverEntropia(String id, ParanormalElement element, int nex, int[] attributes, PlayerPower... powers) {
        super(id, false, element, 0, nex, attributes, powers);
    }
    @Override
    public float onAttack(Player player, float amount, LivingEntity target, DamageSource source) {
        player.getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).ifPresent(cap -> {
            if (amount > target.getHealth()) {
                cap.setDeathHealthPoints((int) (cap.getDeathHealthPoints() + target.getMaxHealth() / 5 + 1), player.getMaxHealth());
            }
        });
        return amount;
    }
}
