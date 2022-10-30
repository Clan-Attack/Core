package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.Color
import org.bukkit.potion.PotionData
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

interface IPotionBuilder : IItemBuilder<IPotionBuilder> {

    val basePotionData: PotionData
    fun setBasePotionData(basePotionData: PotionData): IPotionBuilder

    val hasCustomEffects: Boolean
    val customEffects: List<PotionEffect>
    fun hasCustomEffect(type: PotionEffectType): Boolean
    fun clearCustomEffects(): IPotionBuilder

    fun setCustomEffect(effect: PotionEffect): IPotionBuilder
    fun setCustomEffects(effects: List<PotionEffect>): IPotionBuilder
    fun setCustomEffects(vararg effects: PotionEffect) = setCustomEffects(effects.toList())

    fun addCustomEffect(effect: PotionEffect, overwrite: Boolean = false): IPotionBuilder
    fun addCustomEffects(effects: List<PotionEffect>, overwrite: Boolean = false): IPotionBuilder
    fun addCustomEffects(vararg effects: PotionEffect, overwrite: Boolean = false) =
        addCustomEffects(effects.toList(), overwrite)

    fun removeCustomEffect(type: PotionEffectType): IPotionBuilder
    fun removeCustomEffects(types: List<PotionEffectType>): IPotionBuilder
    fun removeCustomEffects(vararg types: PotionEffectType) = removeCustomEffects(types.toList())

    val hasColor: Boolean
    val color: Color?
    fun setColor(color: Color): IPotionBuilder

}