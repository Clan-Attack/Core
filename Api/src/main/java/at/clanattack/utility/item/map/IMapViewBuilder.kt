package at.clanattack.utility.item.map

import org.bukkit.World
import org.bukkit.map.MapRenderer
import org.bukkit.map.MapView
import org.bukkit.map.MapView.Scale

interface IMapViewBuilder {

    val id: Int
    val virtual: Boolean

    val scale: Scale
    fun setScale(scale: Scale): IMapViewBuilder

    val centerX: Int
    val centerZ: Int
    val center: Pair<Int, Int>

    fun setCenterX(centerX: Int): IMapViewBuilder
    fun setCenterZ(centerY: Int): IMapViewBuilder
    fun setCenter(center: Pair<Int, Int>): IMapViewBuilder

    val world: World?
    fun setWorld(world: World): IMapViewBuilder

    val renderers: List<MapRenderer>
    fun addRenderer(renderer: MapRenderer): IMapViewBuilder
    fun removeRenderer(renderer: MapRenderer): IMapViewBuilder

    val isTrackingPosition: Boolean
    fun setTrackingPosition(trackingPosition: Boolean): IMapViewBuilder

    val unlimitedTracking: Boolean
    fun setUnlimitedTracking(unlimitedTracking: Boolean): IMapViewBuilder

    val isLock: Boolean
    fun setLock(lock: Boolean): IMapViewBuilder

    fun build(): MapView

}