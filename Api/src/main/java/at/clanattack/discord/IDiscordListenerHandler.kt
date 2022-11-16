package at.clanattack.discord

import org.jetbrains.annotations.ApiStatus

@Deprecated("Replaced by the JDA-Reactor event handler")
@ApiStatus.ScheduledForRemoval(inVersion = "0.4")
interface IDiscordListenerHandler {

    fun registerListenerInstance(instance: Any)

}