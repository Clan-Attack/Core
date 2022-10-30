package at.clanattack.utility.inventory

import at.clanattack.utility.inventory.variant.*
import at.clanattack.utility.inventory.variant.horse.*
import at.clanattack.utility.item.IItemBuilder
import org.bukkit.Material
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import kotlin.reflect.KClass

typealias ClickEvent = (Player, event: InventoryClickEvent) -> Unit
typealias CloseEvent = (Player, event: InventoryCloseEvent) -> Unit

interface IInventoryBuilder<Builder : IInventoryBuilder<Builder>> {

    val dismissActionType: DismissActionType
    fun setDismissActionType(dismissActionType: DismissActionType): Builder
    fun neverDismiss() = setDismissActionType(DismissActionType.NEVER)
    fun dismissActions(): Builder

    val size: Int
    val maxStackSize: Int
    fun setMaxStackSize(maxStackSize: Int): Builder

    fun getItem(slot: Int): IItemBuilder<*>?
    fun getItemStack(slot: Int): ItemStack?
    fun setItem(slot: Int, item: ItemStack): Builder
    fun setItem(slot: Int, item: IItemBuilder<*>): Builder
    fun setItem(slot: Int, item: ItemStack, event: ClickEvent): Builder
    fun setItem(slot: Int, item: IItemBuilder<*>, event: ClickEvent): Builder

    fun addItems(vararg items: ItemStack): Builder
    fun addItems(vararg items: IItemBuilder<*>): Builder
    fun addItems(vararg items: ItemStack, event: ClickEvent): Builder
    fun addItems(vararg items: IItemBuilder<*>, event: ClickEvent): Builder

    fun fill(item: ItemStack, overwrite: Boolean): Builder
    fun fill(item: IItemBuilder<*>, overwrite: Boolean): Builder
    fun fill(item: ItemStack, overwrite: Boolean, event: ClickEvent): Builder
    fun fill(item: IItemBuilder<*>, overwrite: Boolean, event: ClickEvent): Builder

    fun removeItems(vararg items: ItemStack): Builder
    fun removeItems(vararg items: IItemBuilder<*>): Builder

    fun cancelClicks(): Builder
    fun onAnyClick(event: ClickEvent): Builder

    fun onClose(event: CloseEvent): Builder

    val content: Array<IItemBuilder<*>?>
    val stackContent: Array<ItemStack?>
    fun setContent(vararg content: ItemStack?): Builder
    fun setContent(vararg content: IItemBuilder<*>?): Builder

    fun contains(material: Material): Boolean
    fun contains(item: ItemStack): Boolean
    fun contains(item: IItemBuilder<*>): Boolean
    fun contains(material: Material, amount: Int): Boolean
    fun contains(item: ItemStack, amount: Int): Boolean
    fun contains(item: IItemBuilder<*>, amount: Int): Boolean
    fun containsAtLeast(item: ItemStack, amount: Int): Boolean
    fun containsAtLeast(item: IItemBuilder<*>, amount: Int): Boolean

    fun all(): Map<Int, IItemBuilder<*>>
    fun all(material: Material): Map<Int, IItemBuilder<*>>
    fun all(item: ItemStack): Map<Int, IItemBuilder<*>>
    fun all(item: IItemBuilder<*>): Map<Int, IItemBuilder<*>>
    fun allStack(): Map<Int, ItemStack>
    fun allStack(material: Material): Map<Int, ItemStack>
    fun allStack(item: ItemStack): Map<Int, ItemStack>
    fun allStack(item: IItemBuilder<*>): Map<Int, ItemStack>

    fun first(material: Material): Int
    fun first(itemStack: ItemStack): Int
    fun first(itemStack: IItemBuilder<*>): Int
    fun firstEmpty(): Int

    fun remove(material: Material): Builder
    fun clear(slot: Int): Builder
    fun clear(): Builder

    fun isEmpty(): Boolean
    fun isNotEmpty(): Boolean

    val viewers: List<HumanEntity>
    val type: InventoryType
    val holder: InventoryHolder?

    fun <T : IInventoryBuilder<out T>> asBuilder(builder: KClass<T>): T
    fun asAnvilBuilder() = asBuilder(IAnvilInventoryBuilder::class)
    fun asBeaconBuilder() = asBuilder(IBeaconInventoryBuilder::class)
    fun asBrewerBuilder() = asBuilder(IBrewerInventoryBuilder::class)
    fun asCartographyBuilder() = asBuilder(ICartographyInventoryBuilder::class)
    fun asCraftingBuilder() = asBuilder(ICraftingInventoryBuilder::class)
    fun asDoubleChestBuilder() = asBuilder(IDoubleChestInventoryBuilder::class)
    fun asEnchantingBuilder() = asBuilder(IEnchantingInventoryBuilder::class)
    fun asFurnaceBuilder() = asBuilder(IFurnaceInventoryBuilder::class)
    fun asLecternBuilder() = asBuilder(ILecternInventoryBuilder::class)
    fun asLoomBuilder() = asBuilder(ILoomInventoryBuilder::class)
    fun asMerchantBuilder() = asBuilder(IMerchantInventoryBuilder::class)
    fun asPlayerBuilder() = asBuilder(IPlayerInventoryBuilder::class)
    fun asSmithingBuilder() = asBuilder(ISmithingInventoryBuilder::class)

    fun asAbstractHorseBuilder() = asBuilder(IAbstractHorseInventoryBuilder::class)
    fun asArmoredHorseBuilder() = asBuilder(IArmoredHorseInventoryBuilder::class)
    fun asHorseBuilder() = asBuilder(IHorseInventoryBuilder::class)
    fun asLlamaBuilder() = asBuilder(ILlamaInventoryBuilder::class)
    fun asSaddledHorseBuilder() = asBuilder(ISaddledHorseInventoryBuilder::class)

    fun build(): Inventory

}
