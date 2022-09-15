package com.guga.ordemparanormal.common.power.blood;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;

public class TestPower extends PlayerPower {
    public TestPower() {
        super("test", false, ParanormalElement.SANGUE, 0, null, 1, new int[]{0, 1, 0});
    }
}
