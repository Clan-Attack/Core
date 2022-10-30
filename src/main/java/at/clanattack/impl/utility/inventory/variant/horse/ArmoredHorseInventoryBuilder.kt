package at.clanattack.impl.utility.inventory.variant.horse

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.horse.IArmoredHorseInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import org.bukkit.inventory.ArmoredHorseInventory
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

abstract class ArmoredHorseInventoryBuilder
<Builder : IArmoredHorseInventoryBuilder<Builder>, Type : ArmoredHorseInventory>(
    inventory: Inventory,
    core: ICore
) : AbstractHorseInventoryBuilder<Builder, Type>(inventory, core), IArmoredHorseInventoryBuilder<Builder> {

    override val armor: IItemBuilder<*>?
        get() = createNullableItem(this.armorStack)

    override val armorStack: ItemStack?
        get() = this.getInventory().armor

    override fun setArmor(item: IItemBuilder<*>) = this.setArmor(item.build())

    override fun setArmor(item: ItemStack) = this.modifyInventory { it.armor = item }

    override fun clearArmor() = this.modifyInventory { it.armor = null }

}