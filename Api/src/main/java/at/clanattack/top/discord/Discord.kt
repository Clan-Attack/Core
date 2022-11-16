package at.clanattack.top.discord

import at.clanattack.discord.IDiscordServiceProvider
import at.clanattack.top.bootstrap.getServiceProvider
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild

val jda: JDA
    get() = getServiceProvider<IDiscordServiceProvider>().jda

val guild: Guild
    get() = getServiceProvider<IDiscordServiceProvider>().guild
