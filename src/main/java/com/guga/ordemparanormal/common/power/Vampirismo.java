package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.paranormaldamage.EntityParanormalDamageSource;
import com.guga.ordemparanormal.api.util.PowerUtils;
import com.guga.ordemparanormal.core.registry.OPPowers;
import com.guga.ordemparanormal.core.registry.OPSounds;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class Vampirismo extends PlayerPower {
    public Vampirismo(ResourceLocation id, ParanormalElement element, int cost, int nex, int[] attributes, PlayerPower... powers) {
        super(id, true, element, cost, nex, attributes, powers);
    }
    @Override
    public void onUse(Player player) {
        HitResult result = PowerUtils.rayTrace(player, 5d, 0, false);
        IAbilitiesCap cap = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (cap == null) return;

        if (result instanceof EntityHitResult entityHitResult && entityHitResult.getEntity() instanceof LivingEntity target) {
            target.hurt(new EntityParanormalDamageSource("vampirismoPower", player).setElement(this.getElement()), 3 * (cap.hasPower(OPPowers.VAMPIRISMO_2) ? 2 : 1));

            if (player.level instanceof ServerLevel level) level.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()),
                    target.getX(), target.getEyeY(), target.getZ(),
                    4, 0d, 0d, 0d, 0d);

            player.level.playSound(null, player.getX(), player.getEyeY(), player.getZ(), OPSounds.VAMPIRISMO_USED.get(), SoundSource.PLAYERS, 1f, 1f);

            player.heal(3 * (cap.hasPower(OPPowers.VAMPIRISMO_2) ? 2 : 1));
            if (cap.hasPower(OPPowers.VAMPIRISMO_2)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300));
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300));
            }
        }
    }
}
