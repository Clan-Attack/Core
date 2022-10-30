package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.FireworkEffect

interface IFireworkEffectBuilder : IItemBuilder<IFireworkEffectBuilder> {

    val hasEffect: Boolean
    val effect: FireworkEffect?

    fun removeEffect(): IFireworkEffectBuilder
    fun setEffect(effect: FireworkEffect): IFireworkEffectBuilder

}