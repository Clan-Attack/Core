package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.IFireworkBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.FireworkEffect
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.FireworkMeta
import org.bukkit.inventory.meta.ItemMeta

class FireworkBuilder(itemStack: ItemStack, core: ICore) : ItemBuilder<IFireworkBuilder, FireworkMeta>(itemStack, core),
    IFireworkBuilder {

    override val effectSize: Int
        get() = this.getMeta().effectsSize

    override val hasEffects: Boolean
        get() = this.getMeta().hasEffects()

    override val effects: List<FireworkEffect>
        get() = this.getMeta().effects

    override fun setEffects(effects: List<FireworkEffect>) = this.modifyMeta {
        it.clearEffects()
        it.addEffects(effects)
    }

    override fun addEffects(effects: List<FireworkEffect>) = this.modifyMeta { it.addEffects(effects) }

    override fun removeEffect(index: Int) = this.modifyMeta { it.removeEffect(index) }

    override fun clearEffects() = this.modifyMeta { it.clearEffects() }

    override val power: Int
        get() = this.getMeta().power

    override fun setPower(power: Int) = this.modifyMeta { it.power = power }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is FireworkMeta

    override fun getInstance() = this

}