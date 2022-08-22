package com.guga.ordemparanormal.common.power.blood;

import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.api.powers.power.PowerType;

public class TestPower extends PlayerPower {
    public TestPower() {
        super("test", true, PowerType.BLOOD, 0, null, 0, new int[]{0, 0, 0});
    }
}
