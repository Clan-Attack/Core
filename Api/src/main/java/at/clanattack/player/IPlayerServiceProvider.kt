package at.clanattack.player

import at.clanattack.bootstrap.provider.IServiceProvider
import at.clanattack.xjkl.future.Future
import org.bukkit.entity.Player
import java.util.UUID

interface IPlayerServiceProvider : IServiceProvider {

    fun existsPlayer(uuid: UUID): Boolean

    fun existsPlayer(name: String): Boolean

    fun createPlayer(uuid: UUID): Future<Unit>

    fun getPlayerAsync(uuid: UUID): Future<IPlayer?>

    fun getPlayer(uuid: UUID) = getPlayerAsync(uuid).getSync()

    fun getPlayerAsync(name: String): Future<IPlayer?>

    fun getPlayer(name: String) = getPlayerAsync(name).getSync()

    fun getPlayerAsync(player: Player) = this.getPlayerAsync(player.uniqueId)

    fun getPlayer(player: Player) = getPlayerAsync(player).getSync()

}