package com.guga.ordemparanormal.client.screen.powerscreen;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.client.screen.PowerScreen;
import com.guga.ordemparanormal.client.screen.buttons.PowerButton;
import com.guga.ordemparanormal.core.registry.OPPowers;

public class KnowledgePowerScreen extends PowerScreen {
    public KnowledgePowerScreen() {
        super(ParanormalElement.CONHECIMENTO);
    }

    @Override
    public void initContents() {
        int tabWidth = this.width - 41;
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 - 10, OPPowers.AFINIDADE_CONHECIMENTO));
        addPowerIcon(new PowerButton(tabWidth/2 + 87, height/2 - 10, OPPowers.CONHECIMENTO_AMPLIADO));
        addPowerIcon(new PowerButton(tabWidth/2 + 147, height/2 - 10, OPPowers.CONHECIMENTO_AMPLIADO_2));
    }
}
