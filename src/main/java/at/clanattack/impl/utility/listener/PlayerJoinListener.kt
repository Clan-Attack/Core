package at.clanattack.impl.utility.listener

import at.clanattack.bootstrap.ICore
import at.clanattack.message.IMessageServiceProvider
import at.clanattack.xjkl.wait.Wait
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener(private val wait: Wait, private val core: ICore) : Listener {

    @EventHandler
    fun playerJoin(event: PlayerJoinEvent) {
        if(wait.isFinished) return

        event.player.kick(this.core.getServiceProvider(IMessageServiceProvider::class)
            .getMessage("core.utility.notLoaded.kick"))
    }

}