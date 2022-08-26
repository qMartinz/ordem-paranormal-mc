package com.guga.ordemparanormal.client;

import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPRituals;
import net.minecraft.resources.ResourceLocation;

public enum RitualSymbol {
    DESCARNAR(OPRituals.DESCARNAR, 0, 0, 62, 62, new ResourceLocation(OrdemParanormal.MOD_ID, "textures/gui/rituals.png")),
    DECADENCIA(OPRituals.DECADENCIA, 62, 0, 62, 62, DESCARNAR.textureLoc),
    ARMADURA_SANGUE(OPRituals.ARMADURA_SANGUE, 124, 0, 64, 64, DESCARNAR.textureLoc),
    CONSUMIR_MANANCIAL(OPRituals.CONSUMIR_MANANCIAL, 188, 0, 64, 64, DESCARNAR.textureLoc),
    CICATRIZACAO(OPRituals.CICATRIZACAO, 0, 62, 64, 64, DESCARNAR.textureLoc),
    ARMA_ATROZ(OPRituals.ARMA_ATROZ, 0, 0, 62, 62, DESCARNAR.textureLoc),
    ARMA_VELOZ(OPRituals.ARMA_VELOZ, 0, 0, 62, 62, DESCARNAR.textureLoc);
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
