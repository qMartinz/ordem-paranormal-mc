package com.guga.ordemparanormal.common.power.energy;

import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.api.powers.power.PowerType;
import com.guga.ordemparanormal.core.registry.OPAPI;

public class TestPower6 extends PlayerPower {
    public TestPower6() {
        super("test6", true, PowerType.ENERGY, 0, OPAPI.TEST_POWER_2, 0, new int[]{0, 0, 0});
    }
}
