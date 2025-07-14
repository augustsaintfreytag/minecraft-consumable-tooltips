package net.saint.consumable_tooltips.compat;

import java.util.ArrayList;

import de.siphalor.capsaicin.api.food.FoodModifications;
import de.siphalor.capsaicin.api.food.FoodProperties;
import de.siphalor.capsaicin.impl.food.FoodContextImpl;
import de.siphalor.capsaicin.impl.food.FoodHandler;
import de.siphalor.capsaicin.impl.food.properties.FoodPropertiesImpl;
import net.dehydration.Mod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.saint.consumable_tooltips.library.ItemFoodProperties;

public class CapsaicinAccess {

	private static final boolean USE_APPLE_SKIN_LIKE_ACCESS = true;

	public ItemFoodProperties getModifiedFoodPropertiesForStack(ItemStack stack, PlayerEntity player) {
		if (USE_APPLE_SKIN_LIKE_ACCESS) {
			return getPropertiesViaAppleSkinLike(stack, player);
		} else {
			return getPropertiesViaFoodModifications(stack, player);
		}
	}

	private ItemFoodProperties getPropertiesViaFoodModifications(ItemStack stack, PlayerEntity player) {
		var defaultFoodComponent = stack.getItem().getFoodComponent();

		if (defaultFoodComponent == null) {
			return null;
		}

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

	private ItemFoodProperties getPropertiesViaAppleSkinLike(ItemStack stack, PlayerEntity player) {
		var foodHandler = FoodHandler.INSTANCE.get();
		foodHandler.withUser(player).withStack(stack);

		var defaultFoodComponent = foodHandler.getStackOriginalFoodComponent();

		if (defaultFoodComponent == null) {
			return null;
		}

		var modifiedFoodComponent = foodHandler.getModifiedFoodComponent();

		if (modifiedFoodComponent == null) {
			Mod.LOGGER.warn("Could not get modified food component alongside default for stack: '{}'",
					stack.getTranslationKey());
			return ItemFoodProperties.fromDefaultFoodComponent(defaultFoodComponent);
		}

		return ItemFoodProperties.fromFoodComponentTuple(defaultFoodComponent, modifiedFoodComponent);
	}

}
