package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder

interface IDamageableBuilder : IItemBuilder<IDamageableBuilder> {

    val hasDamage: Boolean
    val damage: Int
    fun setDamage(damage: Int): IDamageableBuilder

}