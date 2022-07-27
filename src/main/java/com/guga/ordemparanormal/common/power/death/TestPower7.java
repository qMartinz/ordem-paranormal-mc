package com.guga.ordemparanormal.common.power.death;

import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.api.powers.power.PowerType;
import com.guga.ordemparanormal.core.registry.OPAPI;

public class TestPower7 extends PlayerPower {
    public TestPower7() {
        super("test7", true, PowerType.DEATH, 0, OPAPI.TEST_POWER_3, 0, new int[]{0, 0, 0});
    }
}
