package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.IUtilityServiceProvider
import at.clanattack.utility.item.map.IMapViewBuilder
import at.clanattack.utility.item.variants.IMapBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.Color
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.MapMeta
import org.bukkit.map.MapView

class MapBuilder(itemStack: ItemStack, core: ICore) : ItemBuilder<IMapBuilder, MapMeta>(itemStack, core), IMapBuilder {

    override val mapView: MapView?
        get() = this.getMeta().mapView

    override val mapViewBuilder: IMapViewBuilder
        get() = this.core.getServiceProvider(IUtilityServiceProvider::class).itemHandler.createMapView(mapView)

    override fun setMapView(mapView: MapView) = this.modifyMeta { it.mapView = mapView }

    override fun modifyMapView(mapView: (IMapViewBuilder) -> Unit) = this.modifyMeta {
        val mapViewBuilder = this.mapViewBuilder
        mapView(mapViewBuilder)
        it.mapView = mapViewBuilder.build()
    }

    override val isScaling: Boolean
        get() = this.getMeta().isScaling

    override fun setScaling(scaling: Boolean) = this.modifyMeta { it.isScaling = scaling }

    override val hasLocationName: Boolean
        get() = this.getMeta().hasLocationName()

    override val locationName: String?
        get() = this.getMeta().locationName

    override fun setLocationName(name: String) = this.modifyMeta { it.locationName = name }

    override val color: Color?
        get() = this.getMeta().color

    override fun setColor(color: Color) = this.modifyMeta { it.color = color }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is MapMeta

    override fun getInstance() = this

}