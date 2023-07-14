package com.guga.ordemparanormal.client.particles;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;

public class SparkleParticleType extends ParticleType<ColoredDynamicParticleOptions> {
    public SparkleParticleType() {
        super(false, ColoredDynamicParticleOptions.DESERIALIZER);
    }
    @Override
    public Codec<ColoredDynamicParticleOptions> codec() {
        return ColoredDynamicParticleOptions.CODEC;
    }
}
