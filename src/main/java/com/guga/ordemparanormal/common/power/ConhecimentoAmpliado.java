package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import net.minecraft.world.entity.player.Player;

public class ConhecimentoAmpliado extends PlayerPower {
    public ConhecimentoAmpliado() {
        super("conhecimento_ampliado", false, ParanormalElement.CONHECIMENTO, 0, 1, new int[]{0, 0, 1});
    }
    @Override
    public void onAdded(Player player) {
        INexCap nex = player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
        if (nex == null) return;

        nex.setRitualSlots(nex.getRitualSlots() + 1);
    }
}
