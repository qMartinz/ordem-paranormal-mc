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
        add(OPSounds.ZUMBI_BESTIAL_GROWL, soundDefinition("bestial/growl").subtitle("subtitles.ordemparanormal.zumbi_bestial.growl"));
        add(OPSounds.ZUMBI_SANGUE_GROWL, soundDefinition("zumbi_sangue/growl").subtitle("subtitles.ordemparanormal.zumbi_sangue.growl"));
        add(OPSounds.ZOMBIE_CONVERT, soundDefinition("zombie_convert").subtitle("subtitles.ordemparanormal.zombie.convert"));
        add(OPSounds.SKELETON_CONVERT, soundDefinition("skeleton_convert").subtitle("subtitles.ordemparanormal.skeleton.convert"));
        add(OPSounds.CORPSE_DEATH, soundDefinition("corpse_death").subtitle("subtitles.ordemparanormal.corpse.death"));
        add(OPSounds.ZUMBI_SANGUE_DEATH, soundDefinition("corpse_death").subtitle("subtitles.ordemparanormal.zumbi_sangue.death"));
        add(OPSounds.CORPSE_CONVERT, soundDefinition("corpse_death").subtitle("subtitles.ordemparanormal.corpse.convert"));
        add(OPSounds.ZUMBI_SANGUE_CONVERT, soundDefinition("corpse_death").subtitle("subtitles.ordemparanormal.zumbi_sangue.convert"));
        add(OPSounds.RITUAL_LEARNED, soundDefinition("ritual/learned").subtitle("subtitles.ordemparanormal.ritual_learned"));
        add(OPSounds.RITUAL_FORGOTTEN, soundDefinition("ritual/forgotten").subtitle("subtitles.ordemparanormal.ritual_forgotten"));
        add(OPSounds.CORPSE_HURT, soundDefinition("blood/hurt_1")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood/hurt_2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood/hurt_3")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood/hurt_4")))
                .subtitle("subtitles.ordemparanormal.corpse.hurt"));
        add(OPSounds.ZUMBI_SANGUE_HURT, soundDefinition("blood/hurt_1")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood/hurt_2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood/hurt_3")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood/hurt_4")))
                .subtitle("subtitles.ordemparanormal.zumbi_sangue.hurt"));

        add(OPSounds.RITUAL_USED, soundDefinition("ritual/used").subtitle("subtitles.ordemparanormal.ritual.used"));
        add(OPSounds.RITUAL_FAILED, soundDefinition("ritual/failed").subtitle("subtitles.ordemparanormal.ritual.failed"));
        add(OPSounds.INGREDIENT_BLOOD, soundDefinition("ingredient/blood"));
        add(OPSounds.INGREDIENT_DEATH, soundDefinition("ingredient/death"));
        add(OPSounds.INGREDIENT_ENERGY, soundDefinition("ingredient/energy"));
        add(OPSounds.INGREDIENT_KNOWLEDGE, soundDefinition("ingredient/knowledge"));

        add(OPSounds.BLOOD_POWER_USED, soundDefinition("blood/power_used")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood/power_used_2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood/power_used_3")))
                .subtitle("subtitles.ordemparanormal.blood_power_used"));
        add(OPSounds.ENERGY_POWER_USED, soundDefinition("energy/power_used")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "energy/power_used_2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "energy/power_used_3")))
                .subtitle("subtitles.ordemparanormal.energy_power_used"));
        add(OPSounds.DEATH_POWER_USED, soundDefinition("death/power_used")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "death/power_used_2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "death/power_used_3")))
                .subtitle("subtitles.ordemparanormal.death/power_used"));
        add(OPSounds.KNOWLEDGE_POWER_USED, soundDefinition("knowledge/power_used")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "knowledge/power_used_2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "knowledge/power_used_3")))
                .subtitle("subtitles.ordemparanormal.knowledge_power_used"));
        add(OPSounds.FEAR_POWER_USED, soundDefinition("fear/power_used").subtitle("subtitles.ordemparanormal.fear_power_used"));

        add(OPSounds.VAMPIRISMO_USED, soundDefinition("power/vampirismo_used"));

        add(OPSounds.ABERRACAO_HURT, soundDefinition("blood/hurt_1")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood/hurt_2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood/hurt_3")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood/hurt_4")))
                .subtitle("subtitles.ordemparanormal.aberracao.hurt"));
        add(OPSounds.ABERRACAO_IDLE, soundDefinition("aberracao/idle1")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "aberracao/idle2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "aberracao/idle3")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "aberracao/idle4")))
                .subtitle("subtitles.ordemparanormal.aberracao.idle"));
        add(OPSounds.ABERRACAO_STEP, soundDefinition("aberracao/step1")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "aberracao/step2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "aberracao/step3")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "aberracao/step4")))
                .subtitle("subtitles.ordemparanormal.aberracao.step"));
        add(OPSounds.ABERRACAO_ATTACK, soundDefinition("aberracao/attack")
                .subtitle("subtitles.ordemparanormal.aberracao.attack"));
        add(OPSounds.ABERRACAO_ABOCANHAR, soundDefinition("aberracao/abocanhar")
                .subtitle("subtitles.ordemparanormal.aberracao.abocanhar"));
        add(OPSounds.ABERRACAO_DEATH, soundDefinition("aberracao/death")
                .subtitle("subtitles.ordemparanormal.aberracao.death"));

        add(OPSounds.TITA_HURT, soundDefinition("blood/hurt_1")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood/hurt_2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood/hurt_3")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "blood/hurt_4")))
                .subtitle("subtitles.ordemparanormal.tita.hurt"));
        add(OPSounds.TITA_IDLE, soundDefinition("tita_sangue/idle")
                .subtitle("subtitles.ordemparanormal.tita.idle"));
        add(OPSounds.TITA_STEP, soundDefinition("tita_sangue/step1")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "tita_sangue/step2")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "tita_sangue/step3")))
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "tita_sangue/step4")))
                .subtitle("subtitles.ordemparanormal.tita.step"));
        add(OPSounds.TITA_ATTACK, soundDefinition("tita_sangue/attack")
                .subtitle("subtitles.ordemparanormal.tita.attack"));
        add(OPSounds.TITA_BITE, soundDefinition("tita_sangue/bite")
                .subtitle("subtitles.ordemparanormal.tita.bite"));
        add(OPSounds.TITA_DEATH, soundDefinition("tita_sangue/death")
                .subtitle("subtitles.ordemparanormal.tita.death"));

        add(OPSounds.BIDENTE_HIT, soundDefinition("bidente/hit1")
                .with(sound(new ResourceLocation(OrdemParanormal.MOD_ID, "bidente/hit2")))
                .subtitle("subtitles.ordemparanormal.bidente.hit"));
        add(OPSounds.BIDENTE_RIPTIDE_1, soundDefinition("bidente/riptide1")
                .subtitle("subtitles.ordemparanormal.bidente.riptide"));
        add(OPSounds.BIDENTE_RIPTIDE_2, soundDefinition("bidente/riptide2")
                .subtitle("subtitles.ordemparanormal.bidente.riptide"));
        add(OPSounds.BIDENTE_RIPTIDE_3, soundDefinition("bidente/riptide3")
                .subtitle("subtitles.ordemparanormal.bidente.riptide"));
    }
}
