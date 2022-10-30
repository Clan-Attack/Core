package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.IBookBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import net.kyori.adventure.text.Component
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta
import org.bukkit.inventory.meta.ItemMeta

class BookBuilder(itemStack: ItemStack, core: ICore) : ItemBuilder<IBookBuilder, BookMeta>(itemStack, core),
    IBookBuilder {

    override val hasTitle: Boolean
        get() = this.getMeta().hasTitle()

    override val title: Component?
        get() = this.getMeta().title()

    override fun setTitle(title: Component) = this.modifyMeta { it.title(title) }

    override val hasAuthor: Boolean
        get() = this.getMeta().hasAuthor()

    override val author: Component?
        get() = this.getMeta().author()

    override fun setAuthor(author: Component) = this.modifyMeta { it.author(author) }

    override val hasGeneration: Boolean
        get() = this.getMeta().hasGeneration()

    override val generation: BookMeta.Generation?
        get() = this.getMeta().generation

    override fun setGeneration(generation: BookMeta.Generation) = this.modifyMeta { it.generation = generation }

    override val hasPages: Boolean
        get() = this.getMeta().hasPages()

    override val pageCount: Int
        get() = this.getMeta().pageCount

    override fun getPage(page: Int) = this.getMeta().page(page)

    override fun setPage(page: Int, data: Component) = this.modifyMeta { it.page(page, data) }

    override fun addPages(vararg pages: Component) = this.modifyMeta { it.pages(*pages) }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is BookMeta

    override fun getInstance() = this
}