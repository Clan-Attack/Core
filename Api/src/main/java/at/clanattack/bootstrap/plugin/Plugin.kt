package at.clanattack.bootstrap.plugin

import at.clanattack.bootstrap.ICore
import at.clanattack.top.TopCore
import org.bukkit.plugin.java.JavaPlugin

@Suppress("EmptyMethod")
open class Plugin : JavaPlugin() {

    protected val core: ICore
        get() = TopCore.topCore

    override fun onLoad() {
        try {
            with(this.core) { this.registerPlugin(this@Plugin.classLoader, this@Plugin) }
        } catch (e: Exception) {
            this.core.logger.error("An error occurred while trying to load the plugin ${this.description.name}", e)
        }
    }

    override fun onEnable() {
        try {
            enable()
        } catch (e: Exception) {
            this.core.logger.error("An error occurred while trying to load the plugin ${this.description.name}", e)
        }
    }

    override fun onDisable() {
        try {
            disable()
        } catch (e: Exception) {
            this.core.logger.error("An error occurred while trying to load the plugin ${this.description.name}", e)
        }
    }

    open fun load() {

    }

    open fun enable() {

    }

    open fun disable() {

    }

}
