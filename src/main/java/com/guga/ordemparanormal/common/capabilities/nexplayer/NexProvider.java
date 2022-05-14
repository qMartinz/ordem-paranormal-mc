package com.guga.ordemparanormal.common.capabilities.nexplayer;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NexProvider implements ICapabilitySerializable<CompoundTag> {
    private final NexModel nexModel;
    private final LazyOptional<NexModel> optional;

    public NexProvider(NexModel nexModel) {
        this.nexModel = nexModel;
        optional = LazyOptional.of(() -> nexModel);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == NexCapability.INSTANCE) return optional.cast();

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return nexModel.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        nexModel.deserializeNBT(nbt);
    }
}
