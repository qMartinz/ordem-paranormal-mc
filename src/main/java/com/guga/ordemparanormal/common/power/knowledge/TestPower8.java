package com.guga.ordemparanormal.common.power.knowledge;

import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.api.powers.power.PowerType;
import com.guga.ordemparanormal.core.registry.OPPowers;

public class TestPower8 extends PlayerPower {
    public TestPower8() {
        super("test8", true, PowerType.KNOWLEDGE, 0, OPPowers.TEST_POWER_4, 0, new int[]{0, 0, 0});
    }
}
