package com.guga.ordemparanormal.common.commands;

import com.guga.ordemparanormal.api.ApiEvents;
import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class RitualCommand {
    private static final Command<CommandSourceStack> ADD_SLOTS = context -> {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        int amount = IntegerArgumentType.getInteger(context, "amount");

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex ->
                playerNex.setRitualSlots(playerNex.getRitualSlots() + amount));

        context.getSource().sendSuccess(new TranslatableComponent("ordemparanormal.commands.nex.rituals.slots.add.success"), true);
        return 1;
    };
    private static final Command<CommandSourceStack> REMOVE_SLOTS = context -> {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        int amount = IntegerArgumentType.getInteger(context, "amount");

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex ->
                playerNex.setRitualSlots(playerNex.getRitualSlots() - amount));

        context.getSource().sendSuccess(new TranslatableComponent("ordemparanormal.commands.nex.rituals.slots.remove.success"), true);
        return 1;
    };
    private static final Command<CommandSourceStack> ADD_RITUAL = context -> {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        ResourceLocation ritual = ResourceLocation.tryParse(StringArgumentType.getString(context, "ritual"));
        OrdemParanormalAPI api = OrdemParanormalAPI.getInstance();

        player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities ->
                playerAbilities.learnRitual(api.getRitual(ritual)));
        ApiEvents.syncPlayerPowers(player);

        context.getSource().sendSuccess(
                new TranslatableComponent("ordemparanormal.commands.nex.rituals.add.success",
                        api.getRitual(ritual).getDisplayName()), true);
        return 1;
    };
    private static final Command<CommandSourceStack> REMOVE_RITUAL = context -> {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        ResourceLocation ritual = ResourceLocation.tryParse(StringArgumentType.getString(context, "ritual"));
        OrdemParanormalAPI api = OrdemParanormalAPI.getInstance();

        player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities ->
                playerAbilities.forgetRitual(api.getRitual(ritual)));
        ApiEvents.syncPlayerPowers(player);

        context.getSource().sendSuccess(
                new TranslatableComponent("ordemparanormal.commands.nex.rituals.remove.success",
                        api.getRitual(ritual).getDisplayName()), true);
        return 1;
    };
    public static ArgumentBuilder<CommandSourceStack, ?> register(){
        return Commands.literal("rituals")
                .then(Commands.literal("slots")
                        .then(Commands.literal("add").then(Commands.argument("amount", IntegerArgumentType.integer(1, 99)).executes(ADD_SLOTS)))
                        .then(Commands.literal("remove").then(Commands.argument("amount", IntegerArgumentType.integer(1, 99)).executes(REMOVE_SLOTS))))
                .then(Commands.literal("add").then(Commands.argument("ritual", StringArgumentType.greedyString()).suggests(
                        (context, builder) -> SharedSuggestionProvider.suggest(OrdemParanormalAPI.getInstance().getRitualMap().keySet().stream().map(ResourceLocation::toString), builder)
                ).executes(ADD_RITUAL)))
                .then(Commands.literal("remove").then(Commands.argument("ritual", StringArgumentType.greedyString()).suggests(
                        (context, builder) -> SharedSuggestionProvider.suggest(OrdemParanormalAPI.getInstance().getRitualMap().keySet().stream().map(ResourceLocation::toString), builder)
                ).executes(REMOVE_RITUAL)));
    }
}
