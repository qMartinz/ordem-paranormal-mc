package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.common.ritual.*;

public class OPRituals {
    // Rituais
    public static final AbstractRitual DESCARNAR = new Descarnar();
    public static final AbstractRitual DECADENCIA = new Decadencia();
    public static final AbstractRitual CONSUMIR_MANANCIAL = new ConsumirManancial();
    public static final AbstractRitual ARMADURA_SANGUE = new ArmaduraSangue();
    public static final AbstractRitual CICATRIZACAO = new Cicatrizacao();
    public static final AbstractRitual ARMA_ATROZ = new ArmaAtroz();
    public static final AbstractRitual ARMA_VELOZ = new ArmaVeloz();
    public static final AbstractRitual AMALDICOAR_ARMA = new AmaldicoarArma();
    public static final AbstractRitual HEMOFAGIA = new Hemofagia();
    public static final AbstractRitual MEDO_TANGIVEL = new MedoTangivel();

    /**
     * Registra os rituais
     */
    public static void setup(){
        registerRitual(DESCARNAR);
        registerRitual(DECADENCIA);
        registerRitual(CONSUMIR_MANANCIAL);
        registerRitual(ARMADURA_SANGUE);
        registerRitual(CICATRIZACAO);
        registerRitual(ARMA_ATROZ);
        registerRitual(ARMA_VELOZ);
        registerRitual(AMALDICOAR_ARMA);
        registerRitual(HEMOFAGIA);
        registerRitual(MEDO_TANGIVEL);
    }
    public static void registerRitual(AbstractRitual ritual){
        OrdemParanormalAPI.getInstance().registerRitual(ritual.getId(), ritual);
    }

}
