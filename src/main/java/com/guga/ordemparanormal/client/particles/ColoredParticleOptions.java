package com.guga.ordemparanormal.client.particles;

import com.guga.ordemparanormal.api.util.ColorUtil;
import com.guga.ordemparanormal.core.registry.OPParticles;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;

public class ColoredParticleOptions implements ParticleOptions {
    private ParticleType<ColoredParticleOptions> type;
    public static final Codec<ColoredParticleOptions> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Codec.INT.fieldOf("r").forGetter(d -> d.color.getRed()),
                    Codec.INT.fieldOf("g").forGetter(d -> d.color.getGreen()),
                    Codec.INT.fieldOf("b").forGetter(d -> d.color.getBlue()),
                    Codec.FLOAT.fieldOf("size").forGetter(d -> d.size),
                    Codec.FLOAT.fieldOf("alpha").forGetter(d -> d.alpha),
                    Codec.INT.fieldOf("age").forGetter(d -> d.age),
                    Codec.BOOL.fieldOf("glow").forGetter(d -> d.glow)
            )
            .apply(instance, ColoredParticleOptions::new));
    public Color color;
    public float size = .25f;
    public float alpha = 1.0f;
    public int age = 36;
    public boolean glow = true;
    static final ParticleOptions.Deserializer<ColoredParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<>() {
        @Override
        public ColoredParticleOptions fromCommand(ParticleType<ColoredParticleOptions> type, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            return new ColoredParticleOptions(type, ColorUtil.fromString(reader.getString()));
        }

        @Override
        public ColoredParticleOptions fromNetwork(ParticleType<ColoredParticleOptions> type, FriendlyByteBuf buffer) {
            return new ColoredParticleOptions(type, ColorUtil.fromNBT(buffer.readNbt()));
        }
    };

    public ColoredParticleOptions(float r, float g, float b, float size, float alpha, int age, boolean glow) {
        this(OPParticles.ABILITIES_PARTICLE.get(), new Color(r, g, b), size, alpha, age, glow);
    }

    public ColoredParticleOptions(Color color, float size, float alpha, int age, boolean glow) {
        this(OPParticles.ABILITIES_PARTICLE.get(), color, size, alpha, age, glow);
    }

    public ColoredParticleOptions(ParticleType<ColoredParticleOptions> particleTypeData, Color color) {
        this(particleTypeData, color, 0.25f, 1.0f, 36, true);
    }
    public ColoredParticleOptions(ParticleType<ColoredParticleOptions> particleTypeData, Color color, boolean glow) {
        this(particleTypeData, color, 0.25f, 1.0f, 36, glow);
    }

    public ColoredParticleOptions(ParticleType<ColoredParticleOptions> particleTypeData, Color color, float size, float alpha, int age, boolean glow) {
        this.type = particleTypeData;
        this.color = color;
        this.size = size;
        this.alpha = alpha;
        this.age = age;
        this.glow = glow;
    }


    @Override
    public ParticleType<ColoredParticleOptions> getType() {
        return type;
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf packetBuffer) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("r", color.getRed());
        tag.putInt("g", color.getGreen());
        tag.putInt("b", color.getBlue());
        packetBuffer.writeNbt(tag);
    }

    @Override
    public String writeToString() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("r", color.getRed());
        tag.putInt("g", color.getGreen());
        tag.putInt("b", color.getBlue());
        return ForgeRegistries.PARTICLE_TYPES.getKey(type).toString() + " " + tag;
    }
}
