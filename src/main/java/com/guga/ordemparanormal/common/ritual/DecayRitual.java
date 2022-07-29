package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.powers.ElementDamage;
import com.guga.ordemparanormal.api.powers.ParanormalElement;
import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.guga.ordemparanormal.core.registry.OPItems;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;

public class DecayRitual extends AbstractRitual {
    public DecayRitual() {
        super("decay", ParanormalElement.DEATH, 1, 2, OPItems.CINZAS.get());
    }
    @Override
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, @Nullable LivingEntity caster) {
        LivingEntity target = (LivingEntity) rayTraceResult.getEntity();
        float presence = 0;
        if (caster instanceof Player player) {
            INexCap nexCap = player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
            if (nexCap == null) return;
            presence = nexCap.getAttribute(ParanormalAttribute.PRESENCE);
        }
        
        float amount = 
                ElementDamage.isEntityWeakTo(target, ElementDamage.DEATH_DAMAGE) ? 10f + presence*2f :
                        ElementDamage.isEntityResistant(target, ElementDamage.DEATH_DAMAGE) ? 2.5f + presence/2f :
                                5f + presence;
        
        target.hurt(ElementDamage.ritualDamage(caster, ParanormalElement.DEATH), amount);
        target.level.playSound(null, target.blockPosition(), SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.PLAYERS, 2f, 1f);
        
        ServerLevel level = (ServerLevel) world;
        
        level.sendParticles(
                ParticleTypes.ASH,
                target.getX(), target.getEyeY(), target.getZ(), 
                10, 0, 0, 0, 0d);
        
        level.sendParticles(ParticleTypes.CRIT, 
                target.getX(), target.getEyeY(), target.getX(), 
                4, 0, 0, 0, 0d);
        
        MobEffectInstance effect = new MobEffectInstance(OPEffects.DECAY.get(), 100, 1, false, false);
        target.addEffect(effect, caster);
    }
}
