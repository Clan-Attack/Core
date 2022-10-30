package at.clanattack.impl.utility.item.variant

import com.destroystokyo.paper.profile.PlayerProfile
import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.variants.ISkullBuilder
import at.clanattack.impl.utility.item.ItemBuilder
import org.bukkit.OfflinePlayer
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.SkullMeta

class SkullBuilder(itemStack: ItemStack, core: ICore) : ItemBuilder<ISkullBuilder, SkullMeta>(itemStack, core),
    ISkullBuilder {

    override val hasOwner: Boolean
        get() = this.getMeta().hasOwner()

    override val playerProfile: PlayerProfile?
        get() = this.getMeta().playerProfile

    override val owningPlayer: OfflinePlayer?
        get() = this.getMeta().owningPlayer

    override fun setPlayerProfile(playerProfile: PlayerProfile) = this.modifyMeta { it.playerProfile = playerProfile }

    override fun setOwningPlayer(owner: OfflinePlayer) = this.modifyMeta { it.owningPlayer = owningPlayer }

    override fun checkMeta(itemMeta: ItemMeta) = itemMeta is SkullMeta

    override fun getInstance() = this

}