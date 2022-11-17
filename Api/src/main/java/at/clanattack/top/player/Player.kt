package at.clanattack.top.player

import at.clanattack.player.IPlayerServiceProvider
import at.clanattack.top.bootstrap.getServiceProvider
import org.bukkit.entity.Player
import java.util.*

private val playerProvider: IPlayerServiceProvider
    get() = getServiceProvider()

fun existsPlayer(uuid: UUID) = playerProvider.existsPlayer(uuid)

fun existsPlayer(name: String) = playerProvider.existsPlayer(name)

fun getPlayerAsync(uuid: UUID) = playerProvider.getPlayerAsync(uuid)

fun getPlayer(uuid: UUID) = playerProvider.getPlayer(uuid)

fun getPlayerAsync(name: String) = playerProvider.getPlayerAsync(name)

fun getPlayer(name: String) = playerProvider.getPlayer(name)

fun getPlayerAsync(player: Player) = playerProvider.getPlayerAsync(player)

fun getPlayer(player: Player) = playerProvider.getPlayer(player)
