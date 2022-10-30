package at.clanattack.impl.utility.inventory.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.IAnvilInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import at.clanattack.utility.item.createNullableItem
import at.clanattack.impl.utility.inventory.InventoryBuilder
import org.bukkit.inventory.AnvilInventory
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class AnvilInventoryBuilder(inventory: Inventory, core: ICore) :
    InventoryBuilder<IAnvilInventoryBuilder, AnvilInventory>(inventory, core), IAnvilInventoryBuilder {

    override fun checkInventory(inventory: Inventory) = inventory is AnvilInventory

    override fun getInstance() = this

    override val renameText: String?
        get() = this.getInventory().renameText

    override val repairCostAmount: Int
        get() = this.getInventory().repairCostAmount

    override fun setRepairCostAmount(repairCostAmount: Int) =
        this.modifyInventory { it.repairCostAmount = repairCostAmount }

    override val repairCost: Int
        get() = this.getInventory().repairCost

    override fun setRepairCost(repairCost: Int) = this.modifyInventory { it.repairCost = repairCost }

    override val maximumRepairCost: Int
        get() = this.getInventory().maximumRepairCost

    override fun setMaximumRepairCost(maximumRepairCost: Int) =
        this.modifyInventory { it.maximumRepairCost = maximumRepairCost }

    override val leftInput: IItemBuilder<*>?
        get() = createNullableItem(this.leftInputStack)

    override val leftInputStack: ItemStack?
        get() = this.getInventory().firstItem

    override fun setLeftItem(item: IItemBuilder<*>) = this.setLeftItem(item.build())

    override fun setLeftItem(item: ItemStack) = this.modifyInventory { it.firstItem = item }

    override fun clearLeftItem() = this.modifyInventory { it.firstItem = null }

    override val rightInput: IItemBuilder<*>?
        get() = createNullableItem(rightInputStack)

    override val rightInputStack: ItemStack?
        get() = this.getInventory().secondItem

    override fun setRightInput(item: IItemBuilder<*>) = this.setRightInput(item.build())

    override fun setRightInput(item: ItemStack) = this.modifyInventory { it.secondItem = item }

    override fun clearRightInput() = this.modifyInventory { it.secondItem = null }

    override val result: IItemBuilder<*>?
        get() = createNullableItem(this.rightInputStack)

    override val resultStack: ItemStack?
        get() = this.getInventory().result

    override fun setResult(item: IItemBuilder<*>) = this.setResult(item.build())

    override fun setResult(item: ItemStack) = this.modifyInventory { it.result = item }

    override fun clearResult() = this.modifyInventory { it.result = null }

}