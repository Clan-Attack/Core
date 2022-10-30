package at.clanattack.utility.inventory.variant

import at.clanattack.utility.inventory.IInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import org.bukkit.inventory.ItemStack

interface IBeaconInventoryBuilder : IInventoryBuilder<IBeaconInventoryBuilder> {

    val power: IItemBuilder<*>?
    val powerStack: ItemStack?
    fun setPower(item: IItemBuilder<*>): IBeaconInventoryBuilder
    fun setPower(item: ItemStack): IBeaconInventoryBuilder
    fun clearPower(): IBeaconInventoryBuilder

}