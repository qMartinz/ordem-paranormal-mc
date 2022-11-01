package com.guga.ordemparanormal.datagen.server;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPBlocks;
import com.guga.ordemparanormal.core.registry.OPItems;
import com.guga.ordemparanormal.core.registry.OPTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(OPItems.ORGAO.get()), OPItems.CINZAS.get(), 0.35F, 250)
                .unlockedBy("has_organ", has(OPItems.ORGAO.get())).save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(OPItems.COMPONENTE_SANGUE.get())
                .define('X', OPItems.ORGAO.get()).define('Y', OPTags.RAW_MEATS).define('Z', Items.ROTTEN_FLESH)
                .define('S', Tags.Items.STRING).define('L', Tags.Items.LEATHER)
                .pattern("Z Y").pattern("SXS").pattern("LLL")
                .unlockedBy("has_organ", has(OPItems.ORGAO.get())).save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(OPItems.COMPONENTE_CONHECIMENTO.get())
                .define('X', Items.GLOWSTONE).define('Y', Items.RAW_GOLD).define('Z', Tags.Items.GLASS)
                .define('S', Tags.Items.STRING).define('L', Tags.Items.LEATHER)
                .pattern("Z Y").pattern("SXS").pattern("LLL")
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE)).save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(OPItems.COMPONENTE_ENERGIA.get())
                .define('X', Items.SOUL_TORCH).define('Y', Items.AMETHYST_SHARD).define('Z', Items.RAW_COPPER)
                .define('S', Tags.Items.STRING).define('L', Tags.Items.LEATHER)
                .pattern("Z Y").pattern("SXS").pattern("LLL")
                .unlockedBy("has_soul_torch", has(Items.SOUL_TORCH)).save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(OPItems.COMPONENTE_MORTE.get())
                .define('X', OPItems.CINZAS.get()).define('Y', Items.BONE).define('Z', Tags.Items.SAND)
                .define('S', Tags.Items.STRING).define('L', Tags.Items.LEATHER)
                .pattern("Z Y").pattern("SXS").pattern("LLL")
                .unlockedBy("has_ashes", has(OPItems.CINZAS.get())).save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(OPItems.COMPONENTE_SANGUE.get()).requires(OPItems.COMPONENTE_VAZIO.get())
                .requires(OPItems.ORGAO.get()).requires(OPTags.RAW_MEATS).requires(Items.ROTTEN_FLESH)
                .unlockedBy("has_empty_component", has(OPItems.COMPONENTE_VAZIO.get()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(OrdemParanormal.MOD_ID, OPItems.COMPONENTE_SANGUE.get().getDescriptionId() + "_refilled"));
        ShapelessRecipeBuilder.shapeless(OPItems.COMPONENTE_CONHECIMENTO.get()).requires(OPItems.COMPONENTE_VAZIO.get())
                .requires(Items.GLOWSTONE).requires(Items.RAW_GOLD).requires(Tags.Items.GLASS)
                .unlockedBy("has_empty_component", has(OPItems.COMPONENTE_VAZIO.get()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(OrdemParanormal.MOD_ID, OPItems.COMPONENTE_CONHECIMENTO.get().getDescriptionId() + "_refilled"));
        ShapelessRecipeBuilder.shapeless(OPItems.COMPONENTE_MORTE.get()).requires(OPItems.COMPONENTE_VAZIO.get())
                .requires(OPItems.CINZAS.get()).requires(Items.BONE).requires(Tags.Items.SAND)
                .unlockedBy("has_empty_component", has(OPItems.COMPONENTE_VAZIO.get()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(OrdemParanormal.MOD_ID, OPItems.COMPONENTE_MORTE.get().getDescriptionId() + "_refilled"));
        ShapelessRecipeBuilder.shapeless(OPItems.COMPONENTE_ENERGIA.get()).requires(OPItems.COMPONENTE_VAZIO.get())
                .requires(Items.SOUL_TORCH).requires(Items.AMETHYST_SHARD).requires(Items.RAW_COPPER)
                .unlockedBy("has_empty_component", has(OPItems.COMPONENTE_VAZIO.get()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(OrdemParanormal.MOD_ID, OPItems.COMPONENTE_ENERGIA.get().getDescriptionId() + "_refilled"));

        ShapedRecipeBuilder.shaped(OPBlocks.ALTAR_TRANSCENDER.get())
                .define('S', Tags.Items.STONE).define('P', Items.PAPER).define('I', Items.INK_SAC)
                .pattern(" I ").pattern(" P ").pattern(" S ")
                .unlockedBy("has_paper", has(Items.PAPER)).save(pFinishedRecipeConsumer);
    }
}
