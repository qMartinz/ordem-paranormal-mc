package com.guga.ordemparanormal.common.power.blood;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPPowers;

public class TestPower2 extends PlayerPower {
    public TestPower2() {
        super("test2", true, ParanormalElement.SANGUE, 0, OPPowers.TEST_POWER, 1, new int[]{0, 1, 0});
    }
}
