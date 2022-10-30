package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.IFireworkEffectBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.FireworkEffect
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.FireworkEffectMeta
import org.bukkit.inventory.meta.ItemMeta

class FireworkEffectBuilder(itemStack: ItemStack, core: ICore) :
    ItemBuilder<IFireworkEffectBuilder, FireworkEffectMeta>(itemStack, core), IFireworkEffectBuilder {

    override val hasEffect: Boolean
        get() = this.getMeta().hasEffect()

    override val effect: FireworkEffect?
        get() = this.getMeta().effect

    override fun removeEffect() = this.modifyMeta { it.effect = null }

    override fun setEffect(effect: FireworkEffect) = this.modifyMeta { it.effect = effect }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is FireworkEffectMeta

    override fun getInstance() = this

}