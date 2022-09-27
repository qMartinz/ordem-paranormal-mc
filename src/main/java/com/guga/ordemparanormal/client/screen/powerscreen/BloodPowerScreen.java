package com.guga.ordemparanormal.client.screen.powerscreen;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.client.screen.PowerScreen;
import com.guga.ordemparanormal.client.screen.buttons.PowerButton;
import com.guga.ordemparanormal.core.registry.OPPowers;

public class BloodPowerScreen extends PowerScreen {
    public BloodPowerScreen() {
        super(ParanormalElement.SANGUE);
    }
    @Override
    public void initContents() {
        int tabWidth = this.width - 41;
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 - 10, OPPowers.AFINIDADE_SANGUE));
        addPowerIcon(new PowerButton(tabWidth/2 + 77, height/2 - 10, OPPowers.SANGUE_FERRO));
        addPowerIcon(new PowerButton(tabWidth/2 + 127, height/2 - 10, OPPowers.SANGUE_FERRO_2));
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 + 40, OPPowers.PUNHO_ENRAIVECIDO));
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 + 90, OPPowers.PUNHO_ENRAIVECIDO_2));
    }
}
