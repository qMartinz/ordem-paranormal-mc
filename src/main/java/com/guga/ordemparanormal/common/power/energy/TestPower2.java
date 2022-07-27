package com.guga.ordemparanormal.common.power.energy;

import com.guga.ordemparanormal.api.powers.power.PowerType;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;

public class TestPower2 extends PlayerPower {
    public TestPower2() {
        super("test2", true, PowerType.ENERGY, 0, null, 0, new int[]{0, 0, 0});
    }
}
