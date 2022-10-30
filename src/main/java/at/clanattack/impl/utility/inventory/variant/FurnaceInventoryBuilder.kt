package at.clanattack.impl.utility.inventory.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.IFurnaceInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import at.clanattack.utility.item.createNullableItem
import at.clanattack.impl.utility.inventory.InventoryBuilder
import org.bukkit.block.Furnace
import org.bukkit.inventory.FurnaceInventory
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class FurnaceInventoryBuilder(inventory: Inventory, core: ICore) :
    InventoryBuilder<IFurnaceInventoryBuilder, FurnaceInventory>(inventory, core), IFurnaceInventoryBuilder {

    override fun checkInventory(inventory: Inventory) = inventory is FurnaceInventory

    override fun getInstance() = this

    override val smelting: IItemBuilder<*>?
        get() = createNullableItem(this.smeltingStack)

    override val smeltingStack: ItemStack?
        get() = this.getInventory().smelting

    override fun setSmelting(item: IItemBuilder<*>) = this.setSmelting(item.build())

    override fun setSmelting(item: ItemStack) = this.modifyInventory { it.smelting = item }

    override fun clearSmelting() = this.modifyInventory { it.smelting = null }

    override val fuel: IItemBuilder<*>?
        get() = createNullableItem(this.fuelStack)

    override val fuelStack: ItemStack?
        get() = this.getInventory().fuel

    override fun setFuel(item: IItemBuilder<*>) = this.setFuel(item.build())

    override fun setFuel(item: ItemStack) = this.modifyInventory { it.fuel = item }

    override fun clearFuel() = this.modifyInventory { it.fuel = null }

    override val result: IItemBuilder<*>?
        get() = createNullableItem(this.resultStack)

    override val resultStack: ItemStack?
        get() = this.getInventory().result

    override fun setResult(item: IItemBuilder<*>) = this.setResult(item.build())

    override fun setResult(item: ItemStack) = this.modifyInventory { it.result = item }

    override fun clearResult() = this.modifyInventory { it.result = null }

    override fun isFuel(item: IItemBuilder<*>) = this.isFuel(item.build())

    override fun isFuel(item: ItemStack) = this.getInventory().isFuel(item)

    override fun canSmelt(item: IItemBuilder<*>) = this.canSmelt(item.build())

    override fun canSmelt(item: ItemStack) = this.getInventory().canSmelt(item)

    override val holder: Furnace?
        get() = this.getInstance().holder
}