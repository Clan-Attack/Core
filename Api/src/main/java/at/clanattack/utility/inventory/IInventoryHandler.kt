package at.clanattack.utility.inventory

import net.kyori.adventure.text.Component
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

interface IInventoryHandler {

    fun createInventory(inventory: Inventory): IInventoryBuilder<*>
    fun createInventory(holder: InventoryHolder?, type: InventoryType): IInventoryBuilder<*>
    fun createInventory(holder: InventoryHolder?, type: InventoryType, title: Component): IInventoryBuilder<*>
    fun createInventory(holder: InventoryHolder?, rows: Int): IInventoryBuilder<*>
    fun createInventory(holder: InventoryHolder?, rows: Int, title: Component): IInventoryBuilder<*>

    fun createInventory(type: InventoryType) = createInventory(null, type)
    fun createInventory(type: InventoryType, title: Component) = createInventory(null, type, title)
    fun createInventory(rows: Int) = createInventory(null, rows)
    fun createInventory(rows: Int, title: Component) = createInventory(null, rows, title)

}
