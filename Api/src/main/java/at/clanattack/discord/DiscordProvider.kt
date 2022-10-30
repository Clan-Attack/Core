package at.clanattack.discord

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild

object DiscordProvider {

    var instance: IDiscordServiceProvider? = null

    private val safeInstance: IDiscordServiceProvider
        get() = instance ?: throw IllegalStateException("Discord not initialized yet")

    val jda: JDA
        get() = safeInstance.jda

    val guild: Guild
        get() = safeInstance.guild

}

val jda
    get() = DiscordProvider.jda

val discordGuild
    get() = DiscordProvider.guild