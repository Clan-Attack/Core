package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.IKnowledgeBookBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.KnowledgeBookMeta

class KnowledgeBookBuilder(itemStack: ItemStack, core: ICore) :
    ItemBuilder<IKnowledgeBookBuilder, KnowledgeBookMeta>(itemStack, core), IKnowledgeBookBuilder {

    override val hasRecipes: Boolean
        get() = this.getMeta().hasRecipes()

    override val recipes: List<NamespacedKey>
        get() = this.getMeta().recipes

    override fun setRecipes(recipes: List<NamespacedKey>) = this.modifyMeta { it.recipes = recipes }

    override fun addRecipes(vararg recipes: NamespacedKey) = this.modifyMeta { it.addRecipe(*recipes) }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is KnowledgeBookMeta

    override fun getInstance() = this

}