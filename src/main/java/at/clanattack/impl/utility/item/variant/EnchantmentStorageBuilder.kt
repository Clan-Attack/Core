package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.IEnchantmentStorageBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.EnchantmentStorageMeta
import org.bukkit.inventory.meta.ItemMeta

class EnchantmentStorageBuilder(itemStack: ItemStack, core: ICore) :
    ItemBuilder<IEnchantmentStorageBuilder, EnchantmentStorageMeta>(itemStack, core), IEnchantmentStorageBuilder {

    override val hasStoredEnchantments: Boolean
        get() = this.getMeta().hasStoredEnchants()

    override val storedEnchantments: Map<Enchantment, Int>
        get() = this.getMeta().storedEnchants

    override fun hasStoredEnchantment(enchantment: Enchantment) = this.getMeta().hasStoredEnchant(enchantment)

    override fun getStoredEnchantmentLevel(enchantment: Enchantment) = this.getMeta().getStoredEnchantLevel(enchantment)

    override fun addStoredEnchantment(enchantment: Enchantment, level: Int) =
        this.modifyMeta { it.addStoredEnchant(enchantment, level, true) }

    override fun removeStoredEnchantment(enchantment: Enchantment) =
        this.modifyMeta { it.removeStoredEnchant(enchantment) }

    override fun hasConflictingSoredEnchantment(enchantment: Enchantment) =
        this.getMeta().hasConflictingEnchant(enchantment)

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is EnchantmentStorageMeta

    override fun getInstance() = this

}