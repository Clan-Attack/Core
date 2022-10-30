package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

interface ISuspiciousStewBuilder : IItemBuilder<ISuspiciousStewBuilder> {

    val hasCustomEffects: Boolean
    val customEffects: List<PotionEffect>
    fun hasCustomEffect(type: PotionEffectType): Boolean
    fun clearCustomEffects(): ISuspiciousStewBuilder

    fun setCustomEffect(effect: PotionEffect): ISuspiciousStewBuilder
    fun setCustomEffects(effects: List<PotionEffect>): ISuspiciousStewBuilder
    fun setCustomEffects(vararg effects: PotionEffect) = setCustomEffects(effects.toList())

    fun addCustomEffect(effect: PotionEffect, overwrite: Boolean = false): ISuspiciousStewBuilder
    fun addCustomEffects(effects: List<PotionEffect>, overwrite: Boolean = false): ISuspiciousStewBuilder
    fun addCustomEffects(vararg effects: PotionEffect, overwrite: Boolean = false) =
        addCustomEffects(effects.toList(), overwrite)

    fun removeCustomEffect(type: PotionEffectType): ISuspiciousStewBuilder
    fun removeCustomEffects(types: List<PotionEffectType>): ISuspiciousStewBuilder
    fun removeCustomEffects(vararg types: PotionEffectType) = removeCustomEffects(types.toList())

}