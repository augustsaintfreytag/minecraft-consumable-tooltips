package net.saint.consumable_tooltips;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.util.Identifier;
import net.saint.consumable_tooltips.compat.CommercializeAccess;
import net.saint.consumable_tooltips.util.TooltipUtil;

public class ClientMod implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
			CommercializeAccess.init();
		});

		ItemTooltipCallback.EVENT.register(Mod.IDENTIFIER, (stack, context, lines) -> {
			TooltipUtil.addTooltip(stack, context, lines);
		});

		var farmersDelightIdentifier = new Identifier("farmersdelight");
		ItemTooltipCallback.EVENT.addPhaseOrdering(farmersDelightIdentifier, Mod.IDENTIFIER);
	}

}
