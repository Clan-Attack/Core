package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.IAxolotlBucketBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.entity.Axolotl
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.AxolotlBucketMeta
import org.bukkit.inventory.meta.ItemMeta

class AxolotlBucketBuilder(itemStack: ItemStack, core: ICore) :
    ItemBuilder<IAxolotlBucketBuilder, AxolotlBucketMeta>(itemStack, core), IAxolotlBucketBuilder {

    override val hasVariant: Boolean
        get() = this.getMeta().hasVariant()

    override val variant: Axolotl.Variant
        get() = this.getMeta().variant

    override fun setVariant(variant: Axolotl.Variant) = this.modifyMeta { it.variant = variant }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is AxolotlBucketMeta

    override fun getInstance() = this

}