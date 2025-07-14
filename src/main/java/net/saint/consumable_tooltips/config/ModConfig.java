package net.saint.consumable_tooltips.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "consumable_tooltips")
public class ModConfig implements ConfigData {

	@Comment("Enable health rendering in tooltip.")
	public boolean enableHealthTooltip = true;

	@Comment("Enable satiety rendering in tooltip.")
	public boolean enableSatietyTooltip = true;

	@Comment("Enable saturation rendering in tooltip.")
	public boolean enableSaturationTooltip = true;

	@Comment("Enable hydration rendering in tooltip.")
	public boolean enableHydrationTooltip = true;

	@Comment("Enable currency value rendering in tooltip.")
	public boolean enableCurrencyValueTooltip = true;

}