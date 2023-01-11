package com.guga.ordemparanormal.client.screen.powerscreen;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.client.screen.PowerScreen;
import com.guga.ordemparanormal.client.screen.buttons.PowerButton;
import com.guga.ordemparanormal.core.registry.OPPowers;

public class DeathPowerScreen extends PowerScreen {
    public DeathPowerScreen() {
        super(ParanormalElement.MORTE);
    }

    @Override
    public void initContents() {
        int tabWidth = this.width - 41;
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 - 10, OPPowers.AFINIDADE_MORTE));
        addPowerIcon(new PowerButton(tabWidth/2 + 67, height/2 - 10, OPPowers.POTENCIAL_APRIMORADO));
        addPowerIcon(new PowerButton(tabWidth/2 + 67, height/2 - 80, OPPowers.POTENCIAL_APRIMORADO_2));
        addPowerIcon(new PowerButton(tabWidth/2 + 117, height/2 - 80, OPPowers.ESCAPAR_MORTE));
        addPowerIcon(new PowerButton(tabWidth/2 + 117, height/2 - 140, OPPowers.CONSUMIR));
        addPowerIcon(new PowerButton(tabWidth/2 + 117, height/2 - 200, OPPowers.CONSUMIR_2));
        addPowerIcon(new PowerButton(tabWidth/2 - 13, height/2 - 10, OPPowers.PUTREFATO));
        addPowerIcon(new PowerButton(tabWidth/2 - 13, height/2 - 80, OPPowers.PUTREFATO_2));
        addPowerIcon(new PowerButton(tabWidth/2 - 63, height/2 - 80, OPPowers.POTENCIAL_REAPROVEITADO));
        addPowerIcon(new PowerButton(tabWidth/2 - 63, height/2 - 140, OPPowers.SACRIFICAR_ENTROPIA));
        addPowerIcon(new PowerButton(tabWidth/2 - 63, height/2 - 200, OPPowers.SACRIFICAR_ENTROPIA_2));
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 - 80, OPPowers.SURTO_TEMPORAL));
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 - 140, OPPowers.PERIMETRO_ESPIRAL));
        addPowerIcon(new PowerButton(tabWidth/2 - 13, height/2 - 200, OPPowers.ABSORVER_ENTROPIA));
        addPowerIcon(new PowerButton(tabWidth/2 + 67, height/2 - 200, OPPowers.LEMBRAR_DA_MORTE));
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 - 200, OPPowers.LAMINA_MEDO));
    }
}
