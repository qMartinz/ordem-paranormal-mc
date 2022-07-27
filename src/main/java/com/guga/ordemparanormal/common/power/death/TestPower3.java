package com.guga.ordemparanormal.common.power.death;

import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.api.powers.power.PowerType;

public class TestPower3 extends PlayerPower {
    public TestPower3() {
        super("test3", true, PowerType.DEATH, 0, null, 0, new int[]{0, 0, 0});
    }
}
