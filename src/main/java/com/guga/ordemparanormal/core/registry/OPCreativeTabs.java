package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.curses.CurseHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.guga.ordemparanormal.core.OrdemParanormal.MOD_ID;

public class OPCreativeTabs {
    public static final CreativeModeTab OP_TAB = new CreativeModeTab(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return OPBlocks.ALTAR_TRANSCENDER.get().asItem().getDefaultInstance();
        }
    };
    public static final CreativeModeTab RITUALS_TAB = new CreativeModeTab(MOD_ID + ".rituals") {
        @Override
        public ItemStack makeIcon() {
            return OPItems.RITUAL_DESCARNAR.get().getDefaultInstance();
        }
    };
    public static final CreativeModeTab MOBS_TAB = new CreativeModeTab(MOD_ID + ".mobs") {
        @Override
        public ItemStack makeIcon() {
            return OPItems.ZUMBI_SANGUE_OVO.get().getDefaultInstance();
        }
    };
    public static final CreativeModeTab CURSES_TAB = new CreativeModeTab(MOD_ID + ".curses") {
        @Override
        public ItemStack makeIcon() {
            return CurseHelper.addCurse(new ItemStack(Items.DIAMOND_SWORD), OPCurses.LAMINA_MEDO);
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> pItems) {
            super.fillItemList(pItems);
            pItems.add(CurseHelper.addCurse(new ItemStack(Items.WOODEN_SWORD), OPCurses.SANGUINARIA));
            pItems.add(CurseHelper.addCurse(new ItemStack(Items.WOODEN_SWORD), OPCurses.LANCINANTE));
            pItems.add(CurseHelper.addCurse(new ItemStack(Items.WOODEN_SWORD), OPCurses.ANTI_CONHECIMENTO));
            pItems.add(CurseHelper.addCurse(new ItemStack(Items.WOODEN_SWORD), OPCurses.ANTI_ENERGIA));
            pItems.add(CurseHelper.addCurse(new ItemStack(Items.WOODEN_SWORD), OPCurses.ANTI_MORTE));
            pItems.add(CurseHelper.addCurse(new ItemStack(Items.WOODEN_SWORD), OPCurses.ANTI_SANGUE));
            pItems.add(CurseHelper.addCurse(new ItemStack(Items.WOODEN_SWORD), OPCurses.ENERGETICA));
            pItems.add(CurseHelper.addCurse(new ItemStack(Items.WOODEN_SWORD), OPCurses.CONSUMIDORA));
            pItems.add(CurseHelper.addCurse(new ItemStack(Items.LEATHER_BOOTS), OPCurses.LEPIDA));
            pItems.add(CurseHelper.addCurse(new ItemStack(Items.LEATHER_CHESTPLATE), OPCurses.VOLTAICA));
            pItems.add(CurseHelper.addCurse(new ItemStack(Items.WOODEN_PICKAXE), OPCurses.ARDENTE));
            pItems.add(CurseHelper.addCurse(new ItemStack(Items.WOODEN_PICKAXE), OPCurses.SORTUDA));
        }
    };
    public static void init(){}
}
