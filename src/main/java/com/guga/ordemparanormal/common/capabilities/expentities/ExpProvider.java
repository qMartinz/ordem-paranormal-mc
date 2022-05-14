package com.guga.ordemparanormal.common.capabilities.expentities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExpProvider implements ICapabilitySerializable<CompoundTag> {
    private final ExpModel expModel;
    private final LazyOptional<ExpModel> optional;

    public ExpProvider(ExpModel expModel) {
        this.expModel = expModel;
        optional = LazyOptional.of(() -> expModel);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ExpCapability.INSTANCE) return optional.cast();

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return expModel.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        expModel.deserializeNBT(nbt);
    }
}
