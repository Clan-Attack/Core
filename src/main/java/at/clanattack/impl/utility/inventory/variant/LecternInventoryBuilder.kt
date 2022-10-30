package at.clanattack.impl.utility.inventory.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.ILecternInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import at.clanattack.utility.item.createNullableItem
import at.clanattack.impl.utility.inventory.InventoryBuilder
import org.bukkit.block.Lectern
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.LecternInventory

class LecternInventoryBuilder(inventory: Inventory, core: ICore) :
    InventoryBuilder<ILecternInventoryBuilder, LecternInventory>(inventory, core), ILecternInventoryBuilder {

    override fun checkInventory(inventory: Inventory) = inventory is LecternInventory

    override fun getInstance() = this

    override val book: IItemBuilder<*>?
        get() = createNullableItem(this.bookStack)

    override val bookStack: ItemStack?
        get() = this.getInventory().book

    override fun setBook(item: IItemBuilder<*>) = this.setBook(item.build())

    override fun setBook(item: ItemStack) = this.modifyInventory { it.book = item }

    override fun clearBook() = this.modifyInventory { it.book = null }

    override val holder: Lectern?
        get() = this.getInstance().holder
}