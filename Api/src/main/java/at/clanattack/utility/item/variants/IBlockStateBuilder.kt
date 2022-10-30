package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder
import org.bukkit.block.BlockState

interface IBlockStateBuilder: IItemBuilder<IBlockStateBuilder> {

    val hasBlockState: Boolean
    val blockState: BlockState
    fun setBlockState(blockState: BlockState): IBlockStateBuilder
    fun modifyBlockState(consumer: (BlockState) -> Unit): IBlockStateBuilder

}