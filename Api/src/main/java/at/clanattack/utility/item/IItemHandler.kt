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
