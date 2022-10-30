@file:Suppress("unused")
package at.clanattack.utility.item

import at.clanattack.utility.item.map.IMapViewBuilder
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.inventory.ItemStack
import org.bukkit.map.MapView
import org.jetbrains.annotations.Contract

interface IItemHandler {

    fun createItem(stack: ItemStack): IItemBuilder<*>
    
    fun createNullableItem(stack: ItemStack?): IItemBuilder<*>?

    fun createItem(builder: IItemBuilder<*>): IItemBuilder<*>

    fun createItem(material: Material): IItemBuilder<*>

    fun createMapView(mapView: MapView?, world: World? = null): IMapViewBuilder

}

object ItemProvider {

    var instance: IItemHandler? = null

    private val safeInstance: IItemHandler
        get() = instance ?: throw IllegalStateException("Item handling not initialized yet")

    fun createItem(stack: ItemStack) = safeInstance.createItem(stack)

    fun createNullableItem(stack: ItemStack?) = safeInstance.createNullableItem(stack)

    fun createItem(builder: IItemBuilder<*>) = safeInstance.createItem(builder)

    fun createItem(material: Material) = safeInstance.createItem(material)

    fun createMapView(mapView: MapView) = safeInstance.createMapView(mapView)

}

fun createItem(stack: ItemStack) = ItemProvider.createItem(stack)

fun createNullableItem(stack: ItemStack?) = ItemProvider.createNullableItem(stack)

fun createItem(builder: IItemBuilder<*>) = ItemProvider.createItem(builder)

fun createItem(material: Material) = ItemProvider.createItem(material)

fun createMapView(mapView: MapView) = ItemProvider.createMapView(mapView)