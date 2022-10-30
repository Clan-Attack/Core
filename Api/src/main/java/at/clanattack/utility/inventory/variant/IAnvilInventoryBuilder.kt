package at.clanattack.utility.inventory.variant

import at.clanattack.utility.inventory.IInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import org.bukkit.inventory.ItemStack

interface IAnvilInventoryBuilder : IInventoryBuilder<IAnvilInventoryBuilder> {

    val renameText: String?

    val repairCostAmount: Int
    fun setRepairCostAmount(repairCostAmount: Int): IAnvilInventoryBuilder

    val repairCost: Int
    fun setRepairCost(repairCost: Int): IAnvilInventoryBuilder

    val maximumRepairCost: Int
    fun setMaximumRepairCost(maximumRepairCost: Int): IAnvilInventoryBuilder

    val leftInput: IItemBuilder<*>?
    val leftInputStack: ItemStack?
    fun setLeftItem(item: IItemBuilder<*>): IAnvilInventoryBuilder
    fun setLeftItem(item: ItemStack): IAnvilInventoryBuilder
    fun clearLeftItem(): IAnvilInventoryBuilder

    val rightInput: IItemBuilder<*>?
    val rightInputStack: ItemStack?
    fun setRightInput(item: IItemBuilder<*>): IAnvilInventoryBuilder
    fun setRightInput(item: ItemStack): IAnvilInventoryBuilder
    fun clearRightInput(): IAnvilInventoryBuilder

    val result: IItemBuilder<*>?
    val resultStack: ItemStack?
    fun setResult(item: IItemBuilder<*>): IAnvilInventoryBuilder
    fun setResult(item: ItemStack): IAnvilInventoryBuilder
    fun clearResult(): IAnvilInventoryBuilder

}