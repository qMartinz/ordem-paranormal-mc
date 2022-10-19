package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.common.curses.*;

public class OPCurses {
    public static final AbstractCurse ATROZ = new Atroz();
    public static final AbstractCurse VELOZ = new Veloz();
    public static final AbstractCurse DECADENTE = new Decadente();
    public static final AbstractCurse AMALDICOADA = new Amaldicoada();
    public static final AbstractCurse LAMINA_MEDO = new LaminaMedo();

    /**
     * Registra as maldições
     */
    public static void setup(){
        registerCurse(ATROZ);
        registerCurse(VELOZ);
        registerCurse(DECADENTE);
        registerCurse(AMALDICOADA);
        registerCurse(LAMINA_MEDO);
    }
    public static void registerCurse(AbstractCurse curse){
        OrdemParanormalAPI.getInstance().registerCurse(curse.getId(), curse);
    }
}
