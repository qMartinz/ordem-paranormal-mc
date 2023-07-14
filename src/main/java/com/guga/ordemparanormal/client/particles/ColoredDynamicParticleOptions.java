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

public class ColoredDynamicParticleOptions implements ParticleOptions {
    private ParticleType<ColoredDynamicParticleOptions> type;
    public Color color;
    float scale;
    int age;
    public boolean glow = true;

    public static final Codec<ColoredDynamicParticleOptions> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Codec.INT.fieldOf("r").forGetter(d -> d.color.getRed()),
                    Codec.INT.fieldOf("g").forGetter(d -> d.color.getGreen()),
                    Codec.INT.fieldOf("b").forGetter(d -> d.color.getBlue()),
                    Codec.FLOAT.fieldOf("scale").forGetter(d -> d.scale),
                    Codec.INT.fieldOf("age").forGetter(d -> d.age),
                    Codec.BOOL.fieldOf("glow").forGetter(d -> d.glow)
            )
            .apply(instance, ColoredDynamicParticleOptions::new));

    @Override
    public ParticleType<?> getType() {
        return type;
    }

    static final Deserializer<ColoredDynamicParticleOptions> DESERIALIZER = new Deserializer<>() {
        @Override
        public ColoredDynamicParticleOptions fromCommand(ParticleType<ColoredDynamicParticleOptions> type, StringReader reader) 
                throws CommandSyntaxException {
            reader.expect(' ');
            return new ColoredDynamicParticleOptions(type, ColorUtil.fromString(reader.getString()), reader.readFloat(), reader.readInt(), reader.readBoolean());
        }

        @Override
        public ColoredDynamicParticleOptions fromNetwork(ParticleType<ColoredDynamicParticleOptions> type, FriendlyByteBuf buffer) {
            return new ColoredDynamicParticleOptions(type, ColorUtil.fromNBT(buffer.readNbt()), buffer.readFloat(), buffer.readInt(), buffer.readBoolean());
        }
    };

    public ColoredDynamicParticleOptions(float r, float g, float b, float scale, int age, boolean glow) {
        this.type = OPParticles.SPARKLE_PARTICLE.get();
        this.color = new Color(r, g, b);
        this.scale = scale;
        this.age = age;
        this.glow = glow;
    }

    public ColoredDynamicParticleOptions(ParticleType<ColoredDynamicParticleOptions> particleTypeData, Color color, float scale, int age, boolean glow) {
        this.type = particleTypeData;
        this.color = color;
        this.scale = scale;
        this.age = age;
        this.glow = glow;
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buffer) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("r", color.getRed());
        tag.putInt("g", color.getGreen());
        tag.putInt("b", color.getBlue());
        buffer.writeNbt(tag);
        buffer.writeFloat(scale);
        buffer.writeInt(age);
    }

    @Override
    public String writeToString() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("r", color.getRed());
        tag.putInt("g", color.getGreen());
        tag.putInt("b", color.getBlue());
        return ForgeRegistries.PARTICLE_TYPES.getKey(type).toString() + " " + tag + " " + scale + " " + age;
    }
}
