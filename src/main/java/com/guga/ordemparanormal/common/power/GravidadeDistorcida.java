package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.MobEffectPower;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import static com.guga.ordemparanormal.api.ParanormalElement.ENERGIA;

public class GravidadeDistorcida extends MobEffectPower {
    public GravidadeDistorcida() {
        super("gravidade_distorcida",
                new MobEffectInstance(OPEffects.DISTORTED_GRAVITY.get(), 600, 0),
                ENERGIA, 3, 1, new int[]{0, 1, 0});
    }
    @Override
    public boolean canEquip(Player player) {
        IAbilitiesCap cap = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (cap == null) return false;

        return !cap.hasPower(OPPowers.GRAVIDADE_DISTORCIDA_2);
    }
    @Override
    public boolean canUse(Player player) {
        IAbilitiesCap cap = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (cap == null) return false;

        return super.canUse(player) && !cap.hasPower(OPPowers.GRAVIDADE_DISTORCIDA_2);
    }
}
