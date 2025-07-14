package net.saint.consumable_tooltips.util;

public final class CurrencyValueFormattingUtil {

	public static String formattedCurrencyValueWithAccents(double value) {
		var prefix = currencyValueInaccuracyPrefix(value);
		var formattedValue = formattedNumericValue((int) value);

		return prefix + formattedValue;
	}

	private static String currencyValueInaccuracyPrefix(double value) {
		if (value != Math.floor(value)) {
			return "~";
		}

		return "";
	}

	public static String formattedNumericValue(int value) {
		// Format with thousands separator in the form of "2.048".
		return String.format("%,d", value)
				.replace(",", ".")
				.replace(" ", "");
	}

}
