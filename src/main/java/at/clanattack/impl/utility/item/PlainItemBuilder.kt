package at.clanattack.impl.utility.item

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.IItemBuilder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

interface IPlainItemBuilder : IItemBuilder<IPlainItemBuilder>

class PlainItemBuilder(itemStack: ItemStack, core: ICore) : ItemBuilder<IPlainItemBuilder, ItemMeta>(itemStack, core),
    IPlainItemBuilder {

    override fun checkMeta(itemMeta: ItemMeta) = true

    override fun getInstance() = this

}