package com.guga.ordemparanormal.client.screen.buttons;

import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import net.minecraft.network.chat.Component;

public class SlotButton extends InvisibleButton{
    private PlayerPower power;
    public SlotButton(int pX, int pY, int pWidth, int pHeight, Component pMessage, OnPress pOnPress, PlayerPower power) {
        super(pX, pY, pWidth, pHeight, pMessage, pOnPress);
        this.power = power;
    }

    public PlayerPower getPower() {
        return power;
    }

    public void setPower(PlayerPower power) {
        this.power = power;
    }
}
