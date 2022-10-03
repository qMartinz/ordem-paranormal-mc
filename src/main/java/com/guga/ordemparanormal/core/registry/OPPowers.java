package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.abilities.power.RitualPower;
import com.guga.ordemparanormal.common.power.*;

import static com.guga.ordemparanormal.api.ParanormalElement.SANGUE;

public class OPPowers {
    // Poderes
    public static final PlayerPower AFINIDADE_SANGUE = new Afinidade(ParanormalElement.SANGUE);
    public static final PlayerPower AFINIDADE_MORTE = new Afinidade(ParanormalElement.MORTE);
    public static final PlayerPower AFINIDADE_ENERGIA = new Afinidade(ParanormalElement.ENERGIA);
    public static final PlayerPower AFINIDADE_CONHECIMENTO = new Afinidade(ParanormalElement.CONHECIMENTO);
    public static final PlayerPower SANGUE_FERRO = new SangueFerro();
    public static final PlayerPower SANGUE_FERRO_2 = new SangueFerro2();
    public static final PlayerPower PUNHO_ENRAIVECIDO = new PunhoEnraivecido();
    public static final PlayerPower PUNHO_ENRAIVECIDO_2 = new PunhoEnraivecido2();
    public static final PlayerPower ADRENALINA = new Adrenalina();
    public static final PlayerPower ADRENALINA_2 = new Adrenalina2();
    public static final PlayerPower SANGUE_VIVO = new SangueVivo();
    public static final PlayerPower SANGUE_VISCERAL = new SangueVisceral();
    public static final PlayerPower FLAGELO = new Flagelo();
    public static final PlayerPower ABSORVER_AGONIA = new AbsorverAgonia();
    public static final PlayerPower DIETA_ADAPTADA = new PlayerPower("dieta_adaptada", false, SANGUE, 0, 7, new int[]{0, 3, 0}, SANGUE_VIVO);
    public static final PlayerPower DIETA_ADAPTADA_2 = new PlayerPower("dieta_adaptada_2", false, SANGUE, 0, 10, new int[]{0, 5, 0}, DIETA_ADAPTADA, AFINIDADE_SANGUE);
    public static final PlayerPower POTENCIAL_APRIMORADO = new PotencialAprimorado();
    public static final PlayerPower VAMPIRISMO = new Vampirismo();
    public static final PlayerPower VAMPIRISMO_2 = new PlayerPower("vampirismo_2", false, ParanormalElement.SANGUE, 0, 10, new int[]{4, 0, 0}, VAMPIRISMO, AFINIDADE_SANGUE);
    public static final PlayerPower MEDO_TANGIVEL = new RitualPower("medo_tangivel", 20, new int[]{0, 0, 4}, OPRituals.MEDO_TANGIVEL, ABSORVER_AGONIA, AFINIDADE_SANGUE);

    /**
     * Registra os poderes
     */
    public static void setup(){
        registerPower(AFINIDADE_CONHECIMENTO);
        registerPower(AFINIDADE_SANGUE);
        registerPower(AFINIDADE_MORTE);
        registerPower(AFINIDADE_ENERGIA);

        // Sangue
        registerPower(SANGUE_FERRO);
        registerPower(SANGUE_FERRO_2);
        registerPower(PUNHO_ENRAIVECIDO);
        registerPower(PUNHO_ENRAIVECIDO_2);
        registerPower(ADRENALINA);
        registerPower(ADRENALINA_2);
        registerPower(SANGUE_VIVO);
        registerPower(SANGUE_VISCERAL);
        registerPower(FLAGELO);
        registerPower(ABSORVER_AGONIA);
        registerPower(DIETA_ADAPTADA);
        registerPower(DIETA_ADAPTADA_2);
        registerPower(VAMPIRISMO);
        registerPower(VAMPIRISMO_2);
        registerPower(MEDO_TANGIVEL);

        // Energia

        // Morte
        registerPower(POTENCIAL_APRIMORADO);

        // Conhecimento

        // Medo
    }
    public static void registerPower(PlayerPower power){
        OrdemParanormalAPI.getInstance().registerPower(power.getId(), power);
    }
}
