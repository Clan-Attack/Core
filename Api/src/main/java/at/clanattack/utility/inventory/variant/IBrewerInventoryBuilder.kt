package at.clanattack.utility.inventory.variant

import at.clanattack.utility.inventory.IInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import org.bukkit.block.BrewingStand
import org.bukkit.inventory.ItemStack

interface IBrewerInventoryBuilder : IInventoryBuilder<IBrewerInventoryBuilder> {

    val ingredient: IItemBuilder<*>?
    val ingredientStack: ItemStack?
    fun setIngredient(item: IItemBuilder<*>): IBrewerInventoryBuilder
    fun setIngredient(item: ItemStack): IBrewerInventoryBuilder
    fun clearIngredient(): IBrewerInventoryBuilder

    val fuel: IItemBuilder<*>?
    val fuelStack: ItemStack?
    fun setFuel(item: IItemBuilder<*>): IBrewerInventoryBuilder
    fun setFuel(item: ItemStack): IBrewerInventoryBuilder
    fun clearFuel(): IBrewerInventoryBuilder

    override val holder: BrewingStand?

}