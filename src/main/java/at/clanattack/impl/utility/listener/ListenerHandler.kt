package at.clanattack.impl.utility.listener

import at.clanattack.bootstrap.ICore
import at.clanattack.impl.bootstrap.registry.Registry
import at.clanattack.impl.bootstrap.util.annotation.AnnotationScanner
import at.clanattack.utility.IUtilityServiceProvider
import at.clanattack.utility.listener.IListenerHandler
import at.clanattack.utility.listener.ListenerTrigger
import at.clanattack.xjkl.extention.supplyNullable
import at.clanattack.xjkl.wait.Lock
import io.github.classgraph.ClassGraph
import org.bukkit.Bukkit
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.plugin.EventExecutor

class ListenerHandler(private val core: ICore) : Registry(), IListenerHandler {

    private val listeners = mutableMapOf<Class<out Event>, MutableList<ListenerData>>()

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
                    listeners[annotation.event.java]!!.add(ListenerData.populateData(it))
                }
            core.logger.info("Loaded ${listeners.map { it.value.size }.sum()} listeners.")

            loadLock.signal()
        }

    }

    fun registerEvents() {
        this.core.getServiceProvider(IUtilityServiceProvider::class).scopeHandler.async {
            loadLock.await()

            val sortedListeners =
                listeners.map { (event, data) -> event to data.sortedBy { it.priority }.toMutableList() }.toMap()
            listeners.clear()
            sortedListeners.forEach { (event, data) -> listeners[event] = data }

            core.logger.info("Registering events...")

            val listener: org.bukkit.event.Listener = object : org.bukkit.event.Listener {}
            val executor = EventExecutor { _, event -> fireEvent(event) }
            var counter = 0

            (core.annotationScanner as AnnotationScanner).loaders.mapNotNull {
                ClassGraph()
                    .overrideClassLoaders(it)
                    .enableClassInfo()
                    .scan()
                    .getClassInfo(Event::class.java.name)
                    .supplyNullable { classInfo -> classInfo.subclasses }
            }
                .flatten()
                .asSequence()
                .filter { !it.isAbstract }
                .map { it.loadClass().asSubclass(Event::class.java) }
                .filter { it.methods.any { method -> method.parameterCount == 0 && method.name == "getHandlers" } }
                .filter { shouldRegister(it) }
                .distinct()
                .forEach {
                    Bukkit.getPluginManager().registerEvent(
                        it,
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

        val toBeCalled = this.listeners
            .filter { (`class`, _) -> `class`.isAssignableFrom(eventClass) }
            .map { (_, list) -> list.filter { shouldCall(eventClass, it) } }
            .flatten()

        for (listenerData in toBeCalled) {
            if (!shouldCall(event, listenerData)) continue

            val instance = this.getInstance(listenerData.method.declaringClass) ?: continue
            listenerData.method.invoke(instance, event)
        }
    }

    @Suppress("BooleanMethodIsAlwaysInverted")
    private fun shouldCall(event: Event, data: ListenerData): Boolean {
        if (!shouldCall(event::class.java, data)) return false
        if (event is Cancellable && (event as Cancellable).isCancelled && !data.executeCanceled) return false

        return true
    }

    @Suppress("BooleanMethodIsAlwaysInverted")
    private fun shouldCall(event: Class<out Event>, data: ListenerData): Boolean {
        val handlerClass = event.getMethod("getHandlerList").declaringClass

        if (!data.event.isAssignableFrom(event)) return false
        if (data.handlerClass == handlerClass) return true

        return data.includeSubevents
    }

    private fun shouldRegister(event: Class<out Event>) =
        listeners.any { (_, list) -> list.any { shouldCall(event, it) } }

    override fun registerListenerInstance(instance: Any) = this.registerInstance(instance)

}