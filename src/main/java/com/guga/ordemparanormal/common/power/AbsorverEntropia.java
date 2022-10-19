package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.ParanormalEffectsProvider;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import static com.guga.ordemparanormal.api.ParanormalElement.MORTE;

public class AbsorverEntropia extends PlayerPower {
    public AbsorverEntropia() {
        super("absorver_entropia", false, MORTE, 0, 11, new int[]{3, 0, 0}, OPPowers.PERIMETRO_ESPIRAL);
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
