package at.clanattack.impl.utility.inventory.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.IBrewerInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import at.clanattack.utility.item.createNullableItem
import at.clanattack.impl.utility.inventory.InventoryBuilder
import org.bukkit.block.BrewingStand
import org.bukkit.inventory.BrewerInventory
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class BrewerInventoryBuilder(inventory: Inventory, core: ICore) :
    InventoryBuilder<IBrewerInventoryBuilder, BrewerInventory>(inventory, core), IBrewerInventoryBuilder {

    override fun checkInventory(inventory: Inventory) = inventory is BrewerInventory

    override fun getInstance() = this

    override val ingredient: IItemBuilder<*>?
        get() = createNullableItem(this.ingredientStack)

    override val ingredientStack: ItemStack?
        get() = this.getInventory().ingredient

    override fun setIngredient(item: IItemBuilder<*>) = this.setIngredient(item.build())

    override fun setIngredient(item: ItemStack) = this.modifyInventory { it.ingredient = item }

    override fun clearIngredient() = this.modifyInventory { it.ingredient = null }

    override val fuel: IItemBuilder<*>?
        get() = createNullableItem(this.fuelStack)

    override val fuelStack: ItemStack?
        get() = this.getInventory().fuel

    override fun setFuel(item: IItemBuilder<*>) = this.setFuel(item.build())

    override fun setFuel(item: ItemStack) = this.modifyInventory { it.fuel = item }

    override fun clearFuel() = this.modifyInventory { it.fuel = null }

    override val holder: BrewingStand?
        get() = this.getInventory().holder


}