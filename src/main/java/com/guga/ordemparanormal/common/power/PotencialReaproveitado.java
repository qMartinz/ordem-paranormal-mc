package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class PotencialReaproveitado extends PlayerPower {
    public PotencialReaproveitado(String id, ParanormalElement element, int nex, int[] attributes, PlayerPower... powers) {
        super(id, false, element, 0, nex, attributes, powers);
    }
    @Override
    public float onHurt(Player player, float amount, @Nullable Entity attacker, DamageSource source) {
        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(cap -> {
            Random rand = new Random();
            if (rand.nextInt(1, 20) <= 8){
                cap.setCurrentEffort(cap.getCurrentEffort() + 1);
            }
        });
        return amount;
    }
}
