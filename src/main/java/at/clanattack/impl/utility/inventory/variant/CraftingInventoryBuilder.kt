package at.clanattack.impl.utility.inventory.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.ICraftingInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import at.clanattack.utility.item.createNullableItem
import at.clanattack.utility.item.extention.nullableBuilder
import at.clanattack.impl.utility.inventory.InventoryBuilder
import org.bukkit.inventory.CraftingInventory
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe

class CraftingInventoryBuilder(inventory: Inventory, core: ICore) :
    InventoryBuilder<ICraftingInventoryBuilder, CraftingInventory>(inventory, core), ICraftingInventoryBuilder {

    override fun checkInventory(inventory: Inventory) = inventory is CraftingInventory

    override fun getInstance() = this

    override val result: IItemBuilder<*>?
        get() = createNullableItem(this.resultStack)

    override val resultStack: ItemStack?
        get() = this.getInventory().result

    override fun setResult(item: IItemBuilder<*>) = this.setResult(item.build())

    override fun setResult(item: ItemStack) = this.modifyInventory { it.result = item }

    override fun clearResult() = this.modifyInventory { it.result = null }

    override val matrix: Array<IItemBuilder<*>?>
        get() = this.matrixStack.nullableBuilder(this.core)

    override val matrixStack: Array<ItemStack?>
        get() = this.getInventory().matrix

    override fun setMatrix(vararg item: IItemBuilder<*>?) = this.setMatrix(*item.map { it?.build() }.toTypedArray())

    override fun setMatrix(vararg item: ItemStack?) = this.modifyInventory { it.matrix = item }

    override fun clearMatrix() = this.setMatrix(*emptyArray<ItemStack>())

    override val recipe: Recipe?
        get() = this.getInventory().recipe
}