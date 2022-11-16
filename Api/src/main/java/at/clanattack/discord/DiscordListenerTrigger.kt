package at.clanattack.discord

import net.dv8tion.jda.api.events.GenericEvent
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Deprecated("Replaced by the JDA-Reactor event handler")
@ScheduledForRemoval(inVersion = "0.4")
annotation class DiscordListenerTrigger(val event: KClass<out GenericEvent>, val subevents: Boolean = false)
