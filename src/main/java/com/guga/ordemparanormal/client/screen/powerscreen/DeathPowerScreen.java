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
        addPowerIcon(new PowerButton(tabWidth/2 + 77, height/2 - 10, OPPowers.POTENCIAL_APRIMORADO));
    }
}
