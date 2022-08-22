package com.guga.ordemparanormal.common.power.knowledge;

import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.api.powers.power.PowerType;

public class TestPower4 extends PlayerPower {
    public TestPower4() {
        super("test4", true, PowerType.KNOWLEDGE, 0, null, 0, new int[]{0, 0, 0});
    }
}
