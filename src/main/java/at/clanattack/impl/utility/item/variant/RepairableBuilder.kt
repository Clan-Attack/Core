package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.IRepairableBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.Repairable

class RepairableBuilder(itemStack: ItemStack, core: ICore) :
    ItemBuilder<IRepairableBuilder, Repairable>(itemStack, core), IRepairableBuilder {

    override val hasRepairCost: Boolean
        get() = this.getMeta().hasRepairCost()

    override val repairCost: Int
        get() = this.getMeta().repairCost

    override fun setRepairCost(cost: Int) = this.modifyMeta { it.repairCost = cost }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is Repairable

    override fun getInstance() = this

}