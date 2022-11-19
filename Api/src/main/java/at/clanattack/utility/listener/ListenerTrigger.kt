package at.clanattack.utility.listener

import org.bukkit.event.Event
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ListenerTrigger(
    val event: KClass<out Event>,

    @Deprecated("Replace with @IncludeSubevents")
    @ScheduledForRemoval(inVersion = "0.7")
    val subevents: Boolean = false
)

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Priority(val priority: ListenerPriority)

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ExecuteCanceled

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class IncludeSubevents

