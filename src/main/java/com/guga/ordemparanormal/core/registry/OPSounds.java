package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class OPSounds {
    public static final SoundSubRegistryHelper HELPER = OrdemParanormal.REGISTRY_HELPER.getSoundSubHelper();

    // Registrar sons
    public static final RegistryObject<SoundEvent> ZUMBI_SANGUE_GROWL = HELPER.createSoundEvent("zumbi_sangue.growl");
    public static final RegistryObject<SoundEvent> ZUMBI_SANGUE_CONVERT = HELPER.createSoundEvent("zumbi_sangue.convert");
    public static final RegistryObject<SoundEvent> ZUMBI_SANGUE_HURT = HELPER.createSoundEvent("zumbi_sangue.hurt");
    public static final RegistryObject<SoundEvent> ZUMBI_SANGUE_DEATH = HELPER.createSoundEvent("zumbi_sangue.death");
    public static final RegistryObject<SoundEvent> ZUMBI_BESTIAL_GROWL = HELPER.createSoundEvent("zumbi_bestial.growl");
    public static final RegistryObject<SoundEvent> ZOMBIE_CONVERT = HELPER.createSoundEvent("zombie_convert");
    public static final RegistryObject<SoundEvent> SKELETON_CONVERT = HELPER.createSoundEvent("skeleton_convert");
    public static final RegistryObject<SoundEvent> CORPSE_CONVERT = HELPER.createSoundEvent("corpse.convert");
    public static final RegistryObject<SoundEvent> CORPSE_HURT = HELPER.createSoundEvent("corpse.hurt");
    public static final RegistryObject<SoundEvent> CORPSE_DEATH = HELPER.createSoundEvent("corpse.death");
    public static final RegistryObject<SoundEvent> RITUAL_LEARNED = HELPER.createSoundEvent("ritual.learned");
    public static final RegistryObject<SoundEvent> RITUAL_FORGOTTEN = HELPER.createSoundEvent("ritual.forgotten");
    public static final RegistryObject<SoundEvent> RITUAL_USED = HELPER.createSoundEvent("ritual.used");
    public static final RegistryObject<SoundEvent> RITUAL_FAILED = HELPER.createSoundEvent("ritual.failed");
    public static final RegistryObject<SoundEvent> BLOOD_POWER_USED = HELPER.createSoundEvent("blood_power_used");
    public static final RegistryObject<SoundEvent> ENERGY_POWER_USED = HELPER.createSoundEvent("energy_power_used");
    public static final RegistryObject<SoundEvent> DEATH_POWER_USED = HELPER.createSoundEvent("death_power_used");
    public static final RegistryObject<SoundEvent> KNOWLEDGE_POWER_USED = HELPER.createSoundEvent("knowledge_power_used");
    public static final RegistryObject<SoundEvent> FEAR_POWER_USED = HELPER.createSoundEvent("fear_power_used");
    public static final RegistryObject<SoundEvent> INGREDIENT_BLOOD = HELPER.createSoundEvent("ingredient_blood");
    public static final RegistryObject<SoundEvent> INGREDIENT_ENERGY = HELPER.createSoundEvent("ingredient_energy");
    public static final RegistryObject<SoundEvent> INGREDIENT_DEATH = HELPER.createSoundEvent("ingredient_death");
    public static final RegistryObject<SoundEvent> INGREDIENT_KNOWLEDGE = HELPER.createSoundEvent("ingredient_knowledge");
    public static final RegistryObject<SoundEvent> VAMPIRISMO_USED = HELPER.createSoundEvent("vampirismo_used");
    public static final RegistryObject<SoundEvent> ABERRACAO_IDLE = HELPER.createSoundEvent("aberracao.idle");
    public static final RegistryObject<SoundEvent> ABERRACAO_STEP = HELPER.createSoundEvent("aberracao.step");
    public static final RegistryObject<SoundEvent> ABERRACAO_ATTACK = HELPER.createSoundEvent("aberracao.attack");
    public static final RegistryObject<SoundEvent> ABERRACAO_ABOCANHAR = HELPER.createSoundEvent("aberracao.abocanhar");
    public static final RegistryObject<SoundEvent> ABERRACAO_HURT = HELPER.createSoundEvent("aberracao.hurt");
    public static final RegistryObject<SoundEvent> ABERRACAO_DEATH = HELPER.createSoundEvent("aberracao.death");
    public static final RegistryObject<SoundEvent> BIDENTE_HIT = HELPER.createSoundEvent("bidente.hit");
    public static final RegistryObject<SoundEvent> BIDENTE_RIPTIDE_1 = HELPER.createSoundEvent("bidente.riptide_1");
    public static final RegistryObject<SoundEvent> BIDENTE_RIPTIDE_2 = HELPER.createSoundEvent("bidente.riptide_2");
    public static final RegistryObject<SoundEvent> BIDENTE_RIPTIDE_3 = HELPER.createSoundEvent("bidente.riptide_3");
}
