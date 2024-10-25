package net.saint.consumable_tooltips.library;

import de.siphalor.capsaicin.api.food.FoodProperties;
import net.minecraft.item.FoodComponent;

public class ItemFoodProperties {

	public int defaultHunger;
	public float defaultSaturationModifier;

	public int modifiedHunger;
	public float modifiedSaturationModifier;

	public ItemFoodProperties(int defaultHunger, float defaultSaturationModifier, int modifiedHunger,
			float modifiedSaturationModifier) {
		this.defaultHunger = defaultHunger;
		this.defaultSaturationModifier = defaultSaturationModifier;
		this.modifiedHunger = modifiedHunger;
		this.modifiedSaturationModifier = modifiedSaturationModifier;
	}

	public static ItemFoodProperties fromDefaultFoodComponent(FoodComponent defaultFoodComponent) {
		return new ItemFoodProperties(
				defaultFoodComponent.getHunger(),
				defaultFoodComponent.getSaturationModifier(),
				defaultFoodComponent.getHunger(),
				defaultFoodComponent.getSaturationModifier());
	}

	public static ItemFoodProperties fromFoodComponentTuple(FoodComponent defaultFoodComponent,
			FoodComponent modifiedFoodComponent) {
		return new ItemFoodProperties(
				defaultFoodComponent.getHunger(),
				defaultFoodComponent.getSaturationModifier(),
				modifiedFoodComponent.getHunger(),
				modifiedFoodComponent.getSaturationModifier());
	}

	public static ItemFoodProperties fromFoodPropertiesTuple(FoodProperties originalFoodProperties,
			FoodProperties modifiedFoodProperties) {
		return new ItemFoodProperties(
				originalFoodProperties.getHunger(),
				originalFoodProperties.getSaturationModifier(),
				modifiedFoodProperties.getHunger(),
				modifiedFoodProperties.getSaturationModifier());
	}

}
