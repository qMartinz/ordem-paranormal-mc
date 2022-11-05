package com.guga.ordemparanormal.datagen.client;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPSounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class ModSoundDefinitionsProvider extends SoundDefinitionsProvider {

    public ModSoundDefinitionsProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, OrdemParanormal.MOD_ID.toLowerCase(), helper);
    }

    public SoundDefinition soundDefinition(String soundFileName){
        return definition().with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, soundFileName)));
    }
    @Override
    public void registerSounds() {
        add(OPSounds.ZUMBI_BESTIAL_GROWL, soundDefinition("zumbi_bestial.growl").subtitle("subtitles.ordemparanormal.zumbi_bestial.growl"));
        add(OPSounds.ZUMBI_SANGUE_GROWL, soundDefinition("zumbi_sangue.growl").subtitle("subtitles.ordemparanormal.zumbi_sangue.growl"));
        add(OPSounds.ZOMBIE_CONVERT, soundDefinition("zombie_convert").subtitle("subtitles.ordemparanormal.zombie.convert"));
        add(OPSounds.SKELETON_CONVERT, soundDefinition("skeleton_convert").subtitle("subtitles.ordemparanormal.skeleton_convert"));
        add(OPSounds.CORPSE_DEATH, soundDefinition("corpse_death").subtitle("subtitles.ordemparanormal.corpse.death"));
        add(OPSounds.ZUMBI_SANGUE_DEATH, soundDefinition("corpse_death").subtitle("subtitles.ordemparanormal.zumbi_sangue.death"));
        add(OPSounds.CORPSE_CONVERT, soundDefinition("corpse_death").subtitle("subtitles.ordemparanormal.corpse.convert"));
        add(OPSounds.ZUMBI_SANGUE_CONVERT, soundDefinition("corpse_death").subtitle("subtitles.ordemparanormal.zumbi_sangue.convert"));
        add(OPSounds.RITUAL_LEARNED, soundDefinition("ritual_learned").subtitle("subtitles.ordemparanormal.ritual_learned"));
        add(OPSounds.RITUAL_FORGOTTEN, soundDefinition("ritual_forgotten").subtitle("subtitles.ordemparanormal.ritual_forgotten"));
        add(OPSounds.CORPSE_HURT, soundDefinition("blood_hurt_1")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood_hurt_2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood_hurt_3")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood_hurt_4")))
                .subtitle("subtitles.ordemparanormal.corpse.hurt"));
        add(OPSounds.ZUMBI_SANGUE_HURT, soundDefinition("blood_hurt_1")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood_hurt_2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood_hurt_3")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood_hurt_4")))
                .subtitle("subtitles.ordemparanormal.zumbi_sangue.hurt"));

        add(OPSounds.RITUAL_USED, soundDefinition("ritual_used").subtitle("subtitles.ordemparanormal.ritual_used"));
        add(OPSounds.RITUAL_FAILED, soundDefinition("ritual_failed").subtitle("subtitles.ordemparanormal.ritual_failed"));
        add(OPSounds.INGREDIENT_BLOOD, soundDefinition("ingredient_blood"));
        add(OPSounds.INGREDIENT_DEATH, soundDefinition("ingredient_death"));
        add(OPSounds.INGREDIENT_ENERGY, soundDefinition("ingredient_energy"));
        add(OPSounds.INGREDIENT_KNOWLEDGE, soundDefinition("ingredient_knowledge"));

        add(OPSounds.BLOOD_POWER_USED, soundDefinition("blood_power_used")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood_power_used_2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood_power_used_3")))
                .subtitle("subtitles.ordemparanormal.blood_power_used"));
        add(OPSounds.ENERGY_POWER_USED, soundDefinition("energy_power_used")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "energy_power_used_2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "energy_power_used_3")))
                .subtitle("subtitles.ordemparanormal.energy_power_used"));
        add(OPSounds.DEATH_POWER_USED, soundDefinition("death_power_used")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "death_power_used_2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "death_power_used_3")))
                .subtitle("subtitles.ordemparanormal.death_power_used"));
        add(OPSounds.KNOWLEDGE_POWER_USED, soundDefinition("knowledge_power_used")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "knowledge_power_used_2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "knowledge_power_used_3")))
                .subtitle("subtitles.ordemparanormal.knowledge_power_used"));
        add(OPSounds.FEAR_POWER_USED, soundDefinition("fear_power_used").subtitle("subtitles.ordemparanormal.fear_power_used"));

        add(OPSounds.VAMPIRISMO_USED, soundDefinition("vampirismo_used"));
    }
}
