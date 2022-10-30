package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.IBlockDataBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.Material
import org.bukkit.block.data.BlockData
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BlockDataMeta
import org.bukkit.inventory.meta.ItemMeta

class BlockDataBuilder(itemStack: ItemStack, core: ICore) :
    ItemBuilder<IBlockDataBuilder, BlockDataMeta>(itemStack, core), IBlockDataBuilder {

    override val hasBlockData: Boolean
        get() = this.getMeta().hasBlockData()

    override fun getBlockData(material: Material) = this.getMeta().getBlockData(material)

    override fun setBlockData(blockData: BlockData) = this.modifyMeta { it.setBlockData(blockData) }

    override fun modifyBlockData(material: Material, consumer: (BlockData) -> Unit) = this.modifyMeta {
        val blockData = it.getBlockData(material)
        consumer(blockData)
        it.setBlockData(blockData)
    }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is BlockDataMeta

    override fun getInstance() = this

}