package at.clanattack.impl.utility.item.variant

import com.destroystokyo.paper.inventory.meta.ArmorStandMeta
import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.IArmorStandBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class ArmorStandBuilder(itemStack: ItemStack, core: ICore) :
    ItemBuilder<IArmorStandBuilder, ArmorStandMeta>(itemStack, core), IArmorStandBuilder {

    override val invisible: Boolean
        get() = this.getMeta().isInvisible

    override fun setInvisible(invisible: Boolean) = this.modifyMeta { it.isInvisible = invisible }

    override val noBasePlate: Boolean
        get() = this.getMeta().hasNoBasePlate()

    override fun setNoBasePlate(noBasePlate: Boolean) = this.modifyMeta { it.setNoBasePlate(noBasePlate) }

    override val showArms: Boolean
        get() = this.getMeta().shouldShowArms()

    override fun setShowArms(showArms: Boolean) = this.modifyMeta { it.setShowArms(showArms) }

    override val small: Boolean
        get() = this.getMeta().isSmall

    override fun setSmall(small: Boolean) = this.modifyMeta { it.isSmall = small }

    override val marker: Boolean
        get() = this.getMeta().isMarker

    override fun setMarker(marker: Boolean) = this.modifyMeta { it.isMarker = marker }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is ArmorStandMeta

    override fun getInstance() = this

}