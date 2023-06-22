package com.guga.ordemparanormal.client.particles;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;

public class AbilitiesParticleType extends ParticleType<ColoredParticleOptions> {
    public AbilitiesParticleType() {
        super(false, ColoredParticleOptions.DESERIALIZER);
    }
    @Override
    public Codec<ColoredParticleOptions> codec() {
        return ColoredParticleOptions.CODEC;
    }
}
