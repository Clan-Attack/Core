package at.clanattack.impl.utility.inventory

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.IInventoryBuilder
import org.bukkit.inventory.Inventory

interface IPlainInventoryBuilder : IInventoryBuilder<IPlainInventoryBuilder>

class PlainInventoryBuilder(inventory: Inventory, core: ICore) :
    InventoryBuilder<IPlainInventoryBuilder, Inventory>(inventory, core), IPlainInventoryBuilder {

    override fun checkInventory(inventory: Inventory) = true

    override fun getInstance() = this

}