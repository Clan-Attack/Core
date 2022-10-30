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

object InventoryProvider {

    var instance: IInventoryHandler? = null

    private val safeInstance: IInventoryHandler
        get() = instance ?: throw IllegalStateException("Inventory handling not initialized yet")

    fun createInventory(inventory: Inventory) = safeInstance.createInventory(inventory)
    fun createInventory(holder: InventoryHolder?, type: InventoryType) = safeInstance.createInventory(holder, type)
    fun createInventory(holder: InventoryHolder?, type: InventoryType, title: Component) =
        safeInstance.createInventory(holder, type, title)

    fun createInventory(holder: InventoryHolder?, rows: Int) = safeInstance.createInventory(holder, rows)
    fun createInventory(holder: InventoryHolder?, rows: Int, title: Component) =
        safeInstance.createInventory(holder, rows, title)

    fun createInventory(type: InventoryType) = safeInstance.createInventory(type)
    fun createInventory(type: InventoryType, title: Component) = safeInstance.createInventory(type, title)
    fun createInventory(rows: Int) = safeInstance.createInventory(rows)
    fun createInventory(rows: Int, title: Component) = safeInstance.createInventory(rows, title)

}


fun createInventory(inventory: Inventory) = InventoryProvider.createInventory(inventory)
fun createInventory(holder: InventoryHolder?, type: InventoryType) = InventoryProvider.createInventory(holder, type)
fun createInventory(holder: InventoryHolder?, type: InventoryType, title: Component) =
    InventoryProvider.createInventory(holder, type, title)

fun createInventory(holder: InventoryHolder?, rows: Int) = InventoryProvider.createInventory(holder, rows)
fun createInventory(holder: InventoryHolder?, rows: Int, title: Component) =
    InventoryProvider.createInventory(holder, rows, title)

fun createInventory(type: InventoryType) = InventoryProvider.createInventory(type)
fun createInventory(type: InventoryType, title: Component) = InventoryProvider.createInventory(type, title)
fun createInventory(rows: Int) = InventoryProvider.createInventory(rows)
fun createInventory(rows: Int, title: Component) = InventoryProvider.createInventory(rows, title)