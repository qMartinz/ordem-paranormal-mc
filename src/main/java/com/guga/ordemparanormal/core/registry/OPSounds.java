package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OPSounds {
    private static final SoundSubRegistryHelper HELPER = OrdemParanormal.REGISTRY_HELPER.getSoundSubHelper();

    // Registrar sons
    public static final RegistryObject<SoundEvent> ZUMBI_SANGUE_GROWL = HELPER.createSoundEvent("zumbi_sangue.growl");
}
