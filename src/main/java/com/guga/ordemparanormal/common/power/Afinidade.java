package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;

public class Afinidade extends PlayerPower {
    public Afinidade(ParanormalElement element) {
        super("afinidade_" + element.name, false, element, 0, 10, new int[]{0, 0, 0});
    }
}
