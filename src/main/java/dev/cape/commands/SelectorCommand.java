package dev.cape.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.cape.CapeSelector;
import dev.cape.commands.suggestor.CapeSuggestionProvider;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class SelectorCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {

        LiteralArgumentBuilder<ServerCommandSource> commandDispatcher = CommandManager.literal("cape");

        commandDispatcher
                .then(CommandManager.literal("select")
                        .then(CommandManager.argument("cape", StringArgumentType.word())
                                .suggests(new CapeSuggestionProvider())
                                .executes(SelectorCommand::selectCape)))
                .then(CommandManager.literal("remove")
                        .executes(SelectorCommand::removeCape));


        dispatcher.register(commandDispatcher);
    }

    private static int selectCape(CommandContext<ServerCommandSource> source) throws CommandSyntaxException {
        String cape = StringArgumentType.getString(source, "cape");
        if (cape != null) CapeSelector.capeSelectedID = cape;

        return Command.SINGLE_SUCCESS;
    }

    private static int removeCape(CommandContext<ServerCommandSource> source) throws CommandSyntaxException {
        CapeSelector.capeSelectedID = null;
        return Command.SINGLE_SUCCESS;
    }
}
