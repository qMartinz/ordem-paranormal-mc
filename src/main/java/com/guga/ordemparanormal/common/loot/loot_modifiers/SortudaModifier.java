package com.guga.ordemparanormal.common.loot.loot_modifiers;

import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
import java.util.Random;

public class SortudaModifier extends LootModifier {
    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    protected SortudaModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }
    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        return generatedLoot.stream().peek(stack ->
                {
                    if (Mth.nextInt(context.getRandom(), 1, 20) >= 6) {
                        stack.setCount(stack.getCount() * Mth.nextInt(context.getRandom(), 1, 7));
                    }
                })
                .collect(ObjectArrayList.toList());
    }
    public static class Serializer extends GlobalLootModifierSerializer<SortudaModifier> {
        @Override
        public SortudaModifier read(ResourceLocation name, JsonObject json, LootItemCondition[] conditions) {
            return new SortudaModifier(conditions);
        }
        @Override
        public JsonObject write(SortudaModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
