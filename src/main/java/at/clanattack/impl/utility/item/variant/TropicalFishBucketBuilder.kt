package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.ITropicalFishBucketBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.DyeColor
import org.bukkit.entity.TropicalFish
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.TropicalFishBucketMeta

class TropicalFishBucketBuilder(itemStack: ItemStack, core: ICore) :
    ItemBuilder<ITropicalFishBucketBuilder, TropicalFishBucketMeta>(itemStack, core), ITropicalFishBucketBuilder {

    override val hasVariant: Boolean
        get() = this.getMeta().hasVariant()

    override val patternColor: DyeColor
        get() = this.getMeta().patternColor

    override fun setPatternColor(color: DyeColor) = this.modifyMeta { it.patternColor = color }

    override val bodyColor: DyeColor
        get() = this.getMeta().bodyColor

    override fun setBodyColor(color: DyeColor) = this.modifyMeta { it.bodyColor = color }

    override val pattern: TropicalFish.Pattern
        get() = this.getMeta().pattern

    override fun setPattern(pattern: TropicalFish.Pattern) = this.modifyMeta { it.pattern = pattern }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is TropicalFishBucketMeta

    override fun getInstance() = this

}