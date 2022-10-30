package at.clanattack.impl.bootstrap

import at.clanattack.impl.bootstrap.provider.ServiceProviderRegistry
import at.clanattack.impl.bootstrap.util.annotation.AnnotationScanner
import at.clanattack.impl.bootstrap.util.log.Logger
import at.clanattack.bootstrap.ICore
import at.clanattack.bootstrap.util.log.ILogger
import at.clanattack.top.TopCore
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Core(override val javaPlugin: JavaPlugin) : ICore {

    override val annotationScanner = AnnotationScanner()
    override val serviceProviderRegistry = ServiceProviderRegistry(this)
    override val logger: ILogger = Logger(this)
    override val dataFolder: File = this.javaPlugin.dataFolder

    init {
        TopCore.internalCore = this
    }

    fun load() = this.serviceProviderRegistry.registerProviders()
    fun enable() = this.serviceProviderRegistry.enableProviders()
    fun disable() = this.serviceProviderRegistry.disableProviders()


}