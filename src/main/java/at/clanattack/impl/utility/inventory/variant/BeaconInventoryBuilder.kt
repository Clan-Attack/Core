package at.clanattack.impl.utility.inventory.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.IBeaconInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import at.clanattack.impl.utility.inventory.InventoryBuilder
import org.bukkit.inventory.BeaconInventory
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class BeaconInventoryBuilder(inventory: Inventory, core: ICore) :
    InventoryBuilder<IBeaconInventoryBuilder, BeaconInventory>(inventory, core), IBeaconInventoryBuilder {

    override fun checkInventory(inventory: Inventory) = inventory is BeaconInventory

    override fun getInstance() = this

    override val power: IItemBuilder<*>?
        get() = createNullableItem(this.powerStack)

    override val powerStack: ItemStack?
        get() = this.getInventory().item

    override fun setPower(item: IItemBuilder<*>) = this.setPower(item.build())

    override fun setPower(item: ItemStack) = this.modifyInventory { it.item = item }

    override fun clearPower() = this.modifyInventory { it.item = null }


}