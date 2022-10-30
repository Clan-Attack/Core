package at.clanattack.top.utility

import at.clanattack.top.TopCore
import at.clanattack.utility.IUtilityServiceProvider
import net.kyori.adventure.text.Component
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

fun createInventory(inventory: Inventory) =
    TopCore.topCore.getServiceProvider(IUtilityServiceProvider::class).inventoryHandler.createInventory(inventory)

fun createInventory(holder: InventoryHolder?, type: InventoryType) =
    TopCore.topCore.getServiceProvider(IUtilityServiceProvider::class).inventoryHandler.createInventory(holder, type)

fun createInventory(holder: InventoryHolder?, type: InventoryType, title: Component) =
    TopCore.topCore.getServiceProvider(IUtilityServiceProvider::class).inventoryHandler.createInventory(
        holder,
        type,
        title
    )

fun createInventory(holder: InventoryHolder?, rows: Int) =
    TopCore.topCore.getServiceProvider(IUtilityServiceProvider::class).inventoryHandler.createInventory(holder, rows)

fun createInventory(holder: InventoryHolder?, rows: Int, title: Component) =
    TopCore.topCore.getServiceProvider(IUtilityServiceProvider::class).inventoryHandler.createInventory(
        holder,
        rows,
        title
    )

fun createInventory(type: InventoryType) =
    TopCore.topCore.getServiceProvider(IUtilityServiceProvider::class).inventoryHandler.createInventory(type)

fun createInventory(type: InventoryType, title: Component) =
    TopCore.topCore.getServiceProvider(IUtilityServiceProvider::class).inventoryHandler.createInventory(type, title)

fun createInventory(rows: Int) =
    TopCore.topCore.getServiceProvider(IUtilityServiceProvider::class).inventoryHandler.createInventory(rows)

fun createInventory(rows: Int, title: Component) =
    TopCore.topCore.getServiceProvider(IUtilityServiceProvider::class).inventoryHandler.createInventory(rows, title)