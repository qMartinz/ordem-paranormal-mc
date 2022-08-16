package com.guga.ordemparanormal.common.commands;

import com.guga.ordemparanormal.api.capabilities.data.PlayerPowersProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.capabilities.network.SyncPowers;
import com.guga.ordemparanormal.core.network.Messages;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.HashSet;

public class ResetNexCommand implements Command<CommandSourceStack> {
    private static final ResetNexCommand COMMAND = new ResetNexCommand();

    static ArgumentBuilder<CommandSourceStack, ?> register(){
        return Commands.literal("reset").executes(COMMAND);
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            playerNex.setNex(0);
            playerNex.setNexXp(0);
            playerNex.setAttributePoints(0);
            playerNex.setPowerPoints(0);
            playerNex.setAttributes(new int[]{0, 0, 0});
            playerNex.setRitualSlots(0);
            playerNex.setCurrentEffort(playerNex.getMaxEffort());
        });
        player.getCapability(PlayerPowersProvider.PLAYER_POWERS).ifPresent(playerAbilities -> {
            playerAbilities.setKnownRituals(new HashSet<>());
            playerAbilities.setPowers(new HashSet<>());
            playerAbilities.setActivePowers(new HashMap<>());
            Messages.sendToPlayer(new SyncPowers(playerAbilities.serializeNBT()), player);
        });

        return 1;
    }
}
