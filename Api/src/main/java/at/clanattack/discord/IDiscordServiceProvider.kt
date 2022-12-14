package at.clanattack.discord

import at.clanattack.bootstrap.provider.IServiceProvider
import club.minnced.jda.reactor.ReactiveEventManager
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild

interface IDiscordServiceProvider : IServiceProvider {

    val jda: JDA
    val guild: Guild
    val eventManager: ReactiveEventManager

}
