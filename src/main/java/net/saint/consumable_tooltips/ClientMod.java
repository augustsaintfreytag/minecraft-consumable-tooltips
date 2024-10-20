package net.saint.consumable_tooltips;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.saint.consumable_tooltips.util.TooltipUtil;

public class ClientMod implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
			TooltipUtil.addTooltip(stack, context, lines);
		});
	}

}
