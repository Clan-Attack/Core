package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.inventory.ItemStack

interface IBundleBuilder : IItemBuilder<IBundleBuilder> {

    val hasItems: Boolean
    val items: List<ItemStack>
    val itemBuilders: List<IItemBuilder<*>>

    fun setItems(items: List<ItemStack>): IBundleBuilder
    fun setItems(vararg items: ItemStack) = setItems(items.toList())
    fun setItemBuilders(items: List<IItemBuilder<*>>): IBundleBuilder
    fun setItems(vararg items: IItemBuilder<*>) = setItemBuilders(items.toList())

    fun addItems(items: List<ItemStack>): IBundleBuilder
    fun addItems(vararg items: ItemStack) = addItems(items.toList())
    fun addItemBuilders(items: List<IItemBuilder<*>>): IBundleBuilder
    fun addItems(vararg items: IItemBuilder<*>) = addItemBuilders(items.toList())


}