package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.entity.Axolotl.Variant

interface IAxolotlBucketBuilder : IItemBuilder<IAxolotlBucketBuilder> {

    val hasVariant: Boolean
    val variant: Variant
    fun setVariant(variant: Variant): IAxolotlBucketBuilder

}