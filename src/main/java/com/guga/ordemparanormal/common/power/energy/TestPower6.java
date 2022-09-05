package com.guga.ordemparanormal.common.power.energy;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPPowers;

public class TestPower6 extends PlayerPower {
    public TestPower6() {
        super("test6", true, ParanormalElement.DEATH, 0, OPPowers.TEST_POWER_2, 0, new int[]{0, 0, 0});
    }
}
