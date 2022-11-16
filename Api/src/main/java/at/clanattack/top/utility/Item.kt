package at.clanattack.top.utility

import at.clanattack.top.TopCore
import at.clanattack.utility.IUtilityServiceProvider
import at.clanattack.utility.item.IItemBuilder
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.inventory.ItemStack
import org.bukkit.map.MapView

fun createItem(stack: ItemStack) =
    TopCore.core.getServiceProvider(IUtilityServiceProvider::class).itemHandler.createItem(stack)

fun createNullableItem(stack: ItemStack?) =
    TopCore.core.getServiceProvider(IUtilityServiceProvider::class).itemHandler.createNullableItem(stack)

fun createItem(builder: IItemBuilder<*>) =
    TopCore.core.getServiceProvider(IUtilityServiceProvider::class).itemHandler.createItem(builder)

fun createItem(material: Material) =
    TopCore.core.getServiceProvider(IUtilityServiceProvider::class).itemHandler.createItem(material)

fun createMapView(mapView: MapView?, world: World? = null) =
    TopCore.core.getServiceProvider(IUtilityServiceProvider::class).itemHandler.createMapView(mapView, world)