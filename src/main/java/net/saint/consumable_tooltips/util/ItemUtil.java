package net.saint.consumable_tooltips.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.saint.consumable_tooltips.Mod;
import net.saint.consumable_tooltips.library.ItemHungerValues;
import net.saint.consumable_tooltips.library.ItemHydrationValues;
import net.saint.consumable_tooltips.library.ItemProperties;

public class ItemUtil {

	// Item Properties

	public static ItemProperties itemPropertiesForItem(PlayerEntity player, ItemStack stack) {
		var item = stack.getItem();

		if (item instanceof BlockItem) {
			return null;
		}

		var identifier = Registries.ITEM.getId(item);
		var hungerValues = hungerValuesForStack(stack, player);
		var hydrationValues = hydrationValueForStack(stack, player);

		return new ItemProperties(identifier, 0, hungerValues.nutrition, hungerValues.saturation,
				hungerValues.defaultNutrition,
				hungerValues.defaultSaturation, hydrationValues.hydration, hydrationValues.isContaminated);
	}

	private static ItemHungerValues hungerValuesForStack(ItemStack stack, PlayerEntity player) {
		var foodProperties = Mod.CAPSAICIN_ACCESS.getModifiedFoodPropertiesForStack(stack, player);

		if (foodProperties == null) {
			return new ItemHungerValues(0, 0, 0, 0);
		}

		var originalFoodProperties = foodProperties.originalFoodProperties;
		var modifiedFoodProperties = foodProperties.modifiedFoodProperties;

		var nutrition = modifiedFoodProperties.getHunger();
		var saturation = saturationValueFromHungerAndModifier(nutrition,
				modifiedFoodProperties.getSaturationModifier());

		var originalNutrition = originalFoodProperties.getHunger();
		var originalSaturation = saturationValueFromHungerAndModifier(originalNutrition,
				originalFoodProperties.getSaturationModifier());

		return new ItemHungerValues(nutrition, saturation, originalNutrition, originalSaturation);
	}

	private static ItemHydrationValues hydrationValueForStack(ItemStack stack, PlayerEntity player) {
		var hydrationValue = Mod.DEHYDRATION_ACCESS.getHydrationForItemStack(stack, player);
		var isContaminated = Mod.DEHYDRATION_ACCESS.getIsItemStackContaminated(stack);

		return new ItemHydrationValues(hydrationValue, isContaminated);
	}

	private static int saturationValueFromHungerAndModifier(int hungerValue, float saturationModifier) {
		return (int) (hungerValue * saturationModifier * 2f);
	}

}
