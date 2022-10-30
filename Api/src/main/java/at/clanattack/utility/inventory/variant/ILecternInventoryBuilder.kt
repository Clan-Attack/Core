package at.clanattack.utility.inventory.variant

import at.clanattack.utility.inventory.IInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import org.bukkit.block.Lectern
import org.bukkit.inventory.ItemStack

interface ILecternInventoryBuilder : IInventoryBuilder<ILecternInventoryBuilder> {

    val book: IItemBuilder<*>?
    val bookStack: ItemStack?
    fun setBook(item: IItemBuilder<*>): ILecternInventoryBuilder
    fun setBook(item: ItemStack): ILecternInventoryBuilder
    fun clearBook(): ILecternInventoryBuilder

    override val holder: Lectern?


}