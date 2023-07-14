package com.guga.ordemparanormal.client.particles;

import com.guga.ordemparanormal.core.registry.OPParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ParticleOptions;

import java.awt.*;
import java.util.Random;

public class SparkleParticleOptions implements ParticleProvider<ColoredDynamicParticleOptions> {
    private final SpriteSet spriteSet;

    public static final Random random = new Random();

    public SparkleParticleOptions(SpriteSet sprite) {
        this.spriteSet = sprite;
    }

    @Override
    public Particle createParticle(ColoredDynamicParticleOptions options, ClientLevel worldIn, double x, double y, double z,
                                   double xSpeed, double ySpeed, double zSpeed) {
        return new ParticleSparkle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, options.color.getRed(), options.color.getGreen(),
                options.color.getBlue(), options.scale, options.age, this.spriteSet, options.glow);
    }
    public static ParticleOptions createData(Color color) {
        return new ColoredDynamicParticleOptions(OPParticles.SPARKLE_PARTICLE.get(), color, .05f, 36, true);
    }
    public static ParticleOptions createData(Color color, boolean glow) {
        return new ColoredDynamicParticleOptions(OPParticles.SPARKLE_PARTICLE.get(), color, .05f, 36, glow);
    }
    public static ParticleOptions createData(Color color, float scale, int age, boolean glow) {
        return new ColoredDynamicParticleOptions(OPParticles.SPARKLE_PARTICLE.get(), color, scale, age, glow);
    }
}
