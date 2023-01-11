package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class SacrificarEntropia extends PlayerPower {
    public SacrificarEntropia(ResourceLocation id, ParanormalElement element, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements) {
        super(id, false, element, 0, nexRequired, attributesRequired, powerRequirements);
    }
    @Override
    public float onHurt(Player player, float amount, @Nullable Entity attacker, DamageSource source) {
        INexCap cap = player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
        IAbilitiesCap cap2 = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);

        if (source instanceof ParanormalDamageSource pSource && pSource.element == ParanormalElement.MORTE &&
            cap != null && cap2 != null){
            cap.setCurrentEffort(cap.getCurrentEffort() + (amount / (cap2.hasPower(OPPowers.SACRIFICAR_ENTROPIA_2) ? 4 : 1)));
        }
        return super.onHurt(player, amount, attacker, source);
    }
}
