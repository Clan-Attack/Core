package at.clanattack.impl.bootstrap.boot

import at.clanattack.impl.bootstrap.Core
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class Bootstrap : JavaPlugin() {

    private val core by lazy { Core(this) }

    override fun onLoad() {
        this.core.load()
    }

    override fun onEnable() {
        this.core.enable()
    }

    override fun onDisable() {
        this.core.disable()
    }

}