package dev.cape;

import dev.cape.commands.SelectorCommand;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CapeSelector implements ModInitializer {
	public static final String MOD_ID = "cape-selector";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static String capeSelectedID = null;

	@Override
	public void onInitialize() {
		LOGGER.info("CapeSelector enabled correctly!");

		CommandRegistrationCallback.EVENT.register(SelectorCommand::register);
	}
}