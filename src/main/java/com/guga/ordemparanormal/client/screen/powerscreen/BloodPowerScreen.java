package com.guga.ordemparanormal.client.screen.powerscreen;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.client.screen.buttons.PowerButton;
import com.guga.ordemparanormal.core.registry.OPPowers;

public class BloodPowerScreen extends PowerScreen{
    public BloodPowerScreen() {
        super(ParanormalElement.SANGUE);
    }
    @Override
    public void initContents() {
        int tabWidth = this.width - 41;
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 - 10, OPPowers.AFINIDADE_SANGUE));
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 + 30, OPPowers.SANGUE_FERRO));
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 + 70, OPPowers.SANGUE_FERRO_2));
        addPowerIcon(new PowerButton(tabWidth/2 + 57, height/2 + 70, OPPowers.SANGUE_FERRO_3));
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 + 135, OPPowers.SANGUE_FERRO_4));
    }
}
