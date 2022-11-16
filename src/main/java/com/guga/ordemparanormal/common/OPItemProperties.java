package com.guga.ordemparanormal.common;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseHelper;
import com.guga.ordemparanormal.api.curses.CurseInstance;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OPItemProperties {
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
        List<AbstractCurse> curses = CurseHelper.getCurses(stack).stream().map(CurseInstance::getCurse).toList();

        if (!curses.isEmpty() && curses.stream().noneMatch(Objects::isNull)) {
            if (curses.stream().allMatch(curse -> curse.element == ParanormalElement.SANGUE)) curse_float = 1f;

            if (curses.stream().allMatch(curse -> curse.element == ParanormalElement.MORTE)) curse_float = 2f;

            if (curses.stream().allMatch(curse -> curse.element == ParanormalElement.CONHECIMENTO)) curse_float = 3f;

            if (curses.stream().allMatch(curse -> curse.element == ParanormalElement.ENERGIA)) curse_float = 4f;

            if (curses.stream().anyMatch(curse -> curse.element == ParanormalElement.SANGUE) &&
                    curses.stream().anyMatch(curse -> curse.element == ParanormalElement.ENERGIA)) curse_float = 5f;

            if (curses.stream().anyMatch(curse -> curse.element == ParanormalElement.MORTE) &&
                    curses.stream().anyMatch(curse -> curse.element == ParanormalElement.CONHECIMENTO)) curse_float = 6f;

            if (curses.stream().allMatch(curse -> curse.element == ParanormalElement.MEDO)) curse_float = 7f;

            if (curses.stream().anyMatch(curse -> curse.element == ParanormalElement.SANGUE) &&
                    curses.stream().anyMatch(curse -> curse.element == ParanormalElement.ENERGIA) &&
                    curses.stream().anyMatch(curse -> curse.element == ParanormalElement.MEDO)) curse_float = 8f;

            if (curses.stream().anyMatch(curse -> curse.element == ParanormalElement.MORTE) &&
                    curses.stream().anyMatch(curse -> curse.element == ParanormalElement.CONHECIMENTO) &&
                    curses.stream().anyMatch(curse -> curse.element == ParanormalElement.MEDO)) curse_float = 9f;
        }
        return curse_float;
    }
}
