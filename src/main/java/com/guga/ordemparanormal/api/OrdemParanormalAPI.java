package com.guga.ordemparanormal.api;

import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrdemParanormalAPI {
    private ConcurrentHashMap<ResourceLocation, AbstractRitual> ritualMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<ResourceLocation, PlayerPower> powerMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<ResourceLocation, AbstractCurse> curseMap = new ConcurrentHashMap<>();
    public AbstractRitual registerRitual(ResourceLocation id, AbstractRitual ritual){
        return ritualMap.put(id, ritual);
    }
    public PlayerPower registerPower(ResourceLocation id, PlayerPower ability){
        return powerMap.put(id, ability);
    }
    public AbstractCurse registerCurse(ResourceLocation id, AbstractCurse curse){
        return curseMap.put(id, curse);
    }
    public Map<ResourceLocation, AbstractRitual> getRitualMap() {
        return ritualMap;
    }
    public Map<ResourceLocation, PlayerPower> getPowerMap() {
        return powerMap;
    }
    public Map<ResourceLocation, AbstractCurse> getCurseMap(){ return curseMap; }
    public @Nullable AbstractRitual getRitual(ResourceLocation id){
        if(!ritualMap.containsKey(id))
            return null;
        try{
            return ritualMap.get(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public @Nullable PlayerPower getPower(ResourceLocation id){
        if(!powerMap.containsKey(id))
            return null;
        try{
            return powerMap.get(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public @Nullable AbstractCurse getCurse(ResourceLocation id){
        if(!curseMap.containsKey(id))
            return null;
        try{
            return curseMap.get(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private OrdemParanormalAPI(){}
    public static OrdemParanormalAPI getInstance() {
        return ORDEM_PARANORMAL_API;
    }
    private static final OrdemParanormalAPI ORDEM_PARANORMAL_API = new OrdemParanormalAPI();
}
