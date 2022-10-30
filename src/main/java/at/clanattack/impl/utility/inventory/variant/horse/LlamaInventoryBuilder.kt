package at.clanattack.impl.utility.inventory.variant.horse

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.horse.ILlamaInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import at.clanattack.utility.item.createNullableItem
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.LlamaInventory

class LlamaInventoryBuilder(inventory: Inventory, core: ICore) :
    SaddledHorseInventoryBuilder<ILlamaInventoryBuilder, LlamaInventory>(inventory, core), ILlamaInventoryBuilder {

    override fun checkInventory(inventory: Inventory) = inventory is LlamaInventory

    override fun getInstance() = this

    override val decor: IItemBuilder<*>?
        get() = createNullableItem(this.decorStack)

    override val decorStack: ItemStack?
        get() = this.getInventory().decor

    override fun setDecor(item: IItemBuilder<*>) = this.setDecor(item.build())

    override fun setDecor(item: ItemStack) = this.modifyInventory { it.decor = item }

    override fun clearDecor() = this.modifyInventory { it.decor = null }

}