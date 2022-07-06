package com.guga.ordemparanormal.api;

import com.guga.ordemparanormal.api.ability.PlayerAbility;
import com.guga.ordemparanormal.api.ritual.AbstractRitual;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrdemParanormalAPI {
    private ConcurrentHashMap<String, AbstractRitual> ritualMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, PlayerAbility> abilityMap = new ConcurrentHashMap<>();
    public AbstractRitual registerRitual(String id, AbstractRitual ritual){
        return ritualMap.put(id, ritual);
    }
    public PlayerAbility registerAbility(String id, PlayerAbility ability){
        return abilityMap.put(id, ability);
    }
    public void printHashmap(){
        System.out.println(ritualMap.toString());
    }
    public Map<String, AbstractRitual> getRitualMap() {
        return ritualMap;
    }
    public Map<String, PlayerAbility> getAbilityMap() {
        return abilityMap;
    }
    public @Nullable AbstractRitual getRitual(String id){
        if(!ritualMap.containsKey(id))
            return null;
        try{
            return ritualMap.get(id).getClass().newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public @Nullable PlayerAbility getAbility(String id){
        if(!abilityMap.containsKey(id))
            return null;
        try{
            return abilityMap.get(id).getClass().newInstance();
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
