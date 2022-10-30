package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.IPotionBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.Color
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionData
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class PotionBuilder(itemStack: ItemStack, core: ICore) : ItemBuilder<IPotionBuilder, PotionMeta>(itemStack, core),
    IPotionBuilder {

    override val basePotionData: PotionData
        get() = this.getMeta().basePotionData

    override fun setBasePotionData(basePotionData: PotionData) = this.modifyMeta { it.basePotionData = basePotionData }

    override val hasCustomEffects: Boolean
        get() = this.getMeta().hasCustomEffects()

    override val customEffects: List<PotionEffect>
        get() = this.getMeta().customEffects

    override fun hasCustomEffect(type: PotionEffectType) = this.getMeta().hasCustomEffect(type)

    override fun clearCustomEffects() = this.modifyMeta { it.clearCustomEffects() }

    override fun setCustomEffect(effect: PotionEffect): IPotionBuilder {
        this.clearCustomEffects()
        this.addCustomEffect(effect)
        return this
    }

    override fun setCustomEffects(effects: List<PotionEffect>): IPotionBuilder {
        this.clearCustomEffects()
        this.addCustomEffects(effects)
        return this
    }

    override fun addCustomEffect(effect: PotionEffect, overwrite: Boolean) =
        this.modifyMeta { it.addCustomEffect(effect, overwrite) }

    override fun addCustomEffects(effects: List<PotionEffect>, overwrite: Boolean) =
        this.modifyMeta { effects.forEach { effect -> it.addCustomEffect(effect, overwrite) } }

    override fun removeCustomEffect(type: PotionEffectType) = this.modifyMeta { it.removeCustomEffect(type) }

    override fun removeCustomEffects(types: List<PotionEffectType>) =
        this.modifyMeta { types.forEach { type -> it.removeCustomEffect(type) } }

    override val hasColor: Boolean
        get() = this.getMeta().hasColor()

    override val color: Color?
        get() = this.getMeta().color

    override fun setColor(color: Color) = this.modifyMeta { it.color = color }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is PotionMeta

    override fun getInstance() = this

}