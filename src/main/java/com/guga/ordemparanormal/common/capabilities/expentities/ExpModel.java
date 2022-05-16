package com.guga.ordemparanormal.common.capabilities.expentities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.util.INBTSerializable;

public class ExpModel implements INBTSerializable<CompoundTag> {
    public float exposure;
    public final float maxExposure = 100.0F;
    public float getExposure(){ return exposure; }
    public void setExposure(float level){ exposure = level; }

    public boolean isMaxExp(){ return exposure >= maxExposure; }
    public static ExpModel get(LivingEntity entity){
        return entity.getCapability(ExpCapability.INSTANCE).orElseThrow(() ->
                new IllegalArgumentException("Entity " + entity.getName().getContents() + " does not have an Exposure Model.")
        );
    }
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putFloat("exposure", exposure);

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        exposure = nbt.getFloat("exposure");
    }
}
