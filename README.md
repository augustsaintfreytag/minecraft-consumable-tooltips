*(Artwork pending)*

# Consumable Tooltips

A mod for Minecraft that introduces a unified tooltip for consumable items, showing hunger, saturation, hydration, and supported special status effects. Created with the goal to show consistently styled attributes of items that may come from or be modified by different mods. Primarily intended for *Spice of Fabric* and my opinionated fork of *Dehydration* (soon to be renamed and prepated for publication). Inspired by the tooltip design of the *Spice of Valheim* mod.

## Features

The mod introduces the following features:

- Create an aesthetically pleasing, clean, and informative tooltip for consumables
- Values correspond to the exact number of 🍗 or 💧 it restores in the HUD
- Display hunger and saturation values (like *AppleSkin*)
- Display hydration value (like *Dehydration*)
- Support for *Spice of Fabric* and dynamic food values (e.g. diminishing nutrition)
- Supports displaying an item's current player-specific value vs. its default values

It is highly recommended to disable the tooltip rendering of *AppleSkin* and *Dehydration* to remove duplicate information and let *Consumable Tooltips* handle all of it.

## Preview

The default tooltip appearance for an item that gives hunger, saturation, and hydration.

![Screenshot 2024-10-19 163026](https://github.com/user-attachments/assets/68c28815-ff6b-49b5-bbf5-f13749f36f50)

The tooltip for an item that has been consumed before and now has diminished nutritional value (via *Spice of Fabric*).

![Screenshot 2024-10-20 002658](https://github.com/user-attachments/assets/14c9026d-c50b-419b-88be-d2db1f57bc18)

The tooltip for a potion inside *EMI* that has no hunger or saturation but a hydration value.

![Screenshot 2024-10-20 011200 - Copy](https://github.com/user-attachments/assets/17c2fbbe-668c-4921-b5d9-b977f16b5d54)

## Development

The project depends on having a local version of my Dehydration fork that is loaded on configure. If you want to set up the project locally, you may have to also check out my fork of *Dehydration* and place the compiled *jar* file in `./libs/`, for example as `./libs/dehydration-1.4.1+forked.jar`. Update the include path in `build.gradle` as necessary.

## License

This mod was created by Saint for free use by the Minecraft community under the MIT license. It may be shared, modified, or redistributed as part of mod packs with basic attribution.
