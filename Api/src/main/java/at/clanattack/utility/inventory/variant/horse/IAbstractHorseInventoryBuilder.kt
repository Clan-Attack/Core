package at.clanattack.utility.inventory.variant.horse

import at.clanattack.utility.inventory.IInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import org.bukkit.inventory.ItemStack

interface IAbstractHorseInventoryBuilder<Builder : IAbstractHorseInventoryBuilder<Builder>> : IInventoryBuilder<Builder> {

    val saddle: IItemBuilder<*>?
    val saddleStack: ItemStack?
    fun setSaddle(item: IItemBuilder<*>): Builder
    fun setSaddle(item: ItemStack): Builder
    fun clearSaddle(): Builder

}