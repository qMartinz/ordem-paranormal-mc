package com.guga.ordemparanormal.datagen.client.lang;

import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }
    public void add(AbstractRitual ritual, String name, String description) {
        add(ritual.getTranslationKey(), name);
        add(ritual.getTranslationKey() + ".description", description);
    }
    public void add(PlayerPower power, String name, String description) {
        add(power.getTranslationKey(), name);
        add(power.getTranslationKey() + ".description", description);
    }
    public void add(AbstractCurse curse, String name) {
        add(curse.getTranslationKey(), name);
    }
    public void add(VillagerProfession key, String name) {
        super.add("entity.minecraft.villager." + OrdemParanormal.MOD_ID + "." + key.getName(), name);
    }
    public void addDeath(String key, String translation){
        add("death.attack." + key, translation);
    }
    public void addDeathEnemy(String key, String translation){
        add("death.attack." + key + ".player", translation);
    }
    public void addAdvancement(String id, String title, String description){
        add("ordemparanormal.advancement." + id + ".title", title);
        add("ordemparanormal.advancement." + id + ".description", description);
    }
    public void addSubtitle(String id, String subtitle){
        add("subtitles.ordemparanormal." + id, subtitle);
    }
    @Override
    protected void addTranslations() {}
}
