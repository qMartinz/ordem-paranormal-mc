package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.powers.ParanormalElement;
import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.registry.OPItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ConsumeLifeforce extends AbstractRitual {
    public ConsumeLifeforce() {
        super("consume_lifeforce", ParanormalElement.DEATH, 1, 3, false, 0D, OPItems.CINZAS.get());
    }
    @Override
    public void onUseSelf(Level world, @Nullable LivingEntity caster) {
        float presence = 0;
        if (caster instanceof Player player) {
            INexCap nexCap = player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
            if (nexCap == null) return;
            presence = nexCap.getAttribute(ParanormalAttribute.PRESENCE);
        }

        ServerLevel level = (ServerLevel) world;
        level.sendParticles(
                ParticleTypes.ASH,
                caster.getX(), caster.getEyeY(), caster.getZ(),
                10, 0, 0, 0, 0d);
    }
}
