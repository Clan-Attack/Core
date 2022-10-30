package at.clanattack.utility.inventory.variant

import at.clanattack.utility.inventory.IInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe

interface ICraftingInventoryBuilder : IInventoryBuilder<ICraftingInventoryBuilder> {

    val result: IItemBuilder<*>?
    val resultStack: ItemStack?
    fun setResult(item: IItemBuilder<*>): ICraftingInventoryBuilder
    fun setResult(item: ItemStack): ICraftingInventoryBuilder
    fun clearResult(): ICraftingInventoryBuilder

    val matrix: Array<IItemBuilder<*>?>
    val matrixStack: Array<ItemStack?>
    fun setMatrix(vararg item: IItemBuilder<*>?): ICraftingInventoryBuilder
    fun setMatrix(vararg item: ItemStack?): ICraftingInventoryBuilder
    fun clearMatrix(): ICraftingInventoryBuilder

    val recipe: Recipe?

}