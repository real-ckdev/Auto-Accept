package net.ckdev.autoaccept;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class AutoAccept implements ModInitializer {
	public static final String MOD_ID = "autoaccept";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static boolean autoAcceptEnabled = true;

	private static final Path CONFIG_PATH = FabricLoader.getInstance()
			.getConfigDir().resolve("autoaccept.properties");

	@Override
	public void onInitialize() {
		loadConfig();
	}

	public static void loadConfig() {
		if (!Files.exists(CONFIG_PATH)) {
			saveConfig();
			return;
		}
		try {
			Properties props = new Properties();
			props.load(Files.newInputStream(CONFIG_PATH));
			autoAcceptEnabled = Boolean.parseBoolean(props.getProperty("autoAcceptEnabled", "true"));
		} catch (IOException e) {
			LOGGER.error("Failed to load config", e);
		}
	}

	public static void saveConfig() {
		try {
			Properties props = new Properties();
			props.setProperty("autoAcceptEnabled", String.valueOf(autoAcceptEnabled));
			props.store(Files.newOutputStream(CONFIG_PATH), "Come On In Config");
		} catch (IOException e) {
			LOGGER.error("Failed to save config", e);
		}
	}
}