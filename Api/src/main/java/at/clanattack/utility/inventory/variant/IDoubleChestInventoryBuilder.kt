package at.clanattack.utility.inventory.variant

import at.clanattack.utility.inventory.IInventoryBuilder
import org.bukkit.block.DoubleChest
import org.bukkit.inventory.Inventory

interface IDoubleChestInventoryBuilder : IInventoryBuilder<IDoubleChestInventoryBuilder> {

    val leftSide: IInventoryBuilder<*>
    val leftSideInventory: Inventory

    val rightSide: IInventoryBuilder<*>
    val rightSideInventory: Inventory

    override val holder: DoubleChest?

}