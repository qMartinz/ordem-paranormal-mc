/*
 * package com.guga.ordemparanormal.common.commands;
 * 
 * import com.mojang.brigadier.CommandDispatcher;
 * import com.mojang.brigadier.builder.LiteralArgumentBuilder;
 * import net.minecraft.commands.CommandSourceStack;
 * import net.minecraft.commands.Commands;
 * import net.minecraft.commands.arguments.EntityArgument;
 * 
 * public class RitualCommands {
 * public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
 * {
 * dispatcher.register(
 * LiteralArgumentBuilder.<CommandSourceStack>literal("ritual")
 * .requires(source -> source.hasPermission(2))
 * .then(Commands.argument("player", EntityArgument.player())
 * .then(AddRitualCommand.register())));
 * }
 * }
 */