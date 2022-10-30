package at.clanattack.utility.inventory.variant

import at.clanattack.utility.inventory.IInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import org.bukkit.inventory.ItemStack

interface IEnchantingInventoryBuilder : IInventoryBuilder<IEnchantingInventoryBuilder> {

    val enchant: IItemBuilder<*>?
    val enchantStack: ItemStack?
    fun setEnchant(item: IItemBuilder<*>): IEnchantingInventoryBuilder
    fun setEnchant(item: ItemStack): IEnchantingInventoryBuilder
    fun clearEnchant(): IEnchantingInventoryBuilder

    val secondary: IItemBuilder<*>?
    val secondaryStack: ItemStack?
    fun setSecondary(item: IItemBuilder<*>): IEnchantingInventoryBuilder
    fun setSecondary(item: ItemStack): IEnchantingInventoryBuilder
    fun clearSecondary(): IEnchantingInventoryBuilder

}