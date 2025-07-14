package net.saint.consumable_tooltips;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.saint.consumable_tooltips.compat.CapsaicinAccess;
import net.saint.consumable_tooltips.compat.DehydrationAccess;
import net.saint.consumable_tooltips.config.ModConfig;

public class Mod implements ModInitializer {

	public static final Identifier IDENTIFIER = new Identifier("consumable_tooltips");

	public static final Logger LOGGER = LoggerFactory.getLogger("consumable_tooltips");

	public static DehydrationAccess DEHYDRATION_ACCESS;

	public static CapsaicinAccess CAPSAICIN_ACCESS;

	public static ModConfig CONFIG;

	@Override
	public void onInitialize() {
		AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

		DEHYDRATION_ACCESS = new DehydrationAccess();
		CAPSAICIN_ACCESS = new CapsaicinAccess();

		ModCommands.registerCommands();
	}

}
