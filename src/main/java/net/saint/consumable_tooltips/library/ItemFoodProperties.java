package net.saint.consumable_tooltips.library;

import de.siphalor.capsaicin.api.food.FoodProperties;

public class ItemFoodProperties {

	public FoodProperties originalFoodProperties;
	public FoodProperties modifiedFoodProperties;

	public ItemFoodProperties(FoodProperties originalFoodProperties, FoodProperties modifiedFoodProperties) {
		this.originalFoodProperties = originalFoodProperties;
		this.modifiedFoodProperties = modifiedFoodProperties;
	}

}
