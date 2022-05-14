package com.guga.ordemparanormal.common.commands;

import com.guga.ordemparanormal.common.capabilities.nexplayer.NexModel;
import com.guga.ordemparanormal.common.network.SyncNex;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;

public class ResetNexCommand implements Command<CommandSourceStack> {
    private static final ResetNexCommand COMMAND = new ResetNexCommand();

    static ArgumentBuilder<CommandSourceStack, ?> register(){
        return Commands.literal("reset").executes(COMMAND);
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");

        NexModel.get(player).setNexLevel(0);
        SyncNex.send(player);

        return 1;
    }
}
