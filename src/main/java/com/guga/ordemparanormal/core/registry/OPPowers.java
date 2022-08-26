package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.common.power.blood.TestPower;
import com.guga.ordemparanormal.common.power.blood.TestPower5;
import com.guga.ordemparanormal.common.power.death.TestPower3;
import com.guga.ordemparanormal.common.power.death.TestPower7;
import com.guga.ordemparanormal.common.power.energy.TestPower2;
import com.guga.ordemparanormal.common.power.energy.TestPower6;
import com.guga.ordemparanormal.common.power.knowledge.TestPower4;
import com.guga.ordemparanormal.common.power.knowledge.TestPower8;

public class OPPowers {
    // Poderes
    public static final PlayerPower TEST_POWER = new TestPower();
    public static final PlayerPower TEST_POWER_2 = new TestPower2();
    public static final PlayerPower TEST_POWER_3 = new TestPower3();
    public static final PlayerPower TEST_POWER_4 = new TestPower4();
    public static final PlayerPower TEST_POWER_5 = new TestPower5();
    public static final PlayerPower TEST_POWER_6 = new TestPower6();
    public static final PlayerPower TEST_POWER_7 = new TestPower7();
    public static final PlayerPower TEST_POWER_8 = new TestPower8();

    /**
     * Registra os poderes
     */
    public static void setup(){
        registerPower(OPPowers.TEST_POWER);
        registerPower(OPPowers.TEST_POWER_2);
        registerPower(OPPowers.TEST_POWER_3);
        registerPower(OPPowers.TEST_POWER_4);
        registerPower(OPPowers.TEST_POWER_5);
        registerPower(OPPowers.TEST_POWER_6);
        registerPower(OPPowers.TEST_POWER_7);
        registerPower(OPPowers.TEST_POWER_8);
    }
    public static void registerPower(PlayerPower power){
        OrdemParanormalAPI.getInstance().registerPower(power.getId(), power);
    }
}
