package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.common.ritual.CurseRitual;
import com.guga.ordemparanormal.common.ritual.*;
import net.minecraft.resources.ResourceLocation;

import static com.guga.ordemparanormal.api.ParanormalElement.*;
import static com.guga.ordemparanormal.core.OrdemParanormal.MOD_ID;

public class OPRituals {
        /// Rituais
        // Sangue
        public static final AprimoramentoFisico APRIMORAMENTO_FISICO = new AprimoramentoFisico(
                        new ResourceLocation(MOD_ID, "aprimoramento_fisico"), SANGUE, 2, 4, 0D,
                        true);
        public static final CurseRitual ARMA_ATROZ = new CurseRitual(new ResourceLocation(MOD_ID, "arma_atroz"),
                        OPCurses.ATROZ, 1, 2);
        public static final ArmaduraSangue ARMADURA_SANGUE = new ArmaduraSangue(
                        new ResourceLocation(MOD_ID, "armadura_sangue"), SANGUE,
                        1, 3, 0, true);
        public static final Descarnar DESCARNAR = new Descarnar(new ResourceLocation(MOD_ID, "descarnar"), SANGUE,
                        1, 2, 7.5D, true);
        public static final Hemofagia HEMOFAGIA = new Hemofagia(new ResourceLocation(MOD_ID, "hemofagia"),
                        SANGUE, 2, 4, 7.5D,
                        true);
        public static final TransferenciaVital TRANSFERENCIA_VITAL = new TransferenciaVital(
                        new ResourceLocation(MOD_ID, "transferencia_vital"), SANGUE, 2, 4, 7.5D,
                        true);
        // Morte
        public static final Cicatrizacao CICATRIZACAO = new Cicatrizacao(new ResourceLocation(MOD_ID, "cicatrizacao"),
                        ParanormalElement.MORTE, 1, 2,
                7.5D, true);
        public static final ConsumirManancial CONSUMIR_MANANCIAL = new ConsumirManancial(
                        new ResourceLocation(MOD_ID, "consumir_manancial"), MORTE, 1, 3, 0D,
                        true);
        public static final CurseRitual DECADENCIA = new CurseRitual(new ResourceLocation(MOD_ID, "decadencia"),
                        OPCurses.DECADENTE, 1, 2);
        public static final VelocidadeMortal VELOCIDADE_MORTAL = new VelocidadeMortal(
                        new ResourceLocation(MOD_ID, "velocidade_mortal"), MORTE, 2, 4, 0D,
                        true);
        public static final EspiraisDaPerdicao ESPIRAIS_DA_PERDICAO = new EspiraisDaPerdicao(
                        new ResourceLocation(MOD_ID, "espirais_da_perdicao"), MORTE, 1, 2, 7.5D,
                        true);
        public static final ZerarEntropia ZERAR_ENTROPIA = new ZerarEntropia(
                        new ResourceLocation(MOD_ID, "zerar_entropia"), MORTE, 3, 6, 7.5D,
                        true);
        public static final EcoEspiral ECO_ESPIRAL = new EcoEspiral(
                        new ResourceLocation(MOD_ID, "eco_espiral"), MORTE, 2, 4, 7.5D,
                        true);
        // Energia
        public static final CurseRitual ARMA_VELOZ = new CurseRitual(new ResourceLocation(MOD_ID, "arma_veloz"),
                        OPCurses.VELOZ, 1, 2);
        public static final SaltoFantasma SALTO_FANTASMA = new SaltoFantasma(
                        new ResourceLocation(MOD_ID, "salto_fantasma"), ENERGIA, 3, 6, 17.5D,
                        true);
        public static final Teleporte TELEPORTE = new Teleporte(new ResourceLocation(MOD_ID, "teleporte"),
                        ENERGIA, 4, 8, 2D, true);
        public static final Luz LUZ = new Luz(new ResourceLocation(MOD_ID, "luz"), ENERGIA,
                        1, 1, 7.5D, true);
        public static final TransfigurarTerra TRANSFIGURAR_TERRA = new TransfigurarTerra(
                        new ResourceLocation(MOD_ID, "transfigurar_terra"), ENERGIA, 3, 6, 7.5D,
                        true);
        // Conhecimento
        public static final CurseRitual AMALDICOAR_ARMA = new CurseRitual(
                        new ResourceLocation(MOD_ID, "amaldicoar_arma"), OPCurses.AMALDICOADA, 1, 2);
        public static final Perturbacao PERTURBACAO = new Perturbacao(new ResourceLocation(MOD_ID, "perturbacao"),
                        CONHECIMENTO, 1, 1, 7.5D, true);
        public static final Inexistir INEXISTIR = new Inexistir(new ResourceLocation(MOD_ID, "inexistir"),
                        CONHECIMENTO, 4, 8, 7.5D, true);
        public static final AlterarMemoria ALTERAR_MEMORIA = new AlterarMemoria(
                        new ResourceLocation(MOD_ID, "alterar_memoria"), CONHECIMENTO, 3, 6, 7.5D, true);
        // Medo
        public static final MedoTangivel MEDO_TANGIVEL = new MedoTangivel(
                        new ResourceLocation(MOD_ID, "medo_tangivel"), MEDO, 4, 8, true, 0d, false);
        public static final CurseRitual LAMINA_MEDO = new CurseRitual(new ResourceLocation(MOD_ID, "lamina_do_medo"),
                        OPCurses.LAMINA_MEDO, 4, 8);
        public static final Cineraria CINERARIA = new Cineraria(
                        new ResourceLocation(MOD_ID, "cineraria"), MEDO, 1, 2, 0d, false);
        public static final RejeitarNevoa REJEITAR_NEVOA = new RejeitarNevoa(
                        new ResourceLocation(MOD_ID, "rejeitar_nevoa"), MEDO, 2, 4, 0d, false);
        public static final Purgatorio PURGATORIO = new Purgatorio(new ResourceLocation(MOD_ID, "purgatorio"), SANGUE,
                3, 6, 7.5D, true);

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
                registerRitual(PURGATORIO);
                // Morte
                registerRitual(CICATRIZACAO);
                registerRitual(CONSUMIR_MANANCIAL);
                registerRitual(DECADENCIA);
                registerRitual(VELOCIDADE_MORTAL);
                registerRitual(ESPIRAIS_DA_PERDICAO);
                registerRitual(ZERAR_ENTROPIA);
                registerRitual(ECO_ESPIRAL);
                // Energia
                registerRitual(ARMA_VELOZ);
                registerRitual(SALTO_FANTASMA);
                registerRitual(TELEPORTE);
                registerRitual(LUZ);
                registerRitual(TRANSFIGURAR_TERRA);
                // Conhecimento
                registerRitual(AMALDICOAR_ARMA);
                registerRitual(PERTURBACAO);
                registerRitual(INEXISTIR);
                registerRitual(ALTERAR_MEMORIA);
                // Medo
                registerRitual(MEDO_TANGIVEL);
                registerRitual(LAMINA_MEDO);
                registerRitual(CINERARIA);
                registerRitual(REJEITAR_NEVOA);
        }

        public static void registerRitual(AbstractRitual ritual) {
                OrdemParanormalAPI.getInstance().registerRitual(ritual.getId(), ritual);
        }

}
