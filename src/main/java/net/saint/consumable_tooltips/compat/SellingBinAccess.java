package net.saint.consumable_tooltips.compat;

import java.util.HashMap;

import bigchadguys.sellingbin.init.ModConfigs;
import net.dehydration.Mod;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

public class SellingBinAccess {

	private static HashMap<String, Integer> currencyValueByItem = new HashMap<>() {
		{
			this.put("numismatics:spur", 1);
			this.put("numismatics:bevel", 8);
			this.put("numismatics:sprocket", 16);
			this.put("numismatics:cog", 64);
			this.put("numismatics:crown", 8 * 64);
			this.put("numismatics:sun", 64 * 8 * 64);
		}
	};

	private static HashMap<String, Double> cachedTradeValuesByItem = new HashMap<>();

	// Init

	public static void init() {
		if (!FabricLoader.getInstance().isModLoaded("sellingbin")) {
			Mod.LOGGER.info("Selling Bin mod is not loaded, skipping compatibility module init.");
			return;
		}

		reloadTradeTable();
	}

	public static void reloadTradeTable() {
		cachedTradeValuesByItem = tradeValuesByItem();
	}

	// Access

	public static double tradeValueForItem(ItemStack itemStack) {
		var itemIdentifier = Registries.ITEM.getId(itemStack.getItem()).toString();
		var isCurrencyItem = currencyValueByItem.containsKey(itemIdentifier);

		if (isCurrencyItem) {
			return 0.0;
		}

		return cachedTradeValuesByItem.getOrDefault(itemIdentifier, 0.0);
	}

	public static HashMap<String, Double> tradeValuesByItem() {
		var values = new HashMap<String, Double>();

		for (var trade : ModConfigs.SELLING_BIN.getTrades()) {
			var input = trade.getInput();
			var output = trade.getOutput();

			var inputItemIdentifier = input.getFilter().toString();
			var inputItemCount = input.getCount();
			var outputItemIdentifier = output.getItem().toString();
			var outputItemCount = output.getCount();

			var itemValue = (double) tradeValueForOutput(outputItemIdentifier, outputItemCount)
					/ (double) inputItemCount;

			if (itemValue != 0) {
				cachedTradeValuesByItem.put(inputItemIdentifier, itemValue);
			}

			values.put(inputItemIdentifier, itemValue);
		}

		return values;
	}

	private static int tradeValueForOutput(String identifier, int count) {
		var currencyValue = currencyValueByItem.getOrDefault(identifier, 0);
		var tradeValue = currencyValue * count;

		return tradeValue;
	}

}
