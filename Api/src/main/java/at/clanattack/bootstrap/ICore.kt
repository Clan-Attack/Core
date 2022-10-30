package at.clanattack.bootstrap

import at.clanattack.bootstrap.plugin.Plugin
import at.clanattack.bootstrap.provider.IServiceProvider
import at.clanattack.bootstrap.provider.IServiceProviderRegistry
import at.clanattack.bootstrap.util.annotation.IAnnotationScanner
import at.clanattack.bootstrap.util.log.ILogger
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.annotations.Contract
import java.io.File
import kotlin.reflect.KClass

@Suppress("unused")
interface ICore {

    val annotationScanner: IAnnotationScanner
        @Contract(pure = true)
        get

    val serviceProviderRegistry: IServiceProviderRegistry
        @Contract(pure = true)
        get

    val logger: ILogger
        @Contract(pure = true)
        get

    val javaPlugin: JavaPlugin
        @Contract(pure = true)
        get

    val dataFolder: File
        @Contract(pure = true)
        get

    @Contract(pure = true)
    fun <T : IServiceProvider> getServiceProvider(`class`: Class<T>): T = serviceProviderRegistry[`class`]

    @Contract(pure = true)
    fun <T : IServiceProvider> getServiceProvider(`class`: KClass<T>): T = serviceProviderRegistry[`class`]

    fun ICore.registerPlugin(loader: ClassLoader, plugin: Plugin)

}
