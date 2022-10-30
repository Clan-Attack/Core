package at.clanattack.impl.discord

import at.clanattack.bootstrap.ICore
import at.clanattack.discord.DiscordListenerTrigger
import at.clanattack.discord.IDiscordListenerHandler
import at.clanattack.utility.scope.async
import at.clanattack.xjkl.scope.empty
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.hooks.IEventManager
import java.lang.reflect.Method

class DiscordListenerHandler(private val core: ICore) : IEventManager, IDiscordListenerHandler {

    private val instances = mutableMapOf<Class<*>, Any>()
    private val listeners = mutableMapOf<Class<out GenericEvent>, MutableList<Pair<Method, Boolean>>>()

    fun loadListeners() {
        async {
            this.core.logger.info("Loading discord listeners...")
            this.core.annotationScanner.scanMethods(DiscordListenerTrigger::class)
                .forEach {
                    val annotation = it.getAnnotation(DiscordListenerTrigger::class.java)

                    val declaringClass = it.declaringClass
                    if (it.parameterCount != 1 || it.parameterTypes[0] != annotation.event.java) {
                        this.core.logger.error(
                            "Couldn't register discord listener for " + annotation.event.simpleName + " in "
                                    + declaringClass.simpleName + " because the method doesn't have the right parameters."
                        )
                    }

                    if (declaringClass in this.instances) {
                        this.listeners.putIfAbsent(annotation.event.java, mutableListOf())
                        this.listeners[annotation.event.java]!!.add(it to annotation.subevents)
                        return@forEach
                    }

                    val instance = try {
                        declaringClass.getDeclaredConstructor(ICore::class.java).newInstance(this.core)
                    } catch (e: NoSuchMethodException) {
                        try {
                            declaringClass.getDeclaredConstructor().newInstance()
                        } catch (ex: NoSuchMethodException) {
                            this.core.logger.error(
                                "Couldn't register discord listener for " + annotation.event.simpleName + " in "
                                        + declaringClass.simpleName + " because the class doesn't have the correct constructor."
                            )
                            null
                        }
                    } ?: return@forEach

                    this.instances[declaringClass] = instance

                    this.listeners.putIfAbsent(annotation.event.java, mutableListOf())
                    this.listeners[annotation.event.java]!!.add(it to annotation.subevents)
                }

            this.core.logger.info("Loaded ${listeners.size} discord listeners.")
        }
    }

    override fun register(listener: Any) {
        this.registerListenerInstance(listener)
    }

    override fun unregister(listener: Any) = empty()

    override fun handle(event: GenericEvent) {
        val eventClass = event::class.java

        this.listeners
            .filter { (`class`, _) -> `class`.isAssignableFrom(eventClass) }
            .map { it.value.filter { (_, subevents) -> this.shouldCall(eventClass, it.key, subevents) } }
            .flatten()
            .map { it.first }
            .forEach {
                val instance = this.instances[it.declaringClass] ?: return@forEach
                it.invoke(instance, event)
            }
    }

    private fun shouldCall(event: Class<out GenericEvent>, methodEvent: Class<out GenericEvent>, subevents: Boolean): Boolean {
        if (event == methodEvent) return true
        return methodEvent.isAssignableFrom(event) && subevents
    }

    override fun getRegisteredListeners() = this.listeners.values.toMutableList()

    override fun registerListenerInstance(instance: Any) {
        this.instances[instance::class.java] = instance
    }

}