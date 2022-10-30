package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.IItemBuilder
import at.clanattack.utility.item.extention.build
import at.clanattack.utility.item.extention.builder
import at.clanattack.utility.item.variants.IBundleBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BundleMeta
import org.bukkit.inventory.meta.ItemMeta

class BundleBuilder(itemStack: ItemStack, core: ICore) : ItemBuilder<IBundleBuilder, BundleMeta>(itemStack, core),
    IBundleBuilder {

    override val hasItems: Boolean
        get() = this.getMeta().hasItems()

    override val items: List<ItemStack>
        get() = this.getMeta().items

    override val itemBuilders: List<IItemBuilder<*>>
        get() = this.items.builder(this.core)

    override fun setItems(items: List<ItemStack>) = this.modifyMeta { it.setItems(items) }

    override fun setItemBuilders(items: List<IItemBuilder<*>>) = this.setItems(items.build())

    override fun addItems(items: List<ItemStack>) = this.modifyMeta { items.forEach { item -> it.addItem(item) } }

    override fun addItemBuilders(items: List<IItemBuilder<*>>) = this.addItems(items.build())

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is BundleMeta

    override fun getInstance() = this

}