package at.clanattack.impl.utility.listener

import at.clanattack.utility.listener.*
import org.bukkit.event.Event
import java.lang.reflect.Method

data class ListenerData(
    val event: Class<out Event>,
    val priority: ListenerPriority,
    val executeCanceled: Boolean,
    val includeSubevents: Boolean,
    val method: Method
) {
    companion object {

        @Suppress("DEPRECATION")
        fun populateData(method: Method) = ListenerData(
            method.getDeclaredAnnotation(ListenerTrigger::class.java).event.java,
            if (method.isAnnotationPresent(Priority::class.java))
                method.getDeclaredAnnotation(Priority::class.java).priority else ListenerPriority.NORMAL,
            method.isAnnotationPresent(ExecuteCanceled::class.java),
            method.isAnnotationPresent(IncludeSubevents::class.java) ||
                    method.getDeclaredAnnotation(ListenerTrigger::class.java).subevents,
            method
        )

    }


}
