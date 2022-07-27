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
public class SetNexCommand implements Command<CommandSourceStack> {
    private static final SetNexCommand COMMAND = new SetNexCommand();
    static ArgumentBuilder<CommandSourceStack, ?> register(){
        return Commands.literal("set").then(Commands.argument("percent", IntegerArgumentType.integer(0, 99)).executes(COMMAND));
    }
    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        int percent = IntegerArgumentType.getInteger(context, "percent");

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            playerNex.setNex(percent);
        });

        return 1;
    }
}
