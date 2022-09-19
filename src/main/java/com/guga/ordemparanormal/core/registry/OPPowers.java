package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.common.power.blood.TestPower;
import com.guga.ordemparanormal.common.power.blood.TestPower2;

public class OPPowers {
    // Poderes
    public static final PlayerPower TEST_POWER = new TestPower();
    public static final PlayerPower TEST_POWER_2 = new TestPower2();

    /**
     * Registra os poderes
     */
    public static void setup(){
        registerPower(TEST_POWER);
        registerPower(TEST_POWER_2);
    }
    public static void registerPower(PlayerPower power){
        OrdemParanormalAPI.getInstance().registerPower(power.getId(), power);
    }
}
