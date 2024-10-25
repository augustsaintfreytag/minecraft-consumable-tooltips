package net.saint.consumable_tooltips.util;

import java.util.ArrayList;

import de.siphalor.capsaicin.api.food.FoodModifications;
import de.siphalor.capsaicin.api.food.FoodProperties;
import de.siphalor.capsaicin.impl.food.FoodContextImpl;
import de.siphalor.capsaicin.impl.food.properties.FoodPropertiesImpl;
import de.siphalor.capsaicin.impl.util.IItem;
import net.dehydration.Mod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import net.saint.consumable_tooltips.library.ItemFoodProperties;

public class CapsaicinAccess {

	private static final boolean USE_APPLE_SKIN_LIKE_ACCESS = true;

	public ItemFoodProperties getModifiedFoodPropertiesForStack(ItemStack stack, PlayerEntity player) {
		var defaultFoodComponent = stack.getItem().getFoodComponent();

		if (defaultFoodComponent == null) {
			return null;
		}

		if (USE_APPLE_SKIN_LIKE_ACCESS) {
			return getPropertiesViaAppleSkinLike(stack, player, defaultFoodComponent);
		} else {
			return getPropertiesViaFoodModifications(stack, player, defaultFoodComponent);
		}
	}

	private ItemFoodProperties getPropertiesViaFoodModifications(ItemStack stack, PlayerEntity player,
			FoodComponent defaultFoodComponent) {
		// Original

		FoodProperties originalFoodProperties = new FoodPropertiesImpl(defaultFoodComponent.getHunger(),
				defaultFoodComponent.getSaturationModifier(), false, new ArrayList<>());

		// Modified

		FoodProperties modifiedFoodProperties = new FoodPropertiesImpl(defaultFoodComponent.getHunger(),
				defaultFoodComponent.getSaturationModifier(), false, new ArrayList<>());
		FoodModifications.PROPERTIES_MODIFIERS.apply(modifiedFoodProperties,
				new FoodContextImpl(stack, null, defaultFoodComponent.getHunger(),
						defaultFoodComponent.getSaturationModifier(), player));

		return ItemFoodProperties.fromFoodPropertiesTuple(originalFoodProperties, modifiedFoodProperties);
	}

	private ItemFoodProperties getPropertiesViaAppleSkinLike(ItemStack stack, PlayerEntity player,
			FoodComponent defaultFoodComponent) {
		var item = stack.getItem();

		if (!(item instanceof IItem iitem)) {
			// Fallback, can not use AppleSkin-like access.
			// return new ItemFoodProperties(originalFoodComponents,
			// originalFoodComponents);
			Mod.LOGGER.warn("Could not read modified food properties (via AppleSkin-like) for item '" + item + "'.");
			return ItemFoodProperties.fromDefaultFoodComponent(defaultFoodComponent);
		}

		var modifiedFoodComponent = iitem.capsaicin$getVanillaFoodComponent();

		if (modifiedFoodComponent == null) {
			// Fallback, used AppleSkin-like access but could not get valid properties.
			// This may mean that the item's properties were not modified.
			Mod.LOGGER.warn("Read modified food properties (via AppleSkin-like) are null for item '" + item + "'.");
			return ItemFoodProperties.fromDefaultFoodComponent(defaultFoodComponent);
		}

		return ItemFoodProperties.fromFoodComponentTuple(defaultFoodComponent, modifiedFoodComponent);
	}

}
