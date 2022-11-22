package com.guga.ordemparanormal.common.loot.loot_modifiers;

import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArdenteModifier extends LootModifier {
    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    protected ArdenteModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }
    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        return generatedLoot.stream().map(stack -> {
            ItemStack smelted = context.getLevel().getRecipeManager()
                    .getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), context.getLevel())
                    .map(SmeltingRecipe::getResultItem)
                    .filter(itemStack -> !itemStack.isEmpty())
                    .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                    .orElse(stack);

            if (Mth.nextInt(context.getRandom(), 1, 20) >= 7){
                smelted.setCount(smelted.getCount() * Mth.nextInt(context.getRandom(), 1, 3));
            }

            if (smelted != stack) {
                ExperienceOrb.award(context.getLevel(), context.getParam(LootContextParams.ORIGIN), context.getRandom().nextInt(3) + 1);
            }
            return smelted;
        }).collect(ObjectArrayList.toList());
    }
    public static class Serializer extends GlobalLootModifierSerializer<ArdenteModifier> {
        @Override
        public ArdenteModifier read(ResourceLocation name, JsonObject json, LootItemCondition[] conditions) {
            return new ArdenteModifier(conditions);
        }
        @Override
        public JsonObject write(ArdenteModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
