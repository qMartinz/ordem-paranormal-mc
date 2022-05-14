package com.guga.ordemparanormal.common.capabilities.expentities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ExpCapability {
    public static Capability<ExpModel> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});
}
