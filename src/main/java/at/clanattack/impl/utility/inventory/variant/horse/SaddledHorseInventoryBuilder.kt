package at.clanattack.impl.utility.inventory.variant.horse

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.horse.ISaddledHorseInventoryBuilder
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.SaddledHorseInventory

abstract class SaddledHorseInventoryBuilder
<Builder : ISaddledHorseInventoryBuilder<Builder>, Type : SaddledHorseInventory>(
    inventory: Inventory,
    core: ICore
) : AbstractHorseInventoryBuilder<Builder, Type>(inventory, core), ISaddledHorseInventoryBuilder<Builder>