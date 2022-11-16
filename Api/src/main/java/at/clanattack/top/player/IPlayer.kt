package at.clanattack.top.player

import at.clanattack.player.IPlayer
import at.clanattack.player.IPlayerServiceProvider
import at.clanattack.player.actionbar.ActionbarPriority
import at.clanattack.top.TopCore
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

val OfflinePlayer.iPlayer: IPlayer
    get() {
        if (!TopCore.core.getServiceProvider(IPlayerServiceProvider::class).existsPlayer(this.uniqueId))
            TopCore.core.getServiceProvider(IPlayerServiceProvider::class).createPlayer(this.uniqueId).getSync()

        return TopCore.core.getServiceProvider(IPlayerServiceProvider::class).getPlayer(this.uniqueId)
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

fun Player.sendKeyedMessage(key: String, vararg placeholders: String) = this.iPlayer.sendMessage(key, *placeholders)

fun Player.sendKeyedTitle(
    fadeIn: Number,
    stay: Number,
    fadeOut: Number,
    title: String?,
    subtitle: String?,
    vararg placeholders: String
) = this.iPlayer.sendTitle(fadeIn, stay, fadeOut, title, subtitle, *placeholders)
