package net.saint.consumable_tooltips.compat;

import java.util.HashMap;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.saint.commercialize.Commercialize;
import net.saint.consumable_tooltips.Mod;

public final class CommercializeAccess {

	private static HashMap<Identifier, Integer> cachedValuesByItem = new HashMap<>();

	public static void init() {
		if (!FabricLoader.getInstance().isModLoaded("commercialize")) {
			Mod.LOGGER.info("Commercialize mod is not loaded, skipping compatibility module init.");
			return;
		}

	}

	public static int valueForItem(ItemStack itemStack) {
		var identifier = Registries.ITEM.getId(itemStack.getItem());
		return Commercialize.ITEM_MANAGER.getValueForItem(identifier);
		// return cachedValuesByItem.getOrDefault(itemIdentifier, 0);
	}

	private static void reloadItemValues() {
		// …
	}

}
