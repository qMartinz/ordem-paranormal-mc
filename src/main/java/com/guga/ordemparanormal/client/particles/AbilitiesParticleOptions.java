package com.guga.ordemparanormal.client.particles;

import com.guga.ordemparanormal.core.registry.OPParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ParticleOptions;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class AbilitiesParticleOptions implements ParticleProvider<ColoredParticleOptions> {
    private final SpriteSet spriteSet;
    public AbilitiesParticleOptions(SpriteSet sprite) {
        this.spriteSet = sprite;
    }
    @Nullable
    @Override
    public Particle createParticle(ColoredParticleOptions options, ClientLevel pLevel, double pX, double pY, double pZ,
                                   double pXSpeed, double pYSpeed, double pZSpeed) {
        return new ParticleAbilities(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, options.color.getRed() / 255f,
                options.color.getGreen() / 255f, options.color.getBlue() / 255f, options.alpha, options.size,
                options.age, this.spriteSet, options.glow);
    }
    public static ParticleOptions createData(Color color) {
        return new ColoredParticleOptions(OPParticles.ABILITIES_PARTICLE.get(), color);
    }
    public static ParticleOptions createData(Color color, boolean glow) {
        return new ColoredParticleOptions(OPParticles.ABILITIES_PARTICLE.get(), color, glow);
    }
    public static ParticleOptions createData(Color color, float size, float alpha, int age, boolean glow) {
        return new ColoredParticleOptions(color, size, alpha, age, glow);
    }
}
