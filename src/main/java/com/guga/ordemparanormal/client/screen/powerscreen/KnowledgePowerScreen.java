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
        addPowerIcon(new PowerButton(tabWidth/2 - 33, height/2 - 60, OPPowers.PRESENCA_AVASSALADORA_2));
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 - 60, OPPowers.PRESENCA_AVASSALADORA));
        addPowerIcon(new PowerButton(tabWidth/2 + 27, height/2 + 40, OPPowers.SABEDORIA));
        addPowerIcon(new PowerButton(tabWidth/2 + 87, height/2 + 40, OPPowers.SABEDORIA_2));
        addPowerIcon(new PowerButton(tabWidth/2 - 33, height/2 - 10, OPPowers.CARISMATICO));
        addPowerIcon(new PowerButton(tabWidth/2 - 93, height/2 - 10, OPPowers.CARISMATICO_2));
    }
}
