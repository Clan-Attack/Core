package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.IBannerBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.block.banner.Pattern
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BannerMeta
import org.bukkit.inventory.meta.ItemMeta

class BannerBuilder(itemStack: ItemStack, core: ICore) : ItemBuilder<IBannerBuilder, BannerMeta>(itemStack, core),
    IBannerBuilder {

    override val patterns: List<Pattern>
        get() = this.getMeta().patterns

    override val numberOfPatterns: Int
        get() = this.getMeta().numberOfPatterns()

    override fun getPattern(index: Int) = this.getMeta().getPattern(index)

    override fun setPatterns(patterns: List<Pattern>) = this.modifyMeta { it.patterns = patterns }

    override fun setPatterns(index: Int, pattern: Pattern) = this.modifyMeta { it.setPattern(index, pattern) }

    override fun addPattern(pattern: Pattern) = this.modifyMeta { it.addPattern(pattern) }

    override fun addPatterns(patterns: List<Pattern>) = this.modifyMeta {
        patterns.forEach { pattern -> it.addPattern(pattern) }
    }

    override fun removePattern(index: Int) = this.modifyMeta { it.removePattern(index) }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is BannerMeta

    override fun getInstance() = this

}