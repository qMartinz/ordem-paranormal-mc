package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.common.curses.Atroz;
import com.guga.ordemparanormal.common.curses.Veloz;

public class OPCurses {
    public static final AbstractCurse ATROZ = new Atroz();
    public static final AbstractCurse VELOZ = new Veloz();

    /**
     * Registra as maldições
     */
    public static void setup(){
        registerCurse(OPCurses.ATROZ);
        registerCurse(OPCurses.VELOZ);
    }
    public static void registerCurse(AbstractCurse curse){
        OrdemParanormalAPI.getInstance().registerCurse(curse.getId(), curse);
    }
}
