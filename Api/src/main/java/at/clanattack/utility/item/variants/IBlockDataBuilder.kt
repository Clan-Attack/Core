package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.Material
import org.bukkit.block.data.BlockData

interface IBlockDataBuilder: IItemBuilder<IBlockDataBuilder> {

    val hasBlockData: Boolean

    fun getBlockData(material: Material): BlockData
    fun setBlockData(blockData: BlockData): IBlockDataBuilder
    fun modifyBlockData(material: Material, consumer: (BlockData) -> Unit): IBlockDataBuilder

}