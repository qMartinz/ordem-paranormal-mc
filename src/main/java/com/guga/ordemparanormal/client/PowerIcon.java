package com.guga.ordemparanormal.client;

import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPAPI;
import net.minecraft.resources.ResourceLocation;

public enum PowerIcon {
    TEST1(OPAPI.TEST_POWER, 80, 153),
    TEST2(OPAPI.TEST_POWER_2, 92, 153),
    TEST3(OPAPI.TEST_POWER_3, 104, 153),
    TEST4(OPAPI.TEST_POWER_4, 116, 153),
    TEST5(OPAPI.TEST_POWER_5, 80, 153),
    TEST6(OPAPI.TEST_POWER_6, 92, 153),
    TEST7(OPAPI.TEST_POWER_7, 104, 153),
    TEST8(OPAPI.TEST_POWER_8, 116, 153);
    private final PlayerPower ability;
    private final int x;
    private final int y;
    private static final ResourceLocation TEXTURE = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/powerscreen.png");
    PowerIcon(PlayerPower ability, int x, int y){
        this.ability = ability;
        this.x = x;
        this.y = y;
    }
    public PlayerPower getAbility() {
        return ability;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public ResourceLocation getTextureLoc() {
        return TEXTURE;
    }
}
