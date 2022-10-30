package at.clanattack.impl.utility.inventory.variant.horse

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.horse.IAbstractHorseInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import at.clanattack.impl.utility.inventory.InventoryBuilder
import org.bukkit.inventory.AbstractHorseInventory
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

abstract class AbstractHorseInventoryBuilder
<Builder : IAbstractHorseInventoryBuilder<Builder>, Type : AbstractHorseInventory>(
    inventory: Inventory,
    core: ICore
) : InventoryBuilder<Builder, Type>(inventory, core), IAbstractHorseInventoryBuilder<Builder> {

    override val saddle: IItemBuilder<*>?
        get() = createNullableItem(this.saddleStack)

    override val saddleStack: ItemStack?
        get() = this.getInventory().saddle

    override fun setSaddle(item: IItemBuilder<*>) = this.setSaddle(item.build())

    override fun setSaddle(item: ItemStack) = this.modifyInventory { it.saddle = item }

    override fun clearSaddle() = this.modifyInventory { it.saddle = null }


}