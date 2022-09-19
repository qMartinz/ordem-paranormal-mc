package com.guga.ordemparanormal.api.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Objects;

public class CursedItemProperties {
    public static void register(){
        // Propriedade de maldições para espadas
        ItemProperties.register(Items.WOODEN_SWORD,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.STONE_SWORD,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.IRON_SWORD,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.GOLDEN_SWORD,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.DIAMOND_SWORD,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.NETHERITE_SWORD,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
    }
    /**
    * Retorna um float específico baseado em quais maldições o item possui.
     */
    protected static float getCurseFloat(ItemStack stack){
        float curse_float = 0f;

        if (!CurseHelper.getCurses(stack).isEmpty() && CurseHelper.getCurses(stack).stream().noneMatch(Objects::isNull)) {
            if (CurseHelper.getCurses(stack).stream().allMatch(curse -> curse.element == ParanormalElement.SANGUE)) curse_float = 1f;

            if (CurseHelper.getCurses(stack).stream().allMatch(curse -> curse.element == ParanormalElement.MORTE)) curse_float = 2f;

            if (CurseHelper.getCurses(stack).stream().allMatch(curse -> curse.element == ParanormalElement.CONHECIMENTO)) curse_float = 3f;

            if (CurseHelper.getCurses(stack).stream().allMatch(curse -> curse.element == ParanormalElement.ENERGIA)) curse_float = 4f;

            if (CurseHelper.getCurses(stack).stream().anyMatch(curse -> curse.element == ParanormalElement.SANGUE) &&
                    CurseHelper.getCurses(stack).stream().anyMatch(curse -> curse.element == ParanormalElement.ENERGIA)) curse_float = 5f;

            if (CurseHelper.getCurses(stack).stream().anyMatch(curse -> curse.element == ParanormalElement.MORTE) &&
                    CurseHelper.getCurses(stack).stream().anyMatch(curse -> curse.element == ParanormalElement.CONHECIMENTO)) curse_float = 6f;
        }
        return curse_float;
    }
}
