package at.clanattack.utility.inventory.variant

import at.clanattack.utility.inventory.IInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import org.bukkit.block.Furnace
import org.bukkit.inventory.ItemStack

interface IFurnaceInventoryBuilder : IInventoryBuilder<IFurnaceInventoryBuilder> {

    val smelting: IItemBuilder<*>?
    val smeltingStack: ItemStack?
    fun setSmelting(item: IItemBuilder<*>): IFurnaceInventoryBuilder
    fun setSmelting(item: ItemStack): IFurnaceInventoryBuilder
    fun clearSmelting(): IFurnaceInventoryBuilder

    val fuel: IItemBuilder<*>?
    val fuelStack: ItemStack?
    fun setFuel(item: IItemBuilder<*>): IFurnaceInventoryBuilder
    fun setFuel(item: ItemStack): IFurnaceInventoryBuilder
    fun clearFuel(): IFurnaceInventoryBuilder

    val result: IItemBuilder<*>?
    val resultStack: ItemStack?
    fun setResult(item: IItemBuilder<*>): IFurnaceInventoryBuilder
    fun setResult(item: ItemStack): IFurnaceInventoryBuilder
    fun clearResult(): IFurnaceInventoryBuilder

    fun isFuel(item: IItemBuilder<*>): Boolean
    fun isFuel(item: ItemStack): Boolean

    fun canSmelt(item: IItemBuilder<*>): Boolean
    fun canSmelt(item: ItemStack): Boolean

    override val holder: Furnace?

}