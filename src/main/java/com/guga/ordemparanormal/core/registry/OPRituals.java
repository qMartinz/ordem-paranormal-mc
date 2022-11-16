package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.abilities.ritual.CurseRitual;
import com.guga.ordemparanormal.common.ritual.*;

import static com.guga.ordemparanormal.api.ParanormalElement.SANGUE;

public class OPRituals {
        /// Rituais
        // Sangue
        public static final AbstractRitual APRIMORAMENTO_FISICO = new AprimoramentoFisico("aprimoramento_fisico",
                        ParanormalElement.SANGUE, 2, 4, true, 0D, true);
        public static final AbstractRitual ARMA_ATROZ = new CurseRitual("arma_atroz", OPCurses.ATROZ, 1, 2);
        public static final AbstractRitual ARMADURA_SANGUE = new ArmaduraSangue("armadura_sangue",
                        ParanormalElement.SANGUE,
                        1, 3, true, 0, true);
        public static final AbstractRitual DESCARNAR = new Descarnar("descarnar", SANGUE, 1, 2, true, 5D, true);
        public static final AbstractRitual HEMOFAGIA = new Hemofagia("hemofagia", ParanormalElement.SANGUE, 2, 4, true,
                        5D,
                        true);
        public static final AbstractRitual TRANSFERENCIA_VITAL = new TransferenciaVital("transferencia_vital",
                        ParanormalElement.SANGUE, 2, 4, true, 5D, true);
        // Morte
        public static final AbstractRitual CICATRIZACAO = new Cicatrizacao("cicatrizacao", ParanormalElement.MORTE, 1,
                        2,
                        true, 3.5D, true);
        public static final AbstractRitual CONSUMIR_MANANCIAL = new ConsumirManancial("consumir_manancial",
                        ParanormalElement.MORTE, 1, 3, true, 0D, true);
        public static final AbstractRitual DECADENCIA = new CurseRitual("decadencia", OPCurses.DECADENTE, 1, 2);
        public static final AbstractRitual VELOCIDADE_MORTAL = new VelocidadeMortal("velocidade_mortal",
                        ParanormalElement.MORTE, 2, 4, true, 0D, true);
        public static final AbstractRitual ESPIRAIS_DA_PERDICAO = new EspiraisDaPerdicao("espirais_da_perdicao",
                        ParanormalElement.MORTE, 1, 2, true, 5D, true);
        // Energia
        public static final AbstractRitual ARMA_VELOZ = new CurseRitual("arma_veloz", OPCurses.VELOZ, 1, 2);
        public static final AbstractRitual SALTO_FANTASMA = new SaltoFantasma("salto_fantasma",
                        ParanormalElement.ENERGIA,
                        3, 6, false, 15D, true);
        public static final AbstractRitual TELEPORTE = new Teleporte("teleporte", ParanormalElement.ENERGIA, 4, 8, true,
                        2D,
                        true);
        public static final AbstractRitual LUZ = new Luz("luz", ParanormalElement.ENERGIA, 1, 1, false, 5D, true);
        public static final AbstractRitual TRANSFIGURAR_TERRA = new TransfigurarTerra("transfigurar_terra",
                        ParanormalElement.ENERGIA, 3, 6, false, 5D, true);
        // Conhecimento
        public static final AbstractRitual AMALDICOAR_ARMA = new CurseRitual("amaldicoar_arma", OPCurses.AMALDICOADA, 1,
                        2);
        public static final AbstractRitual PERTURBACAO = new Perturbacao("perturbacao", ParanormalElement.CONHECIMENTO,
                        1,
                        1, true, 3D, true);
        public static final AbstractRitual INEXISTIR = new Inexistir("inexistir", ParanormalElement.CONHECIMENTO, 4, 8,
                        true, 3D, true);
        public static final AbstractRitual ALTERAR_MEMORIA = new AlterarMemoria("alterar_memoria",
                        ParanormalElement.CONHECIMENTO, 3, 6, true, 3D, true);
        // Medo
        public static final AbstractRitual MEDO_TANGIVEL = new MedoTangivel("medo_tangivel", ParanormalElement.MEDO, 4,
                        8,
                        true, 0d, false);
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
                registerRitual(TRANSFIGURAR_TERRA);
                // Conhecimento
                registerRitual(AMALDICOAR_ARMA);
                registerRitual(PERTURBACAO);
                registerRitual(INEXISTIR);
                registerRitual(ALTERAR_MEMORIA);
                // Medo
                registerRitual(MEDO_TANGIVEL);
                registerRitual(LAMINA_MEDO);
        }

        public static void registerRitual(AbstractRitual ritual) {
                OrdemParanormalAPI.getInstance().registerRitual(ritual.getId(), ritual);
        }

}
