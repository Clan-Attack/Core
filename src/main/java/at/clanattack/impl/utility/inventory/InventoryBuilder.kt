package at.clanattack.impl.utility.inventory

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.ClickEvent
import at.clanattack.utility.inventory.CloseEvent
import at.clanattack.utility.inventory.DismissActionType
import at.clanattack.utility.inventory.IInventoryBuilder
import at.clanattack.utility.item.IItemBuilder
import at.clanattack.utility.item.createItem
import at.clanattack.utility.item.createNullableItem
import at.clanattack.utility.item.extention.build
import at.clanattack.utility.item.extention.buildNullable
import at.clanattack.utility.item.extention.nullableBuilder
import at.clanattack.xjkl.extention.putAndGet
import at.clanattack.impl.utility.inventory.action.ActionHandler
import org.bukkit.Material
import org.bukkit.entity.HumanEntity
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import kotlin.reflect.KClass
import kotlin.reflect.cast

abstract class InventoryBuilder<Interface : IInventoryBuilder<Interface>, Type : Inventory>(
    private val inventory: Inventory,
    protected val core: ICore
) : IInventoryBuilder<Interface> {

    private var systemActionHandler: ActionHandler? = null

    private val actionHandler: ActionHandler
        get() = systemActionHandler ?: run {
            systemActionHandler = ActionHandler(this.inventory)
            systemActionHandler!!
        }


    override val dismissActionType: DismissActionType
        get() = this.actionHandler.dismissActionType

    override fun setDismissActionType(dismissActionType: DismissActionType): Interface {
        this.actionHandler.dismissActionType = dismissActionType
        return this.getInstance()
    }

    override fun dismissActions(): Interface {
        this.actionHandler.dismissActions()
        return this.getInstance()
    }

    override val size: Int
        get() = this.getInventory().size

    override val maxStackSize: Int
        get() = this.getInventory().maxStackSize

    override fun setMaxStackSize(maxStackSize: Int) = this.modifyInventory { it.maxStackSize = maxStackSize }

    override fun getItem(slot: Int) = createNullableItem(this.inventory.getItem(slot))

    override fun getItemStack(slot: Int) = this.inventory.getItem(slot)

    override fun setItem(slot: Int, item: ItemStack) = this.modifyInventory { it.setItem(slot, item) }

    override fun setItem(slot: Int, item: IItemBuilder<*>) = this.setItem(slot, item.build())

    override fun setItem(slot: Int, item: ItemStack, event: ClickEvent): Interface {
        actionHandler.itemClick.putAndGet(item, mutableListOf()).add(event)
        return this.setItem(slot, item)
    }

    override fun setItem(slot: Int, item: IItemBuilder<*>, event: ClickEvent) = this.setItem(slot, item.build(), event)

    override fun addItems(vararg items: ItemStack) = this.modifyInventory { it.addItem(*items) }

    override fun addItems(vararg items: IItemBuilder<*>) = this.addItems(*items.build())

    override fun addItems(vararg items: ItemStack, event: ClickEvent): Interface {
        items.forEach { item -> actionHandler.itemClick.putAndGet(item, mutableListOf()).add(event) }
        return this.addItems(*items)
    }

    override fun addItems(vararg items: IItemBuilder<*>, event: ClickEvent) =
        this.addItems(*items.build(), event = event)

    override fun fill(item: ItemStack, overwrite: Boolean) = this.modifyInventory {
        for (i in 0 until this.getInventory().size) {
            if (overwrite) it.setItem(i, item)
            else if (it.getItem(i) == null) it.setItem(i, item)
        }
    }

    override fun fill(item: IItemBuilder<*>, overwrite: Boolean) = this.fill(item.build(), overwrite)

    override fun fill(item: ItemStack, overwrite: Boolean, event: ClickEvent): Interface {
        actionHandler.itemClick.putAndGet(item, mutableListOf()).add(event)
        return this.fill(item, overwrite)
    }

    override fun fill(item: IItemBuilder<*>, overwrite: Boolean, event: ClickEvent) =
        this.fill(item.build(), overwrite, event)

    override fun removeItems(vararg items: ItemStack) = this.modifyInventory { it.removeItem(*items) }

    override fun removeItems(vararg items: IItemBuilder<*>) = this.removeItems(*items.build())

    override fun cancelClicks(): Interface {
        this.actionHandler.cancelClicks = true
        return this.getInstance()
    }

    override fun onAnyClick(event: ClickEvent): Interface {
        this.actionHandler.anyClick.add(event)
        return this.getInstance()
    }

    override fun onClose(event: CloseEvent): Interface {
        this.actionHandler.close.add(event)
        return this.getInstance()
    }

    override val content: Array<IItemBuilder<*>?>
        get() = this.stackContent.nullableBuilder(this.core)

    override val stackContent: Array<ItemStack?>
        get() = this.getInventory().contents

    override fun setContent(vararg content: ItemStack?) = this.modifyInventory { it.contents = content }

    override fun setContent(vararg content: IItemBuilder<*>?) = this.setContent(*content.buildNullable())

    override fun contains(material: Material) = this.getInventory().contains(material)

    override fun contains(item: ItemStack) = this.getInventory().contains(item)

    override fun contains(item: IItemBuilder<*>) = this.getInventory().contains(item.build())

    override fun contains(material: Material, amount: Int) = this.getInventory().contains(material, amount)

    override fun contains(item: ItemStack, amount: Int) = this.getInventory().contains(item, amount)

    override fun contains(item: IItemBuilder<*>, amount: Int) = this.contains(item.build(), amount)

    override fun containsAtLeast(item: ItemStack, amount: Int) = this.getInventory().containsAtLeast(item, amount)

    override fun containsAtLeast(item: IItemBuilder<*>, amount: Int) = this.containsAtLeast(item.build(), amount)

    override fun all() = this.allStack().map { (i, c) -> i to createItem(c) }.toMap()

    override fun all(material: Material) = this.allStack(material).map { (i, c) -> i to createItem(c) }.toMap()

    override fun all(item: ItemStack) = this.allStack(item).map { (i, c) -> i to createItem(c) }.toMap()

    override fun all(item: IItemBuilder<*>) = this.allStack(item).map { (i, c) -> i to createItem(c) }.toMap()

    override fun allStack() = this.stackContent.asSequence()
        .mapIndexed { i, c -> i to c }
        .filter { (_, c) -> c != null }
        .map { (i, c) -> i to c!! }
        .toMap()

    override fun allStack(material: Material) = this.getInventory().all(material)

    override fun allStack(item: ItemStack) = this.getInventory().all(item)

    override fun allStack(item: IItemBuilder<*>) = this.allStack(item.build())

    override fun first(material: Material) = this.getInventory().first(material)

    override fun first(itemStack: ItemStack) = this.getInventory().first(itemStack)

    override fun first(itemStack: IItemBuilder<*>) = this.getInventory().first(itemStack.build())

    override fun firstEmpty() = this.getInventory().firstEmpty()

    override fun remove(material: Material) = this.modifyInventory { it.remove(material) }

    override fun clear(slot: Int) = this.modifyInventory { it.clear(slot) }

    override fun clear() = this.modifyInventory { it.clear() }

    override fun isEmpty() = this.getInventory().isEmpty

    override fun isNotEmpty() = !this.isEmpty()

    override val viewers: List<HumanEntity>
        get() = this.inventory.viewers

    override val type: InventoryType
        get() = this.inventory.type

    override val holder: InventoryHolder?
        get() = this.inventory.holder

    override fun <T : IInventoryBuilder<out T>> asBuilder(builder: KClass<T>): T {
        if (builder.isInstance(this)) return builder.cast(this)

        val builderInformation = InventoryHandler.inventoryBuilders[builder]
            ?: throw IllegalStateException("All InventoryBuilders should be registered")

        if (builderInformation.second.isInstance(this.inventory)) {
            return builder.cast(
                builderInformation.first.java
                    .getDeclaredConstructor(Inventory::class.java, ICore::class.java)
                    .newInstance(this.inventory, this.core)
            )
        }

        throw IllegalArgumentException("Can not cast to class witch is not implemented.")
    }

    override fun build() = this.inventory

    protected fun modifyInventory(inventory: (Type) -> Unit): Interface {
        inventory(this.getInventory())
        return this.getInstance()
    }

    @Suppress("UNCHECKED_CAST")
    protected fun getInventory() = inventory as Type

    abstract fun checkInventory(inventory: Inventory): Boolean

    abstract fun getInstance(): Interface

}