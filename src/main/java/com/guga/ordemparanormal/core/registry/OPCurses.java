package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.common.curses.Atroz;
import com.guga.ordemparanormal.common.curses.Decadente;
import com.guga.ordemparanormal.common.curses.Veloz;

public class OPCurses {
    public static final AbstractCurse ATROZ = new Atroz();
    public static final AbstractCurse VELOZ = new Veloz();
    public static final AbstractCurse DECADENTE = new Decadente();

    /**
     * Registra as maldições
     */
    public static void setup(){
        registerCurse(ATROZ);
        registerCurse(VELOZ);
        registerCurse(DECADENTE);
    }
    public static void registerCurse(AbstractCurse curse){
        OrdemParanormalAPI.getInstance().registerCurse(curse.getId(), curse);
    }
}
