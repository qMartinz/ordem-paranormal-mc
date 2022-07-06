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

public class PlayerNexProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final ResourceLocation IDENTIFIER = new ResourceLocation(OrdemParanormal.MOD_ID, "player_nex");
    public static Capability<PlayerNex> PLAYER_NEX = CapabilityManager.get(new CapabilityToken<>(){});
    private PlayerNex playerNex = null;
    private final LazyOptional<PlayerNex> opt = LazyOptional.of(this::createPlayerNex);
    @NotNull
    private PlayerNex createPlayerNex(){
        if (playerNex == null){
            playerNex = new PlayerNex();
        }
        return playerNex;
    }
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == PLAYER_NEX){
            return opt.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return createPlayerNex().saveNBTData();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerNex().loadNBTData(nbt);
    }
}
