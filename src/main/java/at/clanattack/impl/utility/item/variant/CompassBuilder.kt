package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.ICompassBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.Location
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.CompassMeta
import org.bukkit.inventory.meta.ItemMeta

class CompassBuilder(itemStack: ItemStack, core: ICore) : ItemBuilder<ICompassBuilder, CompassMeta>(itemStack, core),
    ICompassBuilder {

    override val hasLodestone: Boolean
        get() = this.getMeta().hasLodestone()

    override val lodestone: Location?
        get() = this.getMeta().lodestone

    override fun setLodestone(lodestone: Location) = this.modifyMeta { it.lodestone = lodestone }

    override val isLodestoneTracked: Boolean
        get() = this.getMeta().isLodestoneTracked

    override fun setLodestoneTracked(tracked: Boolean) = this.modifyMeta { it.isLodestoneTracked = tracked }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is CompassMeta

    override fun getInstance() = this

}