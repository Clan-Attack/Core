package at.clanattack.impl.utility.item.map

import at.clanattack.utility.item.map.IMapViewBuilder
import at.clanattack.xjkl.scope.withThis
import org.bukkit.World
import org.bukkit.map.MapRenderer
import org.bukkit.map.MapView

class MapViewBuilder(private val mapView: MapView) : IMapViewBuilder {

    override val id: Int
        get() = this.mapView.id

    override val virtual: Boolean
        get() = this.mapView.isVirtual

    override val scale: MapView.Scale
        get() = this.mapView.scale

    override fun setScale(scale: MapView.Scale) = this.withThis { this.mapView.scale = scale }

    override val centerX: Int
        get() = this.mapView.centerX

    override val centerZ: Int
        get() = this.mapView.centerZ

    override val center: Pair<Int, Int>
        get() = Pair(this.centerX, this.centerZ)

    override fun setCenterX(centerX: Int) = this.withThis { this.mapView.centerX = centerX }

    override fun setCenterZ(centerY: Int) = this.withThis { this.mapView.centerZ = centerZ }

    override fun setCenter(center: Pair<Int, Int>) = this.withThis {
        this.setCenterZ(center.first)
        this.setCenterZ(center.second)
    }

    override val world: World?
        get() = this.mapView.world

    override fun setWorld(world: World) = this.withThis { this.mapView.setWorld(world) }

    override val renderers: List<MapRenderer>
        get() = this.mapView.renderers

    override fun addRenderer(renderer: MapRenderer) = this.withThis { this.mapView.addRenderer(renderer) }

    override fun removeRenderer(renderer: MapRenderer) = this.withThis { this.mapView.removeRenderer(renderer) }

    override val isTrackingPosition: Boolean
        get() = this.mapView.isTrackingPosition

    override fun setTrackingPosition(trackingPosition: Boolean) =
        this.withThis { this.mapView.isTrackingPosition = trackingPosition }

    override val unlimitedTracking: Boolean
        get() = this.mapView.isUnlimitedTracking

    override fun setUnlimitedTracking(unlimitedTracking: Boolean) =
        this.withThis { this.mapView.isUnlimitedTracking = unlimitedTracking }

    override val isLock: Boolean
        get() = this.mapView.isLocked

    override fun setLock(lock: Boolean) = this.withThis { this.mapView.isLocked = lock }

    override fun build() = this.mapView

}