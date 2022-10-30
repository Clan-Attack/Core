package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.inventory.ItemStack

@Suppress("unused")
interface ICrossbowBuilder : IItemBuilder<ICrossbowBuilder> {

    val hasChargedProjectiles: Boolean
    val chargedProjectiles: List<ItemStack>
    val chargedProjectileBuilders: List<IItemBuilder<*>>

    fun setChargedProjectiles(projectiles: List<ItemStack>): ICrossbowBuilder
    fun setChargedProjectiles(vararg projectiles: ItemStack) = setChargedProjectiles(projectiles.toList())
    fun setChargedProjectileBuilders(projectiles: List<IItemBuilder<*>>): ICrossbowBuilder
    fun setChargedProjectiles(vararg projectiles: IItemBuilder<*>) = setChargedProjectileBuilders(projectiles.toList())

    fun addChargedProjectiles(projectiles: List<ItemStack>): ICrossbowBuilder
    fun addChargedProjectiles(vararg projectiles: ItemStack) = addChargedProjectiles(projectiles.toList())
    fun addChargedProjectileBuilders(projectiles: List<IItemBuilder<*>>): ICrossbowBuilder
    fun addChargedProjectiles(vararg projectiles: IItemBuilder<*>) = addChargedProjectileBuilders(projectiles.toList())

}