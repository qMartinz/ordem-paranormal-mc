package com.guga.ordemparanormal.common.commands;

import com.guga.ordemparanormal.api.ApiEvents;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.core.network.Messages;
import com.guga.ordemparanormal.core.network.Packets;
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

        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
            playerNex.setNex(0);
            playerNex.setNexXp(0);
            playerNex.setAttributePoints(0);
            playerNex.setPowerPoints(0);
            playerNex.setAttributes(new int[]{0, 0, 0});
            playerNex.setRitualSlots(0);
            playerNex.setCurrentEffort(playerNex.getMaxEffort());
            Messages.sendToPlayer(new Packets.SyncNexToClient(playerNex.serializeNBT()), player);
        });
        player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(playerAbilities -> {
            playerAbilities.clearPowers();
            ApiEvents.syncPlayerPowers(player);
        });

        return 1;
    }
}
