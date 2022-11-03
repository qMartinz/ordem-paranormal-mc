package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.MobEffectPower;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class PunhoEnraivecido extends MobEffectPower {
    public PunhoEnraivecido(String id, MobEffectInstance effect, ParanormalElement element, int cost, int nex, int[] attributes) {
        super(id, effect, element, cost, nex, attributes);
    }
    @Override
    public boolean canEquip(Player player) {
        IAbilitiesCap cap = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (cap == null) return false;

        return !cap.hasPower(OPPowers.PUNHO_ENRAIVECIDO_2);
    }
    @Override
    public boolean canUse(Player player) {
        IAbilitiesCap cap = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (cap == null) return false;

        return super.canUse(player) && !cap.hasPower(OPPowers.PUNHO_ENRAIVECIDO_2);
    }
}
