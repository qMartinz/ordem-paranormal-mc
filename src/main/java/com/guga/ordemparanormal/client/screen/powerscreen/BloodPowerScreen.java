package com.guga.ordemparanormal.client.screen.powerscreen;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.client.screen.buttons.PowerButton;
import com.guga.ordemparanormal.core.registry.OPPowers;

public class BloodPowerScreen extends PowerScreen{
    public BloodPowerScreen() {
        super(ParanormalElement.BLOOD);
    }
    @Override
    public void initContents() {
        int tabWidth = this.width - 41;
        addPowerIcon(new PowerButton(tabWidth/2 - 10 + 37, height/2 - 10, OPPowers.TEST_POWER));
    }
}
