/*
 * package com.guga.ordemparanormal.common.commands;
 * 
 * import org.antlr.v4.parse.ANTLRParser.finallyClause_return;
 * 
 * import com.guga.ordemparanormal.api.OrdemParanormalAPI;
 * import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
 * import
 * com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
 * import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
 * import com.guga.ordemparanormal.api.capabilities.network.SyncAbilities;
 * import com.guga.ordemparanormal.core.network.Messages;
 * import com.mojang.brigadier.Command;
 * import com.mojang.brigadier.arguments.IntegerArgumentType;
 * import com.mojang.brigadier.arguments.StringArgumentType;
 * import com.mojang.brigadier.builder.ArgumentBuilder;
 * import com.mojang.brigadier.context.CommandContext;
 * import com.mojang.brigadier.exceptions.CommandSyntaxException;
 * import net.minecraft.commands.CommandSourceStack;
 * import net.minecraft.commands.Commands;
 * import net.minecraft.commands.arguments.EntityArgument;
 * import net.minecraft.server.level.ServerPlayer;
 * 
 * public class AddRitualCommand implements Command<CommandSourceStack> {
 * private static final AddRitualCommand COMMAND = new AddRitualCommand();
 * 
 * public static ArgumentBuilder<CommandSourceStack, ?> register() {
 * return Commands.literal("addRitual")
 * .then(Commands.argument("ritual",
 * StringArgumentType.string()).executes(COMMAND));
 * }
 * 
 * @Override
 * public int run(CommandContext<CommandSourceStack> context) throws
 * CommandSyntaxException {
 * ServerPlayer player = EntityArgument.getPlayer(context, "player");
 * // String param = StringArgumentType.getString(context, "ritual");
 * AbstractRitual ritual = OrdemParanormalAPI.getInstance()
 * .getRitual(StringArgumentType.getString(context, "ritual"));
 * 
 * player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(cap
 * -> {
 * Messages.sendToPlayer(new SyncAbilities(cap.serializeNBT()), player);
 * cap.learnRitual(ritual);
 * });
 * 
 * return 1;
 * }
 * 
 * }
 */