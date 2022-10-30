package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.enchantments.Enchantment

interface IEnchantmentStorageBuilder : IItemBuilder<IEnchantmentStorageBuilder> {

    val hasStoredEnchantments: Boolean
    val storedEnchantments: Map<Enchantment, Int>

    fun hasStoredEnchantment(enchantment: Enchantment): Boolean
    fun getStoredEnchantmentLevel(enchantment: Enchantment): Int

    fun addStoredEnchantment(enchantment: Enchantment, level: Int): IEnchantmentStorageBuilder
    fun removeStoredEnchantment(enchantment: Enchantment): IEnchantmentStorageBuilder

    fun hasConflictingSoredEnchantment(enchantment: Enchantment): Boolean

}