package at.clanattack.impl.utility.inventory.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.ILoomInventoryBuilder
import at.clanattack.impl.utility.inventory.InventoryBuilder
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.LoomInventory

class LoomInventoryBuilder(inventory: Inventory, core: ICore) :
    InventoryBuilder<ILoomInventoryBuilder, LoomInventory>(inventory, core), ILoomInventoryBuilder {

    override fun checkInventory(inventory: Inventory) = inventory is LoomInventory

    override fun getInstance() = this

}