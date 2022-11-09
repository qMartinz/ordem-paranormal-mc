package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseCategory;
import com.guga.ordemparanormal.common.curses.*;
import net.minecraft.world.entity.EquipmentSlot;

public class OPCurses {
    public static final AbstractCurse ATROZ = new Atroz("atroz", ParanormalElement.SANGUE, CurseCategory.WEAPON, EquipmentSlot.MAINHAND);
    public static final AbstractCurse VELOZ = new Veloz("veloz", ParanormalElement.ENERGIA, CurseCategory.WEAPON, EquipmentSlot.MAINHAND);
    public static final AbstractCurse DECADENTE = new Decadente("decadente", ParanormalElement.MORTE, CurseCategory.WEAPON, EquipmentSlot.MAINHAND);
    public static final AbstractCurse AMALDICOADA = new Amaldicoada("amaldicoada", ParanormalElement.CONHECIMENTO, CurseCategory.WEAPON, EquipmentSlot.MAINHAND);
    public static final AbstractCurse LAMINA_MEDO = new LaminaMedo("lamina_do_medo", ParanormalElement.MEDO, CurseCategory.WEAPON, EquipmentSlot.MAINHAND);

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
