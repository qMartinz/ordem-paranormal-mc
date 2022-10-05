package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.common.ritual.*;

public class OPRituals {
    /// Rituais
    // Sangue
    public static final AbstractRitual APRIMORAMENTO_FISICO = new AprimoramentoFisico();
    public static final AbstractRitual ARMA_ATROZ = new ArmaAtroz();
    public static final AbstractRitual ARMADURA_SANGUE = new ArmaduraSangue();
    public static final AbstractRitual DESCARNAR = new Descarnar();
    public static final AbstractRitual HEMOFAGIA = new Hemofagia();
    // Morte
    public static final AbstractRitual CICATRIZACAO = new Cicatrizacao();
    public static final AbstractRitual CONSUMIR_MANANCIAL = new ConsumirManancial();
    public static final AbstractRitual DECADENCIA = new Decadencia();
    public static final AbstractRitual VELOCIDADE_MORTAL = new VelocidadeMortal();
    // Energia
    public static final AbstractRitual ARMA_VELOZ = new ArmaVeloz();
    // Conhecimento
    public static final AbstractRitual AMALDICOAR_ARMA = new AmaldicoarArma();
    // Medo
    public static final AbstractRitual MEDO_TANGIVEL = new MedoTangivel();
    public static final AbstractRitual LAMINA_MEDO = new LaminaMedo();

    /**
     * Registra os rituais
     */
    public static void setup() {
        // Sangue
        registerRitual(APRIMORAMENTO_FISICO);
        registerRitual(ARMA_ATROZ);
        registerRitual(ARMADURA_SANGUE);
        registerRitual(DESCARNAR);
        registerRitual(HEMOFAGIA);
        // Morte
        registerRitual(CICATRIZACAO);
        registerRitual(CONSUMIR_MANANCIAL);
        registerRitual(DECADENCIA);
        registerRitual(VELOCIDADE_MORTAL);
        // Energia
        registerRitual(ARMA_VELOZ);
        // Conhecimento
        registerRitual(AMALDICOAR_ARMA);
        // Medo
        registerRitual(MEDO_TANGIVEL);
        registerRitual(LAMINA_MEDO);
    }

    public static void registerRitual(AbstractRitual ritual) {
        OrdemParanormalAPI.getInstance().registerRitual(ritual.getId(), ritual);
    }

}
