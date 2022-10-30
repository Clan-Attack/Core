package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder

interface IRepairableBuilder : IItemBuilder<IRepairableBuilder> {

    val hasRepairCost: Boolean
    val repairCost: Int
    fun setRepairCost(cost: Int): IRepairableBuilder

}