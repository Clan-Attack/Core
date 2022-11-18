package at.clanattack.impl.bootstrap

import at.clanattack.impl.bootstrap.provider.ServiceProviderRegistry
import at.clanattack.impl.bootstrap.util.annotation.AnnotationScanner
import at.clanattack.impl.bootstrap.util.log.Logger
import at.clanattack.bootstrap.ICore
import at.clanattack.bootstrap.call.SystemState
import at.clanattack.bootstrap.plugin.Plugin
import at.clanattack.bootstrap.util.log.ILogger
import at.clanattack.impl.bootstrap.call.CallHandler
import at.clanattack.top.TopCore
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Core(override val javaPlugin: JavaPlugin) : ICore {

    private var waitFor: Int = 0

    private var loaded: Boolean = false
    private var waited: Int = 0
    private val plugins = mutableListOf<Plugin>()
    private val callHandler = CallHandler(this)

    override val annotationScanner = AnnotationScanner()
    override val serviceProviderRegistry = ServiceProviderRegistry(this)
    override val logger: ILogger = Logger(this)
    override val dataFolder: File = this.javaPlugin.dataFolder

    init {
        TopCore.internalCore = this
    }

    fun load() {
        this.annotationScanner.addLoader(this.javaPlugin::class.java.classLoader)
        this.waitFor = Bukkit.getPluginManager().plugins
            .map { it.description }
            .map { it.depend.union(it.softDepend) }
            .count { "ClanAttack-Core" in it }
    }

    fun enable() {
        this.callHandler.registerMethods()
        this.loadProviders(true)
        this.serviceProviderRegistry.enableProviders()
        this.callHandler.call(SystemState.ENABLED)
    }

    fun disable() {
        this.callHandler.call(SystemState.BEFORE_DISABLE)
        this.serviceProviderRegistry.disableProviders()
        this.callHandler.call(SystemState.DISABLED)
    }

    private fun loadProviders(shouldBeCalled: Boolean) {
        if (this.loaded) return
        if (!shouldBeCalled) this.logger.error("Not all plugins, witch have the Core defined as dependency where loaded!")

        this.callHandler.call(SystemState.BEFORE_LOAD)
        this.serviceProviderRegistry.registerProviders()
        plugins.forEach { it.load() }
        this.callHandler.call(SystemState.LOADED)
    }

    override fun ICore.registerPlugin(loader: ClassLoader, plugin: Plugin) {
        this@Core.annotationScanner.addLoader(loader)
        this@Core.plugins.add(plugin)
        this@Core.waited++

        if (this@Core.waited == this@Core.waitFor) this@Core.loadProviders(false)
    }


}