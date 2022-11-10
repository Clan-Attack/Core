package at.clanattack.impl.utility.listener

import at.clanattack.bootstrap.ICore
import at.clanattack.message.IMessageServiceProvider
import at.clanattack.xjkl.wait.Lock
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent

class PlayerLoginListener(private val lock: Lock, private val core: ICore) : Listener {

    @EventHandler
    fun playerJoin(event: PlayerLoginEvent) {
        if (lock.isFinished) return

        event.disallow(
            PlayerLoginEvent.Result.KICK_OTHER,
            this.core.getServiceProvider(IMessageServiceProvider::class).getMessage("core.utility.notLoaded.kick")
        )
    }

}