package net.saint.consumable_tooltips;

import static net.minecraft.server.command.CommandManager.literal;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.saint.consumable_tooltips.compat.SellingBinAccess;

public final class ModCommands {

	public static void registerCommands() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess,
				environment) -> dispatcher.register(literal("consumable-tooltips")
						.then(literal("reload").requires(source -> source.hasPermissionLevel(4))
								.executes(context -> {
									SellingBinAccess.reloadTradeTable();

									return 1;
								}))));

	}

}
