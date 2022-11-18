package at.clanattack.impl.utility.listener

import at.clanattack.bootstrap.ICore
import at.clanattack.impl.bootstrap.registry.Registry
import at.clanattack.impl.bootstrap.util.annotation.AnnotationScanner
import at.clanattack.utility.IUtilityServiceProvider
import at.clanattack.utility.listener.IListenerHandler
import at.clanattack.utility.listener.ListenerTrigger
import at.clanattack.xjkl.wait.Lock
import io.github.classgraph.ClassGraph
import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.plugin.EventExecutor
import java.lang.reflect.Method

class ListenerHandler(private val core: ICore) : Registry<Any>(ICore::class.java, { core }), IListenerHandler {
    
    private val listeners = mutableMapOf<Class<out Event>, MutableList<Pair<Method, Boolean>>>()

    private var loadLock = Lock()
    private var registerLock = Lock()

    fun registerBlock() {
        Bukkit.getPluginManager().registerEvents(PlayerLoginListener(registerLock, this.core), this.core.javaPlugin)
    }

    fun loadListeners() {
        this.core.getServiceProvider(IUtilityServiceProvider::class).scopeHandler.async {
            core.logger.info("Loading listeners...")
            core.annotationScanner.scanMethods(ListenerTrigger::class)
                .forEach {
                    val annotation = it.getAnnotation(ListenerTrigger::class.java)

                    val declaringClass = it.declaringClass
                    if (it.parameterCount != 1 || it.parameterTypes[0] != annotation.event.java) {
                        core.logger.error(
                            "Couldn't register listener for " + annotation.event.simpleName + " in "
                                    + declaringClass.simpleName + " because the method doesn't have the right parameters."
                        )
                    }

                    createInstanceOrKeep(declaringClass)

                    listeners.putIfAbsent(annotation.event.java, mutableListOf())
                    listeners[annotation.event.java]!!.add(it to annotation.subevents)
                }
            core.logger.info("Loaded ${listeners.size} listeners.")

            loadLock.signal()
        }

    }

    fun registerEvents() {
        this.core.getServiceProvider(IUtilityServiceProvider::class).scopeHandler.async {
            loadLock.await()

            core.logger.info("Registering events...")

            val listener: org.bukkit.event.Listener = object : org.bukkit.event.Listener {}
            val executor = EventExecutor { _, event -> fireEvent(event) }
            var counter = 0

            (core.annotationScanner as AnnotationScanner).loaders.map {
                ClassGraph()
                    .overrideClassLoaders(it)
                    .scan()
                    .getClassInfo(Event::class.java.name)
                    .subclasses
            }
                .flatten()
                .asSequence()
                .filter { !it.isAbstract }
                .forEach {
                    val eventClass = it.loadClass().asSubclass(Event::class.java)
                    if (!eventClass.declaredMethods
                            .any { method -> method.parameterCount == 0 && method.name == "getHandlers" } ||
                        listeners.none { (`class`, events) ->
                            shouldRegister(
                                eventClass,
                                events.map { (_, subevents) -> `class` to subevents })
                        }
                    ) return@forEach

                    Bukkit.getPluginManager().registerEvent(
                        eventClass,
                        listener,
                        EventPriority.NORMAL,
                        executor,
                        core.javaPlugin
                    )

                    counter++
                }

            core.logger.info("Registered $counter Events.")

            registerLock.signal()
        }
    }

    private fun fireEvent(event: Event) {
        val eventClass = event::class.java

        this.listeners
            .filter { (`class`, _) -> `class`.isAssignableFrom(eventClass) }
            .map { it.value.filter { (_, subevents) -> this.shouldCall(eventClass, it.key, subevents) } }
            .flatten()
            .map { it.first }
            .forEach {
                val instance = this.getInstance(it.declaringClass) ?: return@forEach
                it.invoke(instance, event)
            }
    }

    @Suppress("BooleanMethodIsAlwaysInverted")
    private fun shouldCall(event: Class<out Event>, methodEvent: Class<out Event>, subevents: Boolean): Boolean {
        if (event == methodEvent) return true
        return methodEvent.isAssignableFrom(event) && subevents
    }

    private fun shouldRegister(event: Class<out Event>, events: List<Pair<Class<out Event>, Boolean>>) =
        events.any { (methodEvent, subevents) -> shouldCall(event, methodEvent, subevents) }

    override fun registerListenerInstance(instance: Any) = this.registerInstance(instance)

}