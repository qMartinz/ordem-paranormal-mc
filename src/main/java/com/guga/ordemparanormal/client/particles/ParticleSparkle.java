package com.guga.ordemparanormal.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@OnlyIn(Dist.CLIENT)
public class ParticleSparkle extends TextureSheetParticle {
    public float colorR = 0;
    public float colorG = 0;
    public float colorB = 0;
    public float initScale = 0;
    public float initAlpha = 0;
    public boolean glow = true;
    protected ParticleSparkle(ClientLevel level, double x, double y, double z, double xd, double yd, double zd, float r,
                              float g, float b, float scale, int lifetime, SpriteSet sprite, boolean glow) {
        super(level, x, y, z, 0, 0, 0);
        this.colorR = r;
        this.colorG = g;
        this.colorB = b;
        if (this.colorR > 1.0) {
            this.colorR = this.colorR / 255.0f;
        }
        if (this.colorG > 1.0) {
            this.colorG = this.colorG / 255.0f;
        }
        if (this.colorB > 1.0) {
            this.colorB = this.colorB / 255.0f;
        }
        this.setColor(colorR, colorG, colorB);
        this.lifetime = lifetime;
        this.quadSize = scale;
        this.hasPhysics = false;
        this.initScale = scale;
        this.xd = xd * 2.0f;
        this.yd = yd * 2.0f;
        this.zd = zd * 2.0f;
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
        float lifeCoeff = ((float) this.age / (float) this.lifetime) / 3;
        this.quadSize = initScale - initScale * lifeCoeff;
        this.alpha = initAlpha * (1.0f - lifeCoeff*3);

        this.oRoll = roll;
        roll += 1.0f;
    }
    @Override
    public boolean isAlive() {
        return this.age < this.lifetime;
    }
}
