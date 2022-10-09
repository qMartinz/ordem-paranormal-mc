package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPPowers;

public class PunhoEnraivecido2 extends PlayerPower {
    public PunhoEnraivecido2() {
        super("punho_enraivecido_2", false, ParanormalElement.SANGUE, 0, 10, new int[]{5, 0, 0}, OPPowers.PUNHO_ENRAIVECIDO, OPPowers.AFINIDADE_SANGUE);
    }
}
