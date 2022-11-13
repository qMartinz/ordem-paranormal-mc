package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.client.screen.BloodTableMenu;
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
            DeferredRegister.create(ForgeRegistries.CONTAINERS, OrdemParanormal.MOD_ID);
    public static final RegistryObject<MenuType<BloodTableMenu>> BLOOD_TABLE_MENU = registerMenuType(BloodTableMenu::new, "blood_table_menu");
    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name){
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }
}
