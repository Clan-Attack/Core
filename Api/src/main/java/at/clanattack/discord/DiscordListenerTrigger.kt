package at.clanattack.discord

import net.dv8tion.jda.api.events.GenericEvent
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DiscordListenerTrigger(val event: KClass<out GenericEvent>, val subevents: Boolean = false)
