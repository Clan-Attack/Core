package at.clanattack.utility.inventory.variant.horse

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.inventory.ItemStack

interface ILlamaInventoryBuilder : ISaddledHorseInventoryBuilder<ILlamaInventoryBuilder> {

    val decor: IItemBuilder<*>?
    val decorStack: ItemStack?
    fun setDecor(item: IItemBuilder<*>): ILlamaInventoryBuilder
    fun setDecor(item: ItemStack): ILlamaInventoryBuilder
    fun clearDecor(): ILlamaInventoryBuilder

}