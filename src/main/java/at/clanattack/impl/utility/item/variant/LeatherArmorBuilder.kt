package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.ILeatherArmorBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.Color
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.LeatherArmorMeta

class LeatherArmorBuilder(itemStack: ItemStack, core: ICore) :
    ItemBuilder<ILeatherArmorBuilder, LeatherArmorMeta>(itemStack, core), ILeatherArmorBuilder {

    override val color: Color
        get() = this.getMeta().color

    override fun setColor(color: Color) = this.modifyMeta { it.setColor(color) }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is LeatherArmorMeta

    override fun getInstance() = this

}