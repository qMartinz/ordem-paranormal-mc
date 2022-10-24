package com.guga.ordemparanormal.client.screen.powerscreen;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.client.screen.PowerScreen;
import com.guga.ordemparanormal.client.screen.buttons.PowerButton;
import com.guga.ordemparanormal.core.registry.OPPowers;

public class EnergyPowerScreen extends PowerScreen {
    public EnergyPowerScreen() {
        super(ParanormalElement.ENERGIA);
    }
    @Override
    public void initContents() {
        int tabWidth = this.width - 41;
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 - 10, OPPowers.AFINIDADE_ENERGIA));
        addPowerIcon(new PowerButton(tabWidth/2 + 67, height/2 - 50, OPPowers.CAMPO_PROTETOR));
        addPowerIcon(new PowerButton(tabWidth/2 + 127, height/2 - 120, OPPowers.CAMPO_PROTETOR_2));
        addPowerIcon(new PowerButton(tabWidth/2 - 13, height/2 - 50, OPPowers.GRAVIDADE_DISTORCIDA));
        addPowerIcon(new PowerButton(tabWidth/2 - 73, height/2 - 120, OPPowers.GRAVIDADE_DISTORCIDA_2));
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 + 30, OPPowers.CASUALIDADE_FORTUITA));
    }
}
