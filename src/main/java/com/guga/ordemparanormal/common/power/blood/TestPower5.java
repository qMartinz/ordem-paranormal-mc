package com.guga.ordemparanormal.common.power.blood;

import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.api.powers.power.PowerType;
import com.guga.ordemparanormal.core.registry.OPAPI;

public class TestPower5 extends PlayerPower {
    public TestPower5() {
        super("test5", true, PowerType.BLOOD, 0, OPAPI.TEST_POWER, 0, new int[]{0, 0, 0});
    }
}
