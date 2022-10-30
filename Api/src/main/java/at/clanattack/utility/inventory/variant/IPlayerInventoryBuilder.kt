package at.clanattack.utility.inventory.variant

import at.clanattack.utility.inventory.IInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import org.bukkit.entity.HumanEntity
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

interface IPlayerInventoryBuilder : IInventoryBuilder<IPlayerInventoryBuilder> {

    val amorContents: Array<IItemBuilder<*>?>
    val amorContentsStack: Array<ItemStack?>
    fun setArmorContents(vararg items: IItemBuilder<*>?): IPlayerInventoryBuilder
    fun setArmorContents(vararg items: ItemStack?): IPlayerInventoryBuilder
    fun clearArmorContents(): IPlayerInventoryBuilder

    val extraContents: Array<IItemBuilder<*>?>
    val extraContentsStack: Array<ItemStack?>
    fun setExtraContents(vararg items: IItemBuilder<*>?): IPlayerInventoryBuilder
    fun setExtraContents(vararg items: ItemStack?): IPlayerInventoryBuilder
    fun clearExtraContents(): IPlayerInventoryBuilder

    val helmet: IItemBuilder<*>?
    val helmetStack: ItemStack?
    fun setHelmet(item: IItemBuilder<*>): IPlayerInventoryBuilder
    fun setHelmet(item: ItemStack): IPlayerInventoryBuilder
    fun clearHelmet(): IPlayerInventoryBuilder

    val chestplate: IItemBuilder<*>?
    val chestplateStack: ItemStack?
    fun setChestplate(item: IItemBuilder<*>): IPlayerInventoryBuilder
    fun setChestplate(item: ItemStack): IPlayerInventoryBuilder
    fun clearChestplate(): IPlayerInventoryBuilder

    val leggings: IItemBuilder<*>?
    val leggingsStack: ItemStack?
    fun setLeggings(item: IItemBuilder<*>): IPlayerInventoryBuilder
    fun setLeggings(item: ItemStack): IPlayerInventoryBuilder
    fun clearLeggings(): IPlayerInventoryBuilder

    val boots: IItemBuilder<*>?
    val bootsStack: ItemStack?
    fun setBoots(item: IItemBuilder<*>): IPlayerInventoryBuilder
    fun setBoots(item: ItemStack): IPlayerInventoryBuilder
    fun clearBoots(): IPlayerInventoryBuilder

    fun setItem(slot: EquipmentSlot, item: IItemBuilder<*>): IPlayerInventoryBuilder
    fun setItem(slot: EquipmentSlot, item: ItemStack): IPlayerInventoryBuilder

    val mainHand: IItemBuilder<*>
    val mainHandStack: ItemStack
    fun setMainHand(item: IItemBuilder<*>): IPlayerInventoryBuilder
    fun setMainHand(item: ItemStack): IPlayerInventoryBuilder
    fun clearMainHand(): IPlayerInventoryBuilder

    val offHand: IItemBuilder<*>
    val offHandStack: ItemStack
    fun setOffHand(item: IItemBuilder<*>): IPlayerInventoryBuilder
    fun setOffHand(item: ItemStack): IPlayerInventoryBuilder
    fun clearOffHand(): IPlayerInventoryBuilder

    val heldItemSlot: Int
    fun setHeldItemSlot(slot: Int): IPlayerInventoryBuilder

    override val holder: HumanEntity?

}