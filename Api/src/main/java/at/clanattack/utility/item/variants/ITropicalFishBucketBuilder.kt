package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.DyeColor
import org.bukkit.entity.TropicalFish.Pattern

interface ITropicalFishBucketBuilder: IItemBuilder<ITropicalFishBucketBuilder> {

    val hasVariant: Boolean

    val patternColor: DyeColor
    fun setPatternColor(color: DyeColor): ITropicalFishBucketBuilder

    val bodyColor: DyeColor
    fun setBodyColor(color: DyeColor): ITropicalFishBucketBuilder

    val pattern: Pattern
    fun setPattern(pattern: Pattern): ITropicalFishBucketBuilder

}