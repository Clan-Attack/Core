package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.IDamageableBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.ItemMeta

class DamageableBuilder(itemStack: ItemStack, core: ICore) :
    ItemBuilder<IDamageableBuilder, Damageable>(itemStack, core), IDamageableBuilder {

    override val hasDamage: Boolean
        get() = this.getMeta().hasDamage()

    override val damage: Int
        get() = this.getMeta().damage

    override fun setDamage(damage: Int) = this.modifyMeta { it.damage = damage }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is Damageable

    override fun getInstance() = this

}