package com.guga.ordemparanormal.common.commands;

import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.core.network.Messages;
import com.guga.ordemparanormal.core.network.Packets;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;

public class AddXpCommand implements Command<CommandSourceStack> {
    private static final AddXpCommand COMMAND = new AddXpCommand();
    static ArgumentBuilder<CommandSourceStack, ?> register(){
        return Commands.literal("addxp").then(Commands.argument("amount", IntegerArgumentType.integer()).executes(COMMAND));
    }
    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        int amount = IntegerArgumentType.getInteger(context, "amount");

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            playerNex.addNexXp(amount);
            Messages.sendToPlayer(new Packets.SyncNexToClient(playerNex.serializeNBT()), player);
        });

        return 1;
    }
}
