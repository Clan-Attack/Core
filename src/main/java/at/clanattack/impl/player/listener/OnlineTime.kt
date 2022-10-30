package at.clanattack.impl.player.listener

import at.clanattack.bootstrap.ICore
import at.clanattack.database.ISurrealServiceProvider
import at.clanattack.database.query
import at.clanattack.utility.listener.ListenerTrigger
import at.clanattack.impl.player.Player
import at.clanattack.impl.player.model.DBPlayer
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class OnlineTime(private val core: ICore) {

    @ListenerTrigger(PlayerJoinEvent::class)
    fun playerJoin(event: PlayerJoinEvent) {
        Player.joinTime[event.player.uniqueId] = System.currentTimeMillis()
    }

    @ListenerTrigger(PlayerQuitEvent::class)
    fun playerQuiet(event: PlayerQuitEvent) {
        val joinTime = Player.joinTime[event.player.uniqueId]!!
        val newTime = System.currentTimeMillis() - joinTime
        this.core.getServiceProvider(ISurrealServiceProvider::class)
            .query<DBPlayer>("update player:`${event.player.uniqueId}` set onlineTime+=$newTime")
    }

}