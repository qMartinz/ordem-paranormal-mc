package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CampoProtetor extends PlayerPower {
    public CampoProtetor(ResourceLocation id, ParanormalElement element, int nex, int[] attributes, PlayerPower... powers) {
        super(id, false, element, 0, nex, attributes, powers);
    }
    @Override
    public float onShieldBlock(Player player, @Nullable Entity attacker, DamageSource source, float originalBlockedDamage, float blockedDamage) {
        Random rand = new Random();
        player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(cap -> {
            if (rand.nextInt(1, 20) <= (cap.hasPower(OPPowers.CAMPO_PROTETOR_2) ? 6 : 2) && attacker != null) {
                for (int i = 0; i < 360; i++) {
                    if (i % 20 == 0) {
                        if (player.level instanceof ServerLevel level) level.sendParticles(
                                new DustParticleOptions(this.getElement().getParticleVec3fColor(), 0.7f),
                                player.getX() + Math.cos(i),
                                player.getY() + player.getEyeHeight() / 2f,
                                player.getZ() + Math.sin(i),
                                0, 0, 0, 0, 1);
                    }
                }


                attacker.setDeltaMovement(
                        Math.cos(player.getXRot()) * (cap.hasPower(OPPowers.CAMPO_PROTETOR_2) ? 1.5 : 1),
                        0.2d * (cap.hasPower(OPPowers.CAMPO_PROTETOR_2) ? 1.5 : 1),
                        Math.sin(player.getXRot()) * (cap.hasPower(OPPowers.CAMPO_PROTETOR_2) ? 1.5 : 1));
                attacker.setSecondsOnFire(cap.hasPower(OPPowers.CAMPO_PROTETOR_2) ? 5 : 3);
            }
        });
        return super.onShieldBlock(player, attacker, source, originalBlockedDamage, blockedDamage);
    }
}
