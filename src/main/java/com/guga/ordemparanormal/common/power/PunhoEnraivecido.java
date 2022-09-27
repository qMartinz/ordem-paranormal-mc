package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.capabilities.data.IPowerCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerPowersProvider;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class PunhoEnraivecido extends PlayerPower {
    public PunhoEnraivecido() {
        super("punho_enraivecido", true, ParanormalElement.SANGUE, 2, 1, new int[]{1, 0, 0});
    }
    @Override
    public boolean canEquip(Player player) {
        IPowerCap cap = player.getCapability(PlayerPowersProvider.PLAYER_POWERS).orElse(null);
        if (cap == null) return false;

        return !cap.hasPower(OPPowers.PUNHO_ENRAIVECIDO_2);
    }
    @Override
    public boolean canUse(Player player) {
        IPowerCap cap = player.getCapability(PlayerPowersProvider.PLAYER_POWERS).orElse(null);
        if (cap == null) return false;

        return !cap.hasPower(OPPowers.PUNHO_ENRAIVECIDO_2);
    }
    @Override
    public void use(Player player) {
        super.use(player);
        if (!player.hasEffect(OPEffects.ENRAGED_FIST.get()))
            player.addEffect(new MobEffectInstance(OPEffects.ENRAGED_FIST.get(), 1200, 0, false, false));
    }
}
