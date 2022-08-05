package com.guga.ordemparanormal.client;

import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPAPI;
import net.minecraft.resources.ResourceLocation;

public enum RitualSymbol {
    SKINNING(OPAPI.SKINNING, 0, 0, 62, 62, new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/rituals.png")),
    DECAY(OPAPI.DECAY, 62, 0, 62, 62, SKINNING.textureLoc),
    BlOOD_ARMOR(OPAPI.BLOOD_ARMOR, 124, 0, 64, 64, SKINNING.textureLoc),
    CONSUME_LIFEFORCE(OPAPI.CONSUME_LIFEFORCE, 188, 0, 64, 64, SKINNING.textureLoc),
    CICATRIZATION(OPAPI.CICATRIZATION, 0, 62, 64, 64, SKINNING.textureLoc);
    private final AbstractRitual ritual;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final ResourceLocation textureLoc;
    RitualSymbol(AbstractRitual ritual, int x, int y, int width, int height, ResourceLocation textureLoc){
        this.ritual = ritual;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textureLoc = textureLoc;
    }
    public AbstractRitual getRitual() {
        return ritual;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public ResourceLocation getTextureLoc() {
        return textureLoc;
    }
}
