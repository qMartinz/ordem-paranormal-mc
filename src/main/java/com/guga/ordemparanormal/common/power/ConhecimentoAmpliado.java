package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import net.minecraft.world.entity.player.Player;

public class ConhecimentoAmpliado extends PlayerPower {
    public ConhecimentoAmpliado(String id, ParanormalElement element, int nex, int[] attributes, PlayerPower... powers) {
        super(id, false, element, 0, nex, attributes, powers);
    }
    @Override
    public void onAdded(Player player) {
        INexCap nex = player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
        if (nex == null) return;

        nex.setRitualSlots(nex.getRitualSlots() + 2);
    }
}
