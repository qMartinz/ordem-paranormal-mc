package com.guga.ordemparanormal.common.capabilities.nexplayer;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class NexCapability {
    public static final Capability<NexModel> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});
}
