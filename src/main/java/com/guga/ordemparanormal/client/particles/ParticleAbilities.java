package com.guga.ordemparanormal.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class ParticleAbilities extends TextureSheetParticle {
    public float colorR = 0;
    public float colorG = 0;
    public float colorB = 0;
    public float initScale = 0;
    public float initAlpha = 0;
    public boolean glow = true;
    protected ParticleAbilities(ClientLevel level, double x, double y, double z, double xd, double yd, double zd, float r,
                                float g, float b, float a, float scale, int lifetime, SpriteSet sprite, boolean glow) {
        super(level, x, y, z, 0, 0, 0);
        this.hasPhysics = false;

        this.colorR = r;
        this.colorG = g;
        this.colorB = b;
        this.setColor(colorR, colorG, colorB);
        this.lifetime = (int) ((float) lifetime * 0.5f);
        this.quadSize = 0;
        this.initScale = scale;
        this.xd = xd * 2.0f;
        this.yd = yd * 2.0f;
        this.zd = zd * 2.0f;
        this.initAlpha = a;
        this.glow = glow;
        this.pickSprite(sprite);
    }
    @Override
    public ParticleRenderType getRenderType() {
        return glow ? ParticleRenderTypes.GLOW_RENDER : ParticleRenderTypes.NO_GLOW_RENDER;
    }
    @Override
    public void tick() {
        super.tick();

        if (new Random().nextInt(6) == 0) {
            this.age++;
        }
        float lifeCoeff = (float) this.age / (float) this.lifetime;
        this.quadSize = initScale - initScale * lifeCoeff;
        this.alpha = initAlpha * (1.0f - lifeCoeff);

        this.oRoll = roll;
        roll += 1.0f;
    }
    @Override
    public boolean isAlive() {
        return this.age < this.lifetime;
    }
}
