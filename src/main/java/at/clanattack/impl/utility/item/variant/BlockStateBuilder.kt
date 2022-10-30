package at.clanattack.impl.utility.item.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.IBlockStateBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.block.BlockState
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BlockStateMeta
import org.bukkit.inventory.meta.ItemMeta

class BlockStateBuilder(itemStack: ItemStack, core: ICore) :
    ItemBuilder<IBlockStateBuilder, BlockStateMeta>(itemStack, core), IBlockStateBuilder {

    override val hasBlockState: Boolean
        get() = this.getMeta().hasBlockState()

    override val blockState: BlockState
        get() = this.getMeta().blockState

    override fun setBlockState(blockState: BlockState) = this.modifyMeta { it.blockState = blockState }

    override fun modifyBlockState(consumer: (BlockState) -> Unit) = this.modifyMeta {
        val blockState = it.blockState
        consumer(blockState)
        it.blockState = blockState
    }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is BlockStateMeta

    override fun getInstance() = this

}