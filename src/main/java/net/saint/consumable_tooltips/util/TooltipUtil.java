package net.saint.consumable_tooltips.util;

import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.saint.consumable_tooltips.Mod;
import net.saint.consumable_tooltips.library.ItemProperties;

public class TooltipUtil {

	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static void addTooltip(ItemStack stack, TooltipContext context, List<Text> lines) {
		assertTooltipLineOrder(lines);

		var itemProperties = itemPropertiesForItem(stack);

		if (itemProperties == null) {
			return;
		}

		if (Mod.CONFIG.enableHealthTooltip) {
			lines.addAll(createHealthTooltip(itemProperties));
		}

		if (Mod.CONFIG.enableSatietyTooltip) {
			lines.addAll(createNutritionTooltip(itemProperties));
		}

		if (Mod.CONFIG.enableSaturationTooltip) {
			lines.addAll(createSaturationTooltip(itemProperties));
		}

		if (Mod.CONFIG.enableHydrationTooltip) {
			lines.addAll(createHydrationTooltip(itemProperties));
			lines.addAll(createContaminationTooltip(itemProperties));
		}

		if (Mod.CONFIG.enableCurrencyValueTooltip) {
			lines.addAll(createCurrencyValueTooltip(itemProperties));
		}
	}

	private static void assertTooltipLineOrder(List<Text> lines) {
		if (lines.size() < 3) {
			return;
		}

		var modNameLine = extractTooltipModNameLine(lines);

		if (modNameLine == null) {
			return;
		}

		lines.remove(modNameLine);
		lines.add(1, modNameLine);
	}

	private static Text extractTooltipModNameLine(List<Text> lines) {
		var modNameLineIndex = indexOfTooltipModNameLine(lines);

		if (modNameLineIndex == -1) {
			return null;
		}

		var modNameLine = lines.get(modNameLineIndex);
		lines.remove(modNameLineIndex);

		return modNameLine;
	}

	private static int indexOfTooltipModNameLine(List<Text> lines) {
		for (int index = 1; index < lines.size(); index++) {
			var line = lines.get(index);

			if (line.getStyle().isItalic() || line.getString().contains("§9§o")) {
				return index;
			}
		}

		return -1;
	}

	private static List<Text> createHealthTooltip(ItemProperties itemProperties) {
		var health = itemProperties.health;
		var healthComponent = String.format("%.1f", health / 2f);

		if (health == 0) {
			return List.of();
		}

		return Text.literal("❤ " + healthComponent + " Heart" + (health / 2f > 1 ? "s" : ""))
				.getWithStyle(Style.EMPTY.withColor(Formatting.RED));
	}

	private static List<Text> createNutritionTooltip(ItemProperties itemProperties) {
		var nutrition = itemProperties.nutrition;
		var defaultNutrition = itemProperties.defaultNutrition;

		if (defaultNutrition == 0) {
			return List.of();
		}

		var nutritionComponent = String.format("%.1f", nutrition / 2f);
		var defaultNutritionComponent = String.format("%.1f", defaultNutrition / 2f);
		var nutritionSuffix = nutrition < defaultNutrition ? " (⏷" + nutritionComponent + ")" : "";

		return Text.literal("🍖 " + defaultNutritionComponent + " Hunger" + nutritionSuffix)
				.getWithStyle(Style.EMPTY.withColor(Formatting.RED));
	}

	private static List<Text> createSaturationTooltip(ItemProperties itemProperties) {
		var saturation = itemProperties.saturation;
		var defaultSaturation = itemProperties.defaultSaturation;

		if (defaultSaturation == 0) {
			return List.of();
		}

		var saturationComponent = String.format("%.1f", saturation / 2f);
		var defaultSaturationComponent = String.format("%.1f", defaultSaturation / 2f);
		var saturationSuffix = saturation < defaultSaturation ? " (⏷" + saturationComponent + ")" : "";

		return Text.literal("☀ " + defaultSaturationComponent + " Saturation" + saturationSuffix)
				.getWithStyle(Style.EMPTY.withColor(Formatting.GOLD));
	}

	private static List<Text> createHydrationTooltip(ItemProperties itemProperties) {
		var hydration = itemProperties.hydration;

		if (hydration == 0) {
			return List.of();
		}

		var hydrationComponent = String.format("%.1f", hydration / 2f);
		return Text.literal("❄ " + hydrationComponent + " Hydration")
				.getWithStyle(Style.EMPTY.withColor(Formatting.AQUA));
	}

	private static List<Text> createCurrencyValueTooltip(ItemProperties itemProperties) {
		var value = itemProperties.value;

		if (value == 0) {
			return List.of();
		}

		var formattedValue = CurrencyFormattingUtil.formattedCurrencyValueWithAccents(value);
		return Text.literal("¤ " + formattedValue + " Spurs")
				.getWithStyle(Style.EMPTY.withColor(Formatting.GREEN));
	}

	private static List<Text> createContaminationTooltip(ItemProperties itemProperties) {
		if (!itemProperties.isContaminated) {
			return List.of();
		}

		return Text.literal("☠ Contaminated")
				.getWithStyle(Style.EMPTY.withColor(Formatting.LIGHT_PURPLE));
	}

	private static ItemProperties itemPropertiesForItem(ItemStack stack) {
		var player = client.player;

		if (player == null) {
			return null;
		}

		return ItemUtil.itemPropertiesForItem(player, stack);
	}
}
