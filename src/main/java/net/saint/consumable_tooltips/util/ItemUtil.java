package net.saint.consumable_tooltips.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.saint.consumable_tooltips.Mod;
import net.saint.consumable_tooltips.compat.CommercializeAccess;
import net.saint.consumable_tooltips.library.ItemHungerValues;
import net.saint.consumable_tooltips.library.ItemHydrationValues;
import net.saint.consumable_tooltips.library.ItemProperties;

public class ItemUtil {

	// Item Properties

	public static ItemProperties itemPropertiesForItem(PlayerEntity player, ItemStack stack) {
		var item = stack.getItem();

		var identifier = Registries.ITEM.getId(item);
		var value = CommercializeAccess.valueForItem(stack);
		var hungerValues = getHungerValuesForStack(stack, player);
		var hydrationValues = getHydrationValueForStack(stack, player);

		var properties = new ItemProperties();

		properties.id = identifier;
		properties.value = value;
		properties.health = 0;
		properties.nutrition = hungerValues.nutrition;
		properties.saturation = hungerValues.saturation;
		properties.defaultNutrition = hungerValues.defaultNutrition;
		properties.defaultSaturation = hungerValues.defaultSaturation;
		properties.hydration = hydrationValues.hydration;
		properties.isContaminated = hydrationValues.isContaminated;

		return properties;
	}

	private static ItemHungerValues getHungerValuesForStack(ItemStack stack, PlayerEntity player) {
		var foodProperties = Mod.CAPSAICIN_ACCESS.getModifiedFoodPropertiesForStack(stack, player);

		if (foodProperties == null) {
			return new ItemHungerValues(0, 0, 0, 0);
		}

		var currentNutrition = foodProperties.modifiedHunger;
		var currentSaturation = getSaturationValueFromHungerAndModifier(currentNutrition,
				foodProperties.modifiedSaturationModifier);

		var defaultNutrition = foodProperties.defaultHunger;
		var defaultSaturation = getSaturationValueFromHungerAndModifier(defaultNutrition,
				foodProperties.defaultSaturationModifier);

		return new ItemHungerValues(currentNutrition, currentSaturation, defaultNutrition, defaultSaturation);
	}

	private static ItemHydrationValues getHydrationValueForStack(ItemStack stack, PlayerEntity player) {
		var hydrationValue = Mod.DEHYDRATION_ACCESS.getHydrationForItemStack(stack, player);
		var isContaminated = Mod.DEHYDRATION_ACCESS.getIsItemStackContaminated(stack);

		return new ItemHydrationValues(hydrationValue, isContaminated);
	}

	private static int getSaturationValueFromHungerAndModifier(int hungerValue, float saturationModifier) {
		return (int) (hungerValue * saturationModifier * 2f);
	}

}
