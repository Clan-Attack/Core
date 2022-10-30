package at.clanattack.player.extention

import at.clanattack.player.IPlayer
import at.clanattack.player.actionbar.ActionbarPriority
import org.bukkit.OfflinePlayer

val OfflinePlayer.iPlayer: IPlayer
    get() {
        if (!ExtensionPlayerProvider.existsPlayer(this.uniqueId))
            ExtensionPlayerProvider.createPlayer(this.uniqueId).getSync()

        return ExtensionPlayerProvider.getPlayer(this.uniqueId)
            ?: throw IllegalStateException("After creation of player the player should exist")
    }

val OfflinePlayer.onlineTime: Long
    get() = this.iPlayer.onlineTime

val OfflinePlayer.playerData: Map<String, String?>
    get() = this.iPlayer.playerData

fun OfflinePlayer.sendActionbar(priority: ActionbarPriority, stay: Int, key: String, vararg placeholders: String) =
    this.iPlayer.sendActionbar(priority, stay, key, *placeholders)

fun OfflinePlayer.sendActionbar(priority: ActionbarPriority, key: String, vararg placeholders: String) =
    this.iPlayer.sendActionbar(priority, key, *placeholders)

inline fun <reified T : Any> OfflinePlayer.getPlayerData(key: String) = this.iPlayer.getPlayerData(key, T::class)

inline fun <reified T : Any> OfflinePlayer.getPlayerData(key: String, default: T) =
    this.iPlayer.getPlayerData(key, default, T::class)

fun OfflinePlayer.setPlayerData(key: String, value: Any) = this.iPlayer.setPlayerData(key, value)

fun OfflinePlayer.hasPlayerData(key: String) = this.iPlayer.hasPlayerData(key)

fun OfflinePlayer.removePlayerData(key: String) = this.iPlayer.removePlayerData(key)