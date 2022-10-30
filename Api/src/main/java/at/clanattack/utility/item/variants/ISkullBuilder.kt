package at.clanattack.utility.item.variants

import com.destroystokyo.paper.profile.PlayerProfile
import at.clanattack.utility.item.IItemBuilder
import org.bukkit.OfflinePlayer

interface ISkullBuilder : IItemBuilder<ISkullBuilder> {

    val hasOwner: Boolean
    val playerProfile: PlayerProfile?
    val owningPlayer: OfflinePlayer?
    fun setPlayerProfile(playerProfile: PlayerProfile): ISkullBuilder
    fun setOwningPlayer(owner: OfflinePlayer): ISkullBuilder

}