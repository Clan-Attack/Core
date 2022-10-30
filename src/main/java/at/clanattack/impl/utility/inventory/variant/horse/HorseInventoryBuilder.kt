package at.clanattack.impl.utility.inventory.variant.horse

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.horse.IHorseInventoryBuilder
import org.bukkit.inventory.HorseInventory
import org.bukkit.inventory.Inventory

class HorseInventoryBuilder(inventory: Inventory, core: ICore) :
    ArmoredHorseInventoryBuilder<IHorseInventoryBuilder, HorseInventory>(inventory, core), IHorseInventoryBuilder {

    override fun checkInventory(inventory: Inventory) = inventory is HorseInventory

    override fun getInstance() = this

}
