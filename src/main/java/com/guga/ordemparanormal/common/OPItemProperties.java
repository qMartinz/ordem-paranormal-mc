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

        // Propriedade de maldições para picaretas
        ItemProperties.register(Items.WOODEN_PICKAXE,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.STONE_PICKAXE,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.IRON_PICKAXE,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.GOLDEN_PICKAXE,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.DIAMOND_PICKAXE,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.NETHERITE_PICKAXE,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));

        // Propriedade de maldições para machados
        ItemProperties.register(Items.WOODEN_AXE,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.STONE_AXE,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.IRON_AXE,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.GOLDEN_AXE,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.DIAMOND_AXE,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.NETHERITE_AXE,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));

        // Propriedade de maldições para pás
        ItemProperties.register(Items.WOODEN_SHOVEL,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.STONE_SHOVEL,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.IRON_SHOVEL,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.GOLDEN_SHOVEL,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.DIAMOND_SHOVEL,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
        ItemProperties.register(Items.NETHERITE_SHOVEL,
                new ResourceLocation(OrdemParanormal.MOD_ID, "curses"), (stack, level, entity, id) -> getCurseFloat(stack));
    }
    /**
    * Retorna um float específico baseado em quais maldições o item possui.
     */
    protected static float getCurseFloat(ItemStack stack){
        float curse_float = 0f;
        List<AbstractCurse> curses = CurseHelper.getCurses(stack).stream().map(CurseInstance::getCurse).toList();

        if (!curses.isEmpty() && curses.stream().noneMatch(Objects::isNull)) {
            if (curses.stream().allMatch(curse -> curse.element == ParanormalElement.SANGUE)) {
                curse_float = 0.1f;
            } else if (curses.stream().allMatch(curse -> curse.element == ParanormalElement.MORTE)) {
                curse_float = 0.2f;
            } else if (curses.stream().allMatch(curse -> curse.element == ParanormalElement.CONHECIMENTO)) {
                curse_float = 0.3f;
            } else if (curses.stream().allMatch(curse -> curse.element == ParanormalElement.ENERGIA)) {
                curse_float = 0.4f;
            } else if (curses.stream().anyMatch(curse -> curse.element == ParanormalElement.SANGUE) &&
                    curses.stream().anyMatch(curse -> curse.element == ParanormalElement.ENERGIA)) {
                curse_float = 0.5f;
            } else if (curses.stream().anyMatch(curse -> curse.element == ParanormalElement.MORTE) &&
                    curses.stream().anyMatch(curse -> curse.element == ParanormalElement.CONHECIMENTO)) {
                curse_float = 0.6f;
            } else if (curses.stream().allMatch(curse -> curse.element == ParanormalElement.MEDO)) {
                curse_float = 0.7f;
            } else if (curses.stream().anyMatch(curse -> curse.element == ParanormalElement.SANGUE) &&
                    curses.stream().anyMatch(curse -> curse.element == ParanormalElement.ENERGIA) &&
                    curses.stream().anyMatch(curse -> curse.element == ParanormalElement.MEDO)) {
                curse_float = 0.8f;
            } else if (curses.stream().anyMatch(curse -> curse.element == ParanormalElement.MORTE) &&
                    curses.stream().anyMatch(curse -> curse.element == ParanormalElement.CONHECIMENTO) &&
                    curses.stream().anyMatch(curse -> curse.element == ParanormalElement.MEDO)) {
                curse_float = 0.9f;
            }
        }
        return curse_float;
    }
}
