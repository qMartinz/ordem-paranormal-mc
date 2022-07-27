package com.guga.ordemparanormal.api;

import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrdemParanormalAPI {
    private ConcurrentHashMap<String, AbstractRitual> ritualMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, PlayerPower> powerMap = new ConcurrentHashMap<>();
    public AbstractRitual registerRitual(String id, AbstractRitual ritual){
        return ritualMap.put(id, ritual);
    }
    public PlayerPower registerPower(String id, PlayerPower ability){
        return powerMap.put(id, ability);
    }
    public void printHashmap(){
        System.out.println(ritualMap.toString());
    }
    public Map<String, AbstractRitual> getRitualMap() {
        return ritualMap;
    }
    public Map<String, PlayerPower> getPowerMap() {
        return powerMap;
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
    public @Nullable PlayerPower getPower(String id){
        if(!powerMap.containsKey(id))
            return null;
        try{
            return powerMap.get(id);
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
