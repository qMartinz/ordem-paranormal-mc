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
        add(OPSounds.RITUAL_LEARNED, soundDefinition("ritual_learned"));
        add(OPSounds.RITUAL_FORGOTTEN, soundDefinition("ritual_forgotten"));
    }
}
