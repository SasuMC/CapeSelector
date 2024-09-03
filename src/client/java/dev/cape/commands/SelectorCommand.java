package dev.cape.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.cape.CapeSelector;
import dev.cape.commands.suggestor.CapeSuggestionProvider;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class SelectorCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(literal("cape")
                .then(literal("select")
                        .then(argument("cape", StringArgumentType.word())
                                .suggests(new CapeSuggestionProvider())
                                .executes(SelectorCommand::selectCape)))
                .then(literal("remove")
                        .executes(SelectorCommand::removeCape)));
    }

    private static int selectCape(CommandContext<FabricClientCommandSource> source) throws CommandSyntaxException {
        String cape = StringArgumentType.getString(source, "cape");
        if (cape != null) CapeSelector.capeSelectedID = cape;

        return Command.SINGLE_SUCCESS;
    }

    private static int removeCape(CommandContext<FabricClientCommandSource> source) throws CommandSyntaxException {
        CapeSelector.capeSelectedID = null;
        return Command.SINGLE_SUCCESS;
    }
}
