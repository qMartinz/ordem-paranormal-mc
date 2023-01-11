package com.guga.ordemparanormal.common.commands;

import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.core.network.Messages;
import com.guga.ordemparanormal.core.network.Packets;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;

import static com.guga.ordemparanormal.api.attributes.ParanormalAttribute.*;

public class AttributeCommand {
    private static final Command<CommandSourceStack> ADD_POINTS = context -> {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        int amount = IntegerArgumentType.getInteger(context, "amount");

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex ->
                playerNex.setAttributePoints(playerNex.getAttributePoints() + amount));

        context.getSource().sendSuccess(new TranslatableComponent("ordemparanormal.commands.nex.attributes.points.add.success"), true);
        return 1;
    };
    private static final Command<CommandSourceStack> REMOVE_POINTS = context -> {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        int amount = IntegerArgumentType.getInteger(context, "amount");

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            playerNex.setAttributePoints(playerNex.getAttributePoints() - amount);
            Messages.sendToPlayer(new Packets.SyncNexToClient(playerNex.serializeNBT()), player);
        });

        context.getSource().sendSuccess(new TranslatableComponent("ordemparanormal.commands.nex.attributes.points.remove.success"), true);
        return 1;
    };
    private static final Command<CommandSourceStack> ADD_VIGOR = context -> {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        int amount = IntegerArgumentType.getInteger(context, "amount");

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            playerNex.setAttribute(VIGOR, playerNex.getAttribute(VIGOR) + amount);
            Messages.sendToPlayer(new Packets.SyncNexToClient(playerNex.serializeNBT()), player);
        });

        context.getSource().sendSuccess(new TranslatableComponent("ordemparanormal.commands.nex.attributes.vigor.add.success"), true);
        return 1;
    };
    private static final Command<CommandSourceStack> REMOVE_VIGOR = context -> {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        int amount = IntegerArgumentType.getInteger(context, "amount");

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            playerNex.setAttribute(VIGOR, playerNex.getAttribute(VIGOR) - amount);
            Messages.sendToPlayer(new Packets.SyncNexToClient(playerNex.serializeNBT()), player);
        });

        context.getSource().sendSuccess(new TranslatableComponent("ordemparanormal.commands.nex.attributes.vigor.remove.success"), true);
        return 1;
    };
    private static final Command<CommandSourceStack> ADD_STRENGTH = context -> {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        int amount = IntegerArgumentType.getInteger(context, "amount");

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            playerNex.setAttribute(STRENGTH, playerNex.getAttribute(STRENGTH) + amount);
            Messages.sendToPlayer(new Packets.SyncNexToClient(playerNex.serializeNBT()), player);
        });

        context.getSource().sendSuccess(new TranslatableComponent("ordemparanormal.commands.nex.attributes.strength.add.success"), true);
        return 1;
    };
    private static final Command<CommandSourceStack> REMOVE_STRENGTH = context -> {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        int amount = IntegerArgumentType.getInteger(context, "amount");

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            playerNex.setAttribute(STRENGTH, playerNex.getAttribute(STRENGTH) - amount);
            Messages.sendToPlayer(new Packets.SyncNexToClient(playerNex.serializeNBT()), player);
        });

        context.getSource().sendSuccess(new TranslatableComponent("ordemparanormal.commands.nex.attributes.strength.remove.success"), true);
        return 1;
    };
    private static final Command<CommandSourceStack> ADD_PRESENCE = context -> {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        int amount = IntegerArgumentType.getInteger(context, "amount");

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            playerNex.setAttribute(PRESENCE, playerNex.getAttribute(PRESENCE) + amount);
            Messages.sendToPlayer(new Packets.SyncNexToClient(playerNex.serializeNBT()), player);
        });

        context.getSource().sendSuccess(new TranslatableComponent("ordemparanormal.commands.nex.attributes.presence.add.success"), true);
        return 1;
    };
    private static final Command<CommandSourceStack> REMOVE_PRESENCE = context -> {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        int amount = IntegerArgumentType.getInteger(context, "amount");

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            playerNex.setAttribute(PRESENCE, playerNex.getAttribute(PRESENCE) - amount);
            Messages.sendToPlayer(new Packets.SyncNexToClient(playerNex.serializeNBT()), player);
        });

        context.getSource().sendSuccess(new TranslatableComponent("ordemparanormal.commands.nex.attributes.presence.remove.success"), true);
        return 1;
    };
    public static ArgumentBuilder<CommandSourceStack, ?> register(){
        return Commands.literal("attributes")
                .then(Commands.literal("points")
                        .then(Commands.literal("add").then(Commands.argument("amount", IntegerArgumentType.integer(1, 99)).executes(ADD_POINTS)))
                        .then(Commands.literal("remove").then(Commands.argument("amount", IntegerArgumentType.integer(1, 99)).executes(REMOVE_POINTS))))
                .then(Commands.literal("vigor")
                        .then(Commands.literal("add").then(Commands.argument("amount", IntegerArgumentType.integer(1, 99)).executes(ADD_VIGOR)))
                        .then(Commands.literal("remove").then(Commands.argument("amount", IntegerArgumentType.integer(1, 99)).executes(REMOVE_VIGOR))))
                .then(Commands.literal("strength")
                        .then(Commands.literal("add").then(Commands.argument("amount", IntegerArgumentType.integer(1, 99)).executes(ADD_STRENGTH)))
                        .then(Commands.literal("remove").then(Commands.argument("amount", IntegerArgumentType.integer(1, 99)).executes(REMOVE_STRENGTH))))
                .then(Commands.literal("presence")
                        .then(Commands.literal("add").then(Commands.argument("amount", IntegerArgumentType.integer(1, 99)).executes(ADD_PRESENCE)))
                        .then(Commands.literal("remove").then(Commands.argument("amount", IntegerArgumentType.integer(1, 99)).executes(REMOVE_PRESENCE))));
    }
}
