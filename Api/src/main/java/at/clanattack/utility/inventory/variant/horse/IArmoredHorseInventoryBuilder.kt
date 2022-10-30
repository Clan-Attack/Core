package at.clanattack.utility.inventory.variant.horse

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.inventory.ItemStack

interface IArmoredHorseInventoryBuilder<Builder : IArmoredHorseInventoryBuilder<Builder>> :
    IAbstractHorseInventoryBuilder<Builder> {

    val armor: IItemBuilder<*>?
    val armorStack: ItemStack?
    fun setArmor(item: IItemBuilder<*>): Builder
    fun setArmor(item: ItemStack): Builder
    fun clearArmor(): Builder

}