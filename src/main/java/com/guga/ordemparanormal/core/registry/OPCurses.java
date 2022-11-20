package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.common.curses.*;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.resources.ResourceLocation;

import static com.guga.ordemparanormal.api.ParanormalElement.*;
import static com.guga.ordemparanormal.api.curses.CurseCategory.*;
import static com.guga.ordemparanormal.core.OrdemParanormal.MOD_ID;
import static net.minecraft.world.entity.EquipmentSlot.*;

public class OPCurses {
    public static final AbstractCurse ATROZ = new Atroz(new ResourceLocation(MOD_ID, "atroz"), SANGUE, WEAPON, MAINHAND);
    public static final AbstractCurse VELOZ = new Veloz(new ResourceLocation(MOD_ID, "veloz"), ENERGIA, WEAPON, MAINHAND);
    public static final AbstractCurse DECADENTE = new Decadente(new ResourceLocation(MOD_ID, "decadente"), MORTE, WEAPON, MAINHAND);
    public static final AbstractCurse AMALDICOADA = new Amaldicoada(new ResourceLocation(MOD_ID, "amaldicoada"), CONHECIMENTO, WEAPON, MAINHAND);
    public static final AbstractCurse LAMINA_MEDO = new LaminaMedo(new ResourceLocation(MOD_ID, "lamina_do_medo"), MEDO, WEAPON, MAINHAND);
    public static final AbstractCurse SANGUINARIA = new Sanguinaria(new ResourceLocation(MOD_ID, "sanguinaria"), SANGUE, WEAPON, MAINHAND);
    public static final AbstractCurse LANCINANTE = new Lancinante(new ResourceLocation(MOD_ID, "lancinante"), SANGUE, WEAPON, MAINHAND);
    public static final AbstractCurse ANTI_CONHECIMENTO = new AntiElemento(new ResourceLocation(MOD_ID, "anti_conhecimento"), CONHECIMENTO, WEAPON, CONHECIMENTO, MAINHAND);
    public static final AbstractCurse ANTI_SANGUE = new AntiElemento(new ResourceLocation(MOD_ID, "anti_sangue"), CONHECIMENTO, WEAPON, SANGUE, MAINHAND);
    public static final AbstractCurse ANTI_ENERGIA = new AntiElemento(new ResourceLocation(MOD_ID, "anti_energia"), CONHECIMENTO, WEAPON, ENERGIA, MAINHAND);
    public static final AbstractCurse ANTI_MORTE = new AntiElemento(new ResourceLocation(MOD_ID, "anti_morte"), CONHECIMENTO, WEAPON, MORTE, MAINHAND);
    public static final AbstractCurse LEPIDA = new Lepida(new ResourceLocation(MOD_ID, "lepida"), ENERGIA, ARMOR_FEET, FEET);
    public static final AbstractCurse VOLTAICA = new Voltaica(new ResourceLocation(MOD_ID, "voltaica"), ENERGIA, ARMOR_CHEST, CHEST);
    public static final AbstractCurse ENERGETICA = new Energetica(new ResourceLocation(MOD_ID, "energetica"), ENERGIA, WEAPON, MAINHAND);
    public static final AbstractCurse CONSUMIDORA = new Consumidora(new ResourceLocation(MOD_ID, "consumidora"), MORTE, WEAPON, MAINHAND);
    public static final AbstractCurse SORTUDA = new Sortuda(new ResourceLocation(MOD_ID, "sortuda"), ENERGIA, DIGGER, MAINHAND);
    public static final AbstractCurse ARDENTE = new Ardente(new ResourceLocation(MOD_ID, "ardente"), ENERGIA, DIGGER, MAINHAND);

    /**
     * Registra as maldições
     */
    public static void setup(){
        registerCurse(ATROZ);
        registerCurse(VELOZ);
        registerCurse(DECADENTE);
        registerCurse(AMALDICOADA);
        registerCurse(LAMINA_MEDO);
        registerCurse(SANGUINARIA);
        registerCurse(LANCINANTE);
        registerCurse(ANTI_CONHECIMENTO);
        registerCurse(ANTI_SANGUE);
        registerCurse(ANTI_ENERGIA);
        registerCurse(ANTI_MORTE);
        registerCurse(LEPIDA);
        registerCurse(VOLTAICA);
        registerCurse(ENERGETICA);
        registerCurse(CONSUMIDORA);
        registerCurse(SORTUDA);
        registerCurse(ARDENTE);
    }
    public static void registerCurse(AbstractCurse curse){
        OrdemParanormalAPI.getInstance().registerCurse(curse.getId(), curse);
    }
}
