package dev.cape.commands.suggestor;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.server.command.ServerCommandSource;

import java.util.concurrent.CompletableFuture;

public class CapeSuggestionProvider implements SuggestionProvider<ServerCommandSource> {

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) throws CommandSyntaxException {
        String[] capes = {"15anniversary", "mcc", "minecon2011", "minecon2012", "minecon2013", "minecon2015", "minecon2016", "mojang", "mojang_classic", "cobalt", "mojira"};

        for (String cape : capes) {
            builder.suggest(cape);
        }

        return builder.buildFuture();
    }
}
