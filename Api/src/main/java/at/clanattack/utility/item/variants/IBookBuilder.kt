package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import net.kyori.adventure.text.Component
import org.bukkit.inventory.meta.BookMeta.Generation

@Suppress("unused")
interface IBookBuilder : IItemBuilder<IBookBuilder> {

    val hasTitle: Boolean
    val title: Component?
    fun setTitle(title: Component): IBookBuilder

    val hasAuthor: Boolean
    val author: Component?
    fun setAuthor(author: Component): IBookBuilder

    val hasGeneration: Boolean
    val generation: Generation?
    fun setGeneration(generation: Generation): IBookBuilder

    val hasPages: Boolean
    val pageCount: Int

    fun getPage(page: Int): Component
    fun setPage(page: Int, data: Component): IBookBuilder
    fun addPages(vararg pages: Component): IBookBuilder
    fun addPages(pages: List<Component>) = addPages(*pages.toTypedArray())

}