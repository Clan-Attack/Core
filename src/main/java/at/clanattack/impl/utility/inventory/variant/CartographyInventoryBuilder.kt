package at.clanattack.impl.utility.inventory.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.ICartographyInventoryBuilder
import at.clanattack.impl.utility.inventory.InventoryBuilder
import org.bukkit.inventory.CartographyInventory
import org.bukkit.inventory.Inventory

class CartographyInventoryBuilder(inventory: Inventory, core: ICore) :
    InventoryBuilder<ICartographyInventoryBuilder, CartographyInventory>(inventory, core),
    ICartographyInventoryBuilder {

    override fun checkInventory(inventory: Inventory) = inventory is CartographyInventory

    override fun getInstance() = this

}