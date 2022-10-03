package com.guga.ordemparanormal.api.abilities.power;

import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.util.PowerUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.HitResult;

public class RitualPower extends PlayerPower{
    public final AbstractRitual ritual;
    public RitualPower(String id, int nexRequired, int[] attributesRequired, AbstractRitual ritual, PlayerPower... powerRequirements) {
        super(id, true, ritual.getElement(), 0, nexRequired, attributesRequired, powerRequirements);
        this.ritual = ritual;
    }
    public AbstractRitual getRitual() {
        return ritual;
    }
    @Override
    public void onAdded(Player player) {
        IAbilitiesCap abilities = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        INexCap nex = player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
        if (abilities == null || nex == null) return;

        nex.setRitualSlots(nex.getRitualSlots() + 1);
        abilities.learnRitual(ritual);
    }
    @Override
    public void use(Player player) {
        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(cap -> {
            if (canUse(player) && cap.getPowerCooldown() == 0) {
                HitResult result = PowerUtils.rayTrace(player, ritual.getRange(), 0, false);
                ritual.onUse(result, player.level, player);

                cap.setPowerCooldown(15);
                this.onUse(player);
            }
        });
    }
}
