package at.clanattack.library

import org.bukkit.plugin.java.JavaPlugin

class Library : JavaPlugin() {

    override fun onLoad() {
        this.logger.info("Loaded all libraries.")
    }

}