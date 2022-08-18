package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import com.guga.ordemparanormal.common.power.blood.TestPower;
import com.guga.ordemparanormal.common.power.blood.TestPower5;
import com.guga.ordemparanormal.common.power.death.TestPower7;
import com.guga.ordemparanormal.common.power.energy.TestPower2;
import com.guga.ordemparanormal.common.power.death.TestPower3;
import com.guga.ordemparanormal.common.power.energy.TestPower6;
import com.guga.ordemparanormal.common.power.knowledge.TestPower4;
import com.guga.ordemparanormal.common.power.knowledge.TestPower8;
import com.guga.ordemparanormal.common.ritual.*;

public class OPAPI {
    public static final AbstractRitual DESCARNAR = new Descarnar();
    public static final AbstractRitual DECADENCIA = new Decadencia();
    public static final AbstractRitual CONSUMIR_MANANCIAL = new ConsumirManancial();
    public static final AbstractRitual ARMADURA_SANGUE = new ArmaduraSangue();
    public static final AbstractRitual CICATRIZACAO = new Cicatrizacao();
    public static final PlayerPower TEST_POWER = new TestPower();
    public static final PlayerPower TEST_POWER_2 = new TestPower2();
    public static final PlayerPower TEST_POWER_3 = new TestPower3();
    public static final PlayerPower TEST_POWER_4 = new TestPower4();
    public static final PlayerPower TEST_POWER_5 = new TestPower5();
    public static final PlayerPower TEST_POWER_6 = new TestPower6();
    public static final PlayerPower TEST_POWER_7 = new TestPower7();
    public static final PlayerPower TEST_POWER_8 = new TestPower8();
    public static void setup(){
        registerRitual(DESCARNAR);
        registerRitual(DECADENCIA);
        registerRitual(CONSUMIR_MANANCIAL);
        registerRitual(ARMADURA_SANGUE);
        registerRitual(CICATRIZACAO);
        registerPower(TEST_POWER);
        registerPower(TEST_POWER_2);
        registerPower(TEST_POWER_3);
        registerPower(TEST_POWER_4);
        registerPower(TEST_POWER_5);
        registerPower(TEST_POWER_6);
        registerPower(TEST_POWER_7);
        registerPower(TEST_POWER_8);
    }
    public static void registerRitual(AbstractRitual ritual){
        OrdemParanormalAPI.getInstance().registerRitual(ritual.getId(), ritual);
    }
    public static void registerPower(PlayerPower power){
        OrdemParanormalAPI.getInstance().registerPower(power.getId(), power);
    }
}
