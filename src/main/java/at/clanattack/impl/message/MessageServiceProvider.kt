package at.clanattack.impl.message

import at.clanattack.bootstrap.ICore
import at.clanattack.bootstrap.provider.AbstractServiceProvider
import at.clanattack.bootstrap.provider.ServiceProvider
import at.clanattack.database.ISurrealServiceProvider
import at.clanattack.message.IMessageServiceProvider
import at.clanattack.message.MessageProvider
import at.clanattack.xjkl.future.CompletableFuture
import at.clanattack.xjkl.future.Future
import net.kyori.adventure.text.Component

@ServiceProvider(interfaceClass = IMessageServiceProvider::class, dependProviders = [ISurrealServiceProvider::class])
class MessageServiceProvider(core: ICore) : AbstractServiceProvider(core), IMessageServiceProvider {

    private val colorizing = Colorizing()
    private val messageService = MessageService(core)

    init {
        MessageProvider.instance = this

        this.registerGlobalPlaceholder("prefix", "core.prefix")
        this.registerGlobalPlaceholder("prefix.screen", "core.prefix.screen")
    }

    override fun getMessageAsync(key: String, vararg placeholders: String): Future<Component> {
        val completableFuture = CompletableFuture<Component>()

        this.messageService.getMessageAsync(key, placeholders)
            .then { it -> completableFuture.complete(this.replaceColors(it, '&')) }

        return completableFuture
    }

    override fun getStringMessageAsync(key: String, vararg placeholders: String) =
        this.messageService.getMessageAsync(key, placeholders)

    override fun registerGlobalPlaceholder(key: String, replaceKey: String) =
        this.messageService.registerGlobalPlaceholder(key, replaceKey)

    override fun replaceColors(message: String, code: Char) = colorizing.colorize(message, code)

}