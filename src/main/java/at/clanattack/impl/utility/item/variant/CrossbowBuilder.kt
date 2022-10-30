package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.IItemBuilder
import at.clanattack.utility.item.extention.build
import at.clanattack.utility.item.extention.builder
import at.clanattack.utility.item.variants.ICrossbowBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.CrossbowMeta
import org.bukkit.inventory.meta.ItemMeta

class CrossbowBuilder(itemStack: ItemStack, core: ICore) : ItemBuilder<ICrossbowBuilder, CrossbowMeta>(itemStack, core),
    ICrossbowBuilder {

    override val hasChargedProjectiles: Boolean
        get() = this.getMeta().hasChargedProjectiles()

    override val chargedProjectiles: List<ItemStack>
        get() = this.getMeta().chargedProjectiles

    override val chargedProjectileBuilders: List<IItemBuilder<*>>
        get() = this.chargedProjectiles.builder(this.core)

    override fun setChargedProjectiles(projectiles: List<ItemStack>) =
        this.modifyMeta { it.setChargedProjectiles(projectiles) }

    override fun setChargedProjectileBuilders(projectiles: List<IItemBuilder<*>>) =
        this.setChargedProjectiles(projectiles.build())

    override fun addChargedProjectiles(projectiles: List<ItemStack>) = this.modifyMeta {
        projectiles.forEach { projectile -> it.addChargedProjectile(projectile) }
    }

    override fun addChargedProjectileBuilders(projectiles: List<IItemBuilder<*>>) =
        this.addChargedProjectiles(projectiles.build())

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is CrossbowMeta

    override fun getInstance() = this

}