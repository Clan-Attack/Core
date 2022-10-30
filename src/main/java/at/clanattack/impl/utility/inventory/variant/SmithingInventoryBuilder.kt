package at.clanattack.impl.utility.inventory.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.ISmithingInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import at.clanattack.utility.item.createNullableItem
import at.clanattack.impl.utility.inventory.InventoryBuilder
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.SmithingInventory

class SmithingInventoryBuilder(inventory: Inventory, core: ICore) :
    InventoryBuilder<ISmithingInventoryBuilder, SmithingInventory>(inventory, core), ISmithingInventoryBuilder {

    override fun checkInventory(inventory: Inventory) = inventory is SmithingInventory

    override fun getInstance() = this

    override val result: IItemBuilder<*>?
        get() = createNullableItem(this.resultStack)

    override val resultStack: ItemStack?
        get() = this.getInventory().result

    override fun setResult(item: IItemBuilder<*>) = this.setResult(item.build())

    override fun setResult(item: ItemStack) = this.modifyInventory { it.result = item }

    override fun clearResult() = this.modifyInventory { it.result = null }

    override val inputEquipment: IItemBuilder<*>?
        get() = createNullableItem(this.inputEquipmentStack)

    override val inputEquipmentStack: ItemStack?
        get() = this.getInventory().inputEquipment

    override fun setInputEquipment(item: IItemBuilder<*>) = this.setInputEquipment(item.build())

    override fun setInputEquipment(item: ItemStack) = this.modifyInventory { it.inputEquipment = item }

    override fun clearInputEquipment() = this.modifyInventory { it.inputEquipment = null }

    override val recipe: Recipe?
        get() = this.getInventory().recipe
}