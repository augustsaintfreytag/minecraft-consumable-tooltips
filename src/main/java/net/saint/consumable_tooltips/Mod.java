package net.saint.consumable_tooltips;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.saint.consumable_tooltips.config.ModConfig;
import net.saint.consumable_tooltips.util.CapsaicinAccess;
import net.saint.consumable_tooltips.util.DehydrationAccess;

public class Mod implements ModInitializer {

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
	}

}
