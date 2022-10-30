package at.clanattack.utility.inventory.variant

import at.clanattack.utility.inventory.IInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe

interface ISmithingInventoryBuilder : IInventoryBuilder<ISmithingInventoryBuilder> {

    val result: IItemBuilder<*>?
    val resultStack: ItemStack?
    fun setResult(item: IItemBuilder<*>): ISmithingInventoryBuilder
    fun setResult(item: ItemStack): ISmithingInventoryBuilder
    fun clearResult(): ISmithingInventoryBuilder

    val inputEquipment: IItemBuilder<*>?
    val inputEquipmentStack: ItemStack?
    fun setInputEquipment(item: IItemBuilder<*>): ISmithingInventoryBuilder
    fun setInputEquipment(item: ItemStack): ISmithingInventoryBuilder
    fun clearInputEquipment(): ISmithingInventoryBuilder

    val recipe: Recipe?

}