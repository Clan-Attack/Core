package at.clanattack.impl.utility.inventory.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.IInventoryBuilder
import at.clanattack.utility.inventory.variant.IDoubleChestInventoryBuilder
import at.clanattack.impl.utility.inventory.InventoryBuilder
import org.bukkit.block.DoubleChest
import org.bukkit.inventory.DoubleChestInventory
import org.bukkit.inventory.Inventory

class DoubleChestInventoryBuilder(inventory: Inventory, core: ICore) :
    InventoryBuilder<IDoubleChestInventoryBuilder, DoubleChestInventory>(inventory, core),
    IDoubleChestInventoryBuilder {

    override fun checkInventory(inventory: Inventory) = inventory is DoubleChestInventory

    override fun getInstance() = this

    override val leftSide: IInventoryBuilder<*>
        get() = createInventory(this.leftSideInventory)

    override val leftSideInventory: Inventory
        get() = this.getInventory().leftSide

    override val rightSide: IInventoryBuilder<*>
        get() = createInventory(this.rightSideInventory)

    override val rightSideInventory: Inventory
        get() = this.getInventory().rightSide

    override val holder: DoubleChest?
        get() = this.getInstance().holder

}