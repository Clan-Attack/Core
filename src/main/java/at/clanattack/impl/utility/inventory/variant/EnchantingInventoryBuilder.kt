package at.clanattack.impl.utility.inventory.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.IEnchantingInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import at.clanattack.impl.utility.inventory.InventoryBuilder
import org.bukkit.inventory.EnchantingInventory
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class EnchantingInventoryBuilder(inventory: Inventory, core: ICore) :
    InventoryBuilder<IEnchantingInventoryBuilder, EnchantingInventory>(inventory, core), IEnchantingInventoryBuilder {

    override fun checkInventory(inventory: Inventory) = inventory is EnchantingInventory

    override fun getInstance() = this

    override val enchant: IItemBuilder<*>?
        get() = createNullableItem(this.enchantStack)

    override val enchantStack: ItemStack?
        get() = this.getInventory().item

    override fun setEnchant(item: IItemBuilder<*>) = this.setEnchant(item.build())

    override fun setEnchant(item: ItemStack) = this.modifyInventory { it.item = item }

    override fun clearEnchant() = this.modifyInventory { it.item = null }

    override val secondary: IItemBuilder<*>?
        get() = createNullableItem(this.secondaryStack)

    override val secondaryStack: ItemStack?
        get() = this.getInventory().secondary

    override fun setSecondary(item: IItemBuilder<*>) = this.setSecondary(item.build())

    override fun setSecondary(item: ItemStack) = this.modifyInventory { it.secondary = item }

    override fun clearSecondary() = this.modifyInventory { it.secondary = null }


}