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
import net.minecraft.server.level.ServerPlayer;

public class PowerCommand {
    private static final Command<CommandSourceStack> ADD_POINTS = context -> {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        int amount = IntegerArgumentType.getInteger(context, "amount");

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex ->
                playerNex.setPowerPoints(playerNex.getPowerPoints() + amount));

        context.getSource().sendSuccess(new TranslatableComponent("ordemparanormal.commands.nex.powers.points.add.success"), true);
        return 1;
    };
    private static final Command<CommandSourceStack> REMOVE_POINTS = context -> {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        int amount = IntegerArgumentType.getInteger(context, "amount");

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex ->
                playerNex.setPowerPoints(playerNex.getPowerPoints() - amount));

        context.getSource().sendSuccess(new TranslatableComponent("ordemparanormal.commands.nex.powers.points.remove.success"), true);
        return 1;
    };
    private static final Command<CommandSourceStack> ADD_POWER = context -> {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        String power = StringArgumentType.getString(context, "power");
        OrdemParanormalAPI api = OrdemParanormalAPI.getInstance();

        player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities ->
                playerAbilities.addPower(api.getPower(power)));
        ApiEvents.syncPlayerPowers(player);

        context.getSource().sendSuccess(
                new TranslatableComponent("ordemparanormal.commands.nex.powers.add.success",
                        api.getPower(power).getDisplayName()), true);
        return 1;
    };
    private static final Command<CommandSourceStack> REMOVE_POWER = context -> {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        String power = StringArgumentType.getString(context, "power");
        OrdemParanormalAPI api = OrdemParanormalAPI.getInstance();

        player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities ->
                playerAbilities.removePower(api.getPower(power)));
        ApiEvents.syncPlayerPowers(player);

        context.getSource().sendSuccess(
                new TranslatableComponent("ordemparanormal.commands.nex.powers.remove.success",
                        api.getPower(power).getDisplayName()), true);
        return 1;
    };
    public static ArgumentBuilder<CommandSourceStack, ?> register(){
        return Commands.literal("powers")
                .then(Commands.literal("points")
                        .then(Commands.literal("add").then(Commands.argument("amount", IntegerArgumentType.integer(1, 99)).executes(ADD_POINTS)))
                        .then(Commands.literal("remove").then(Commands.argument("amount", IntegerArgumentType.integer(1, 99)).executes(REMOVE_POINTS))))
                .then(Commands.literal("add").then(Commands.argument("power", StringArgumentType.greedyString()).suggests(
                        (context, builder) -> SharedSuggestionProvider.suggest(OrdemParanormalAPI.getInstance().getPowerMap().keySet(), builder)
                ).executes(ADD_POWER)))
                .then(Commands.literal("remove").then(Commands.argument("power", StringArgumentType.greedyString()).suggests(
                        (context, builder) -> SharedSuggestionProvider.suggest(OrdemParanormalAPI.getInstance().getPowerMap().keySet(), builder)
                ).executes(REMOVE_POWER)));
    }
}
