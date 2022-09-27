package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.common.power.*;

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

        // Energia

        // Morte

        // Conhecimento

        // Medo
    }
    public static void registerPower(PlayerPower power){
        OrdemParanormalAPI.getInstance().registerPower(power.getId(), power);
    }
}
