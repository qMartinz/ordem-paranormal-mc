package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.client.screen.BloodTableMenu;
import com.guga.ordemparanormal.client.screen.DeathTableMenu;
import com.guga.ordemparanormal.client.screen.EnergyTableMenu;
import com.guga.ordemparanormal.client.screen.KnowledgeTableMenu;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OPMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, OrdemParanormal.MOD_ID);
    public static final RegistryObject<MenuType<BloodTableMenu>> BLOOD_TABLE_MENU = registerMenuType(BloodTableMenu::new, "blood_table_menu");
    public static final RegistryObject<MenuType<EnergyTableMenu>> ENERGY_TABLE_MENU = registerMenuType(EnergyTableMenu::new, "energy_table_menu");
    public static final RegistryObject<MenuType<DeathTableMenu>> DEATH_TABLE_MENU = registerMenuType(DeathTableMenu::new, "death_table_menu");
    public static final RegistryObject<MenuType<KnowledgeTableMenu>> KNOWLEDGE_TABLE_MENU = registerMenuType(KnowledgeTableMenu::new, "knowledge_table_menu");
    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name){
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }
}
