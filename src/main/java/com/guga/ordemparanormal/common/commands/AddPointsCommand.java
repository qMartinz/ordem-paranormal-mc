package com.guga.ordemparanormal.common.commands;

import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;

public class AddPointsCommand implements Command<CommandSourceStack> {
    private static final AddPointsCommand COMMAND = new AddPointsCommand();
    static ArgumentBuilder<CommandSourceStack, ?> register(){
        return Commands.literal("addpoints").then(Commands.argument("amount", IntegerArgumentType.integer(1, 99)).executes(COMMAND));
    }
    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        int amount = IntegerArgumentType.getInteger(context, "amount");

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            playerNex.setAbilityPoints(playerNex.getAbilityPoints() + amount);
        });

        return 1;
    }
}
