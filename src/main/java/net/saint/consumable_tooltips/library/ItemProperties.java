package net.saint.consumable_tooltips.library;

import net.minecraft.util.Identifier;

public class ItemProperties {

	public Identifier id;

	public int health;

	public int nutrition;
	public int saturation;

	public int defaultNutrition;
	public int defaultSaturation;

	public int hydration;
	public boolean isContaminated;

	public ItemProperties(Identifier id, int health, int nutrition, int saturation, int defaultNutrition,
			int defaultSaturation, int hydration, boolean isContaminated) {
		this.id = id;

		this.health = health;

		this.nutrition = nutrition;
		this.saturation = saturation;
		this.defaultNutrition = defaultNutrition;
		this.defaultSaturation = defaultSaturation;

		this.hydration = hydration;
		this.isContaminated = isContaminated;
	}

}