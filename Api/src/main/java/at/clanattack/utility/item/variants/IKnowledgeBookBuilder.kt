package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.NamespacedKey

interface IKnowledgeBookBuilder : IItemBuilder<IKnowledgeBookBuilder> {

    val hasRecipes: Boolean
    val recipes: List<NamespacedKey>

    fun setRecipes(recipes: List<NamespacedKey>): IKnowledgeBookBuilder
    fun setRecipes(vararg recipes: NamespacedKey) = setRecipes(recipes.toList())

    fun addRecipes(vararg recipes: NamespacedKey): IKnowledgeBookBuilder
    fun addRecipes(recipes: List<NamespacedKey>) = addRecipes(*recipes.toTypedArray())

}