package com.guga.ordemparanormal.api.capabilities.data;

import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ParanormalEffectsProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final ResourceLocation IDENTIFIER = new ResourceLocation(OrdemParanormal.MOD_ID, "paranormal_effects");
    private final IEffectsCap backend = new ParanormalEffects();
    private final LazyOptional<IEffectsCap> optionalData = LazyOptional.of(() -> backend);
    public static Capability<IEffectsCap> PARANORMAL_EFFECTS = CapabilityManager.get(new CapabilityToken<>(){});
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return PARANORMAL_EFFECTS.orEmpty(cap, this.optionalData);
    }
    @Override
    public CompoundTag serializeNBT() {
        return this.backend.serializeNBT();
    }
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.backend.deserializeNBT(nbt);
    }
}
