package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.FireworkEffect

@Suppress("unused")
interface IFireworkBuilder : IItemBuilder<IFireworkBuilder> {

    val effectSize: Int
    val hasEffects: Boolean
    val effects: List<FireworkEffect>
    fun setEffects(effects: List<FireworkEffect>): IFireworkBuilder
    fun setEffects(vararg effects: FireworkEffect) = addEffects(effects.toList())
    fun addEffects(effects: List<FireworkEffect>): IFireworkBuilder
    fun addEffects(vararg effects: FireworkEffect) = addEffects(effects.toList())
    fun removeEffect(index: Int): IFireworkBuilder
    fun clearEffects(): IFireworkBuilder

    val power: Int
    fun setPower(power: Int): IFireworkBuilder

}