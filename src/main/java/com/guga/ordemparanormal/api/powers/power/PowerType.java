package com.guga.ordemparanormal.api.powers.power;

import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.resources.ResourceLocation;

public enum PowerType {
    FEAR(0, 240),
    BLOOD(16, 240),
    KNOWLEDGE(32, 240),
    DEATH(48, 240),
    ENERGY(64, 240);
    public static final ResourceLocation TEXTURE = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/powerscreen.png");
    public final int x;
    public final int y;
    PowerType(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
