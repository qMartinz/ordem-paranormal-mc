package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.abilities.ritual.CurseRitual;
import com.guga.ordemparanormal.common.ritual.*;

public class OPRituals {
    /// Rituais
    // Sangue
    public static final AbstractRitual APRIMORAMENTO_FISICO = new AprimoramentoFisico();
    public static final AbstractRitual ARMA_ATROZ = new CurseRitual("arma_atroz", OPCurses.ATROZ, 1, 2);
    public static final AbstractRitual ARMADURA_SANGUE = new ArmaduraSangue();
    public static final AbstractRitual DESCARNAR = new Descarnar();
    public static final AbstractRitual HEMOFAGIA = new Hemofagia();
    public static final AbstractRitual TRANSFERENCIA_VITAL = new TransferenciaVital();
    // Morte
    public static final AbstractRitual CICATRIZACAO = new Cicatrizacao();
    public static final AbstractRitual CONSUMIR_MANANCIAL = new ConsumirManancial();
    public static final AbstractRitual DECADENCIA = new CurseRitual("decadencia", OPCurses.DECADENTE, 1, 2);
    public static final AbstractRitual VELOCIDADE_MORTAL = new VelocidadeMortal();
    public static final AbstractRitual ESPIRAIS_DA_PERDICAO = new EspiraisDaPerdicao();
    // Energia
    public static final AbstractRitual ARMA_VELOZ = new CurseRitual("arma_veloz", OPCurses.VELOZ, 1, 2);
    public static final AbstractRitual SALTO_FANTASMA = new SaltoFantasma();
    public static final AbstractRitual TELEPORTE = new Teleporte();
    // Conhecimento
    public static final AbstractRitual AMALDICOAR_ARMA = new CurseRitual("amaldicoar_arma", OPCurses.AMALDICOADA, 1, 2);
    // Medo
    public static final AbstractRitual MEDO_TANGIVEL = new MedoTangivel();
    public static final AbstractRitual LAMINA_MEDO = new CurseRitual("lamina_do_medo", OPCurses.LAMINA_MEDO, 4, 8);

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
        registerRitual(TRANSFERENCIA_VITAL);
        // Morte
        registerRitual(CICATRIZACAO);
        registerRitual(CONSUMIR_MANANCIAL);
        registerRitual(DECADENCIA);
        registerRitual(VELOCIDADE_MORTAL);
        registerRitual(ESPIRAIS_DA_PERDICAO);
        // Energia
        registerRitual(ARMA_VELOZ);
        registerRitual(SALTO_FANTASMA);
        registerRitual(TELEPORTE);
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
