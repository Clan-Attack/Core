package at.clanattack.impl.utility.inventory.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.IPlayerInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import at.clanattack.utility.item.createItem
import at.clanattack.utility.item.createNullableItem
import at.clanattack.utility.item.extention.buildNullable
import at.clanattack.utility.item.extention.nullableBuilder
import at.clanattack.impl.utility.inventory.InventoryBuilder
import org.bukkit.entity.HumanEntity
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory

class PlayerInventoryBuilder(inventory: Inventory, core: ICore) :
    InventoryBuilder<IPlayerInventoryBuilder, PlayerInventory>(inventory, core), IPlayerInventoryBuilder {

    override fun checkInventory(inventory: Inventory) = inventory is PlayerInventory

    override fun getInstance() = this

    override val amorContents: Array<IItemBuilder<*>?>
        get() = this.amorContentsStack.nullableBuilder(this.core)

    override val amorContentsStack: Array<ItemStack?>
        get() = this.getInventory().armorContents

    override fun setArmorContents(vararg items: IItemBuilder<*>?) = this.setArmorContents(*items.buildNullable())

    override fun setArmorContents(vararg items: ItemStack?) = this.modifyInventory { it.armorContents = items }

    override fun clearArmorContents() = this.setArmorContents(*emptyArray<ItemStack>())

    override val extraContents: Array<IItemBuilder<*>?>
        get() = this.extraContentsStack.nullableBuilder(this.core)

    override val extraContentsStack: Array<ItemStack?>
        get() = this.getInventory().extraContents

    override fun setExtraContents(vararg items: IItemBuilder<*>?) = this.setExtraContents(*items.buildNullable())

    override fun setExtraContents(vararg items: ItemStack?) = this.modifyInventory { it.extraContents = items }

    override fun clearExtraContents() = this.setExtraContents(*emptyArray<ItemStack>())

    override val helmet: IItemBuilder<*>?
        get() = createNullableItem(this.helmetStack)

    override val helmetStack: ItemStack?
        get() = this.getInventory().helmet

    override fun setHelmet(item: IItemBuilder<*>) = this.setHelmet(item.build())

    override fun setHelmet(item: ItemStack) = this.modifyInventory { it.helmet = item }

    override fun clearHelmet() = this.modifyInventory { it.helmet = null }

    override val chestplate: IItemBuilder<*>?
        get() = createNullableItem(this.chestplateStack)

    override val chestplateStack: ItemStack?
        get() = this.getInventory().chestplate

    override fun setChestplate(item: IItemBuilder<*>) = this.setChestplate(item.build())

    override fun setChestplate(item: ItemStack) = this.modifyInventory { it.chestplate = item }

    override fun clearChestplate() = this.modifyInventory { it.chestplate = null }

    override val leggings: IItemBuilder<*>?
        get() = createNullableItem(this.leggingsStack)

    override val leggingsStack: ItemStack?
        get() = this.getInventory().leggings

    override fun setLeggings(item: IItemBuilder<*>) = this.setLeggings(item.build())

    override fun setLeggings(item: ItemStack) = this.modifyInventory { it.leggings = item }

    override fun clearLeggings() = this.modifyInventory { it.leggings = null }

    override val boots: IItemBuilder<*>?
        get() = createNullableItem(this.bootsStack)

    override val bootsStack: ItemStack?
        get() = this.getInventory().boots

    override fun setBoots(item: IItemBuilder<*>) = this.setBoots(item.build())

    override fun setBoots(item: ItemStack) = this.modifyInventory { it.boots = item }

    override fun clearBoots() = this.modifyInventory { it.boots = null }

    override fun setItem(slot: EquipmentSlot, item: IItemBuilder<*>) = this.setItem(slot, item.build())

    override fun setItem(slot: EquipmentSlot, item: ItemStack) = this.modifyInventory { it.setItem(slot, item) }

    override val mainHand: IItemBuilder<*>
        get() = createItem(this.mainHandStack)

    override val mainHandStack: ItemStack
        get() = this.getInventory().itemInMainHand

    override fun setMainHand(item: IItemBuilder<*>) = this.setMainHand(item.build())

    override fun setMainHand(item: ItemStack) = this.modifyInventory { it.setItemInMainHand(item) }

    override fun clearMainHand() = this.modifyInventory { it.setItemInMainHand(null) }

    override val offHand: IItemBuilder<*>
        get() = createItem(this.offHandStack)

    override val offHandStack: ItemStack
        get() = this.getInventory().itemInOffHand

    override fun setOffHand(item: IItemBuilder<*>) = this.setOffHand(item.build())

    override fun setOffHand(item: ItemStack) = this.modifyInventory { it.setItemInOffHand(item) }

    override fun clearOffHand() = this.modifyInventory { it.setItemInOffHand(null) }

    override val heldItemSlot: Int
        get() = this.getInventory().heldItemSlot

    override fun setHeldItemSlot(slot: Int) = this.modifyInventory { it.heldItemSlot = slot }

    override val holder: HumanEntity?
        get() = this.getInventory().holder
}