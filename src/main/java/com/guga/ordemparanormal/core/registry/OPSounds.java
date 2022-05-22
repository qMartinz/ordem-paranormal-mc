package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class OPSounds {
    private static final SoundSubRegistryHelper HELPER = OrdemParanormal.REGISTRY_HELPER.getSoundSubHelper();

    // Registrar sons
    public static final RegistryObject<SoundEvent> ZUMBI_SANGUE_GROWL = HELPER.createSoundEvent("zumbi_sangue.growl");
    public static final RegistryObject<SoundEvent> ZUMBI_BESTIAL_GROWL = HELPER.createSoundEvent("zumbi_bestial.growl");
}
