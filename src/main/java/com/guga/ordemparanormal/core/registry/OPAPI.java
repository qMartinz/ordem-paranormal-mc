package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.ability.PlayerAbility;
import com.guga.ordemparanormal.api.ritual.AbstractRitual;
import com.guga.ordemparanormal.common.ritual.SkinningRitual;

public class OPAPI {
    public static final AbstractRitual SKINNING = new SkinningRitual();
    public static void setup(){
        registerRitual(SKINNING);
    }
    public static void registerRitual(AbstractRitual ritual){
        OrdemParanormalAPI.getInstance().registerRitual(ritual.getName(), ritual);
    }
    public static void registerAbility(PlayerAbility ability){
        OrdemParanormalAPI.getInstance().registerAbility(ability.getName(), ability);
    }
}
