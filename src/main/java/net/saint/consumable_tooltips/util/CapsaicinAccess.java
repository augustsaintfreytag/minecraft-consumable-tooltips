package net.saint.consumable_tooltips.util;

import java.util.ArrayList;

import de.siphalor.capsaicin.api.food.FoodModifications;
import de.siphalor.capsaicin.api.food.FoodProperties;
import de.siphalor.capsaicin.impl.food.FoodContextImpl;
import de.siphalor.capsaicin.impl.food.properties.FoodPropertiesImpl;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.saint.consumable_tooltips.library.ItemFoodProperties;

public class CapsaicinAccess {

	public ItemFoodProperties getModifiedFoodPropertiesForStack(ItemStack stack, PlayerEntity player) {
		var originalFoodComponents = stack.getItem().getFoodComponent();

		if (originalFoodComponents == null) {
			return null;
		}

		// Original

		FoodProperties originalFoodProperties = new FoodPropertiesImpl(originalFoodComponents.getHunger(),
				originalFoodComponents.getSaturationModifier(), false, new ArrayList<>());

		// Modified

		FoodProperties modifiedFoodProperties = new FoodPropertiesImpl(originalFoodComponents.getHunger(),
				originalFoodComponents.getSaturationModifier(), false, new ArrayList<>());
		FoodModifications.PROPERTIES_MODIFIERS.apply(modifiedFoodProperties,
				new FoodContextImpl(stack, null, originalFoodComponents.getHunger(),
						originalFoodComponents.getSaturationModifier(), player));

		return new ItemFoodProperties(originalFoodProperties, modifiedFoodProperties);
	}

}
