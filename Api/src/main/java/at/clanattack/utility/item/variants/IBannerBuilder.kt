package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.block.banner.Pattern

@Suppress("unused")
interface IBannerBuilder : IItemBuilder<IBannerBuilder> {

    val patterns: List<Pattern>
    val numberOfPatterns: Int

    fun getPattern(index: Int): Pattern

    fun setPatterns(patterns: List<Pattern>): IBannerBuilder
    fun setPatterns(vararg patterns: Pattern) = setPatterns(patterns.toList())
    fun setPatterns(index: Int, pattern: Pattern): IBannerBuilder

    fun addPattern(pattern: Pattern): IBannerBuilder
    fun addPatterns(patterns: List<Pattern>): IBannerBuilder
    fun addPatterns(vararg patterns: Pattern) = addPatterns(patterns.toList())

    fun removePattern(index: Int): IBannerBuilder

}