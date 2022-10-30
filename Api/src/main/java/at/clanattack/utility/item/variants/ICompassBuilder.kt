package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.Location

interface ICompassBuilder : IItemBuilder<ICompassBuilder> {

    val hasLodestone: Boolean
    val lodestone: Location?
    fun setLodestone(lodestone: Location): ICompassBuilder

    val isLodestoneTracked: Boolean
    fun setLodestoneTracked(tracked: Boolean): ICompassBuilder

}