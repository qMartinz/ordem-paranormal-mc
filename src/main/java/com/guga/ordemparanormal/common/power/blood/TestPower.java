package com.guga.ordemparanormal.common.power.blood;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;

public class TestPower extends PlayerPower {
    public TestPower() {
        super("test", true, ParanormalElement.BLOOD, 0, null, 0, new int[]{0, 0, 0});
    }
}
