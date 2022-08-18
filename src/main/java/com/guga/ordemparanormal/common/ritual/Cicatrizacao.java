package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.powers.ElementDamage;
import com.guga.ordemparanormal.api.powers.ParanormalElement;
import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import com.guga.ordemparanormal.common.entity.ParanormalCreature;
import com.guga.ordemparanormal.core.registry.OPItems;
import com.mojang.math.Vector3f;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;

public class Cicatrizacao extends AbstractRitual {
    public Cicatrizacao() {
        super("cicatrizacao", ParanormalElement.DEATH, 1, 2, true, 3.5D, OPItems.CINZAS.get());
    }
    @Override
    public void onUseSelf(Level world, @Nullable LivingEntity caster) {
        float presence = 0;
        if (caster instanceof Player player) {
            INexCap nexCap = player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
            if (nexCap == null) return;
            presence = nexCap.getAttribute(ParanormalAttribute.PRESENCE);
        }

        float amount = 4 + presence;
        caster.setHealth(caster.getHealth() + amount);

        ServerLevel level = (ServerLevel) world;
        level.sendParticles(
                new DustParticleOptions(new Vector3f(0.25f, 0.25f, 0.25f), 0.7f),
                caster.getX(), caster.getEyeY(), caster.getZ(),
                20, 0.4d, 0.4d, 0.4d, 0d);
    }

    @Override
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster) {
        float presence = 0;
        if (caster instanceof Player player) {
            INexCap nexCap = player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
            if (nexCap == null) return;
            presence = nexCap.getAttribute(ParanormalAttribute.PRESENCE);
        }

        float amount = 4 + presence;
        LivingEntity target = (LivingEntity) rayTraceResult.getEntity();
        if (target instanceof ParanormalCreature paranormalCreature) {
            if (paranormalCreature.getMainElement() == ParanormalElement.BLOOD){
                paranormalCreature.hurt(ElementDamage.ritualDamage(caster, getElement()), amount);
            } else {
                paranormalCreature.setHealth(paranormalCreature.getHealth() + amount);
            }
        } else {
            target.setHealth(target.getHealth() + amount);
        }

        ServerLevel level = (ServerLevel) world;
        level.sendParticles(
                ParticleTypes.ASH,
                target.getX(), target.getEyeY(), target.getZ(),
                10, 0, 0, 0, 0d);
    }
}
