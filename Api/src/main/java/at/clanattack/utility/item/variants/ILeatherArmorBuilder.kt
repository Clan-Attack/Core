package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.Color

interface ILeatherArmorBuilder : IItemBuilder<ILeatherArmorBuilder> {

    val color: Color
    fun setColor(color: Color): ILeatherArmorBuilder

}