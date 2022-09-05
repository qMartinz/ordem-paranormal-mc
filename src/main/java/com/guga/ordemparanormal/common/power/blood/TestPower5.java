package com.guga.ordemparanormal.common.power.blood;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPPowers;

public class TestPower5 extends PlayerPower {
    public TestPower5() {
        super("test5", true, ParanormalElement.DEATH, 0, OPPowers.TEST_POWER, 0, new int[]{0, 0, 0});
    }
}
