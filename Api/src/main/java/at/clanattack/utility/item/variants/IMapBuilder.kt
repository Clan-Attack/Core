package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import at.clanattack.utility.item.map.IMapViewBuilder
import org.bukkit.Color
import org.bukkit.map.MapView

@Suppress("unused")
interface IMapBuilder : IItemBuilder<IMapBuilder> {

    val mapView: MapView?
    val mapViewBuilder: IMapViewBuilder
    fun setMapView(mapView: MapView): IMapBuilder
    fun setMapView(mapView: IMapViewBuilder) = setMapView(mapView.build())
    fun modifyMapView(mapView: (IMapViewBuilder) -> Unit): IMapBuilder

    val isScaling: Boolean
    fun setScaling(scaling: Boolean): IMapBuilder

    val hasLocationName: Boolean
    val locationName: String?
    fun setLocationName(name: String): IMapBuilder

    val color: Color?
    fun setColor(color: Color): IMapBuilder

}