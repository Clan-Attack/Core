package at.clanattack.impl.discord

import at.clanattack.bootstrap.ICore
import at.clanattack.bootstrap.provider.AbstractServiceProvider
import at.clanattack.bootstrap.provider.ServiceProvider
import at.clanattack.discord.IDiscordServiceProvider
import at.clanattack.message.IMessageServiceProvider
import at.clanattack.settings.ISettingServiceProvider
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.Activity.ActivityType
import net.dv8tion.jda.api.entities.Guild

@ServiceProvider(IDiscordServiceProvider::class, [ISettingServiceProvider::class])
class DiscordServiceProvider(core: ICore) : AbstractServiceProvider(core), IDiscordServiceProvider {

    private var internalJda: JDA? = null
    private var internalGuild: Guild? = null
    override val listenerHandler = DiscordListenerHandler(core)

    override val jda: JDA
        get() = internalJda ?: throw IllegalStateException("Discord not initialized yet")

    override val guild: Guild
        get() = internalGuild ?: throw IllegalStateException("Discord not initialized yet")

    override fun load() {
        this.internalJda =
            JDABuilder.createDefault(
                this.core.getServiceProvider(ISettingServiceProvider::class)
                    .getSetting("core.discord.token", String::class)
            )
                .setActivity(
                    Activity.of(
                        this.core.getServiceProvider(ISettingServiceProvider::class)
                            .getSetting("core.discord.activity", ActivityType.WATCHING, ActivityType::class),
                        this.core.getServiceProvider(IMessageServiceProvider::class)
                            .getStringMessage("core.discord.activity")
                    )
                )
                .setEventManager(listenerHandler)
                .build()

        jda.awaitReady()

        this.internalGuild = this.jda.getGuildById(
            this.core.getServiceProvider(ISettingServiceProvider::class)
                .getSetting("core.discord.guild", String::class)
                ?: throw IllegalStateException("Discord guild id should be set")
        )
    }

    override fun disable() {
        this.jda.shutdownNow()
    }


}